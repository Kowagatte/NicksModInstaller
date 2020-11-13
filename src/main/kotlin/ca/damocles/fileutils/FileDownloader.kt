package ca.damocles.fileutils

import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.nio.ByteBuffer
import java.nio.channels.Channels
import java.nio.channels.ReadableByteChannel

/**
 * This is not at all written by me.
 * I understand the performance decrease implications, but my users want to see the download progress.
 */


private interface RBCWrapperDelegate {
    // The RBCWrapperDelegate receives rbcProgressCallback() messages
    // from the read loop.  It is passed the progress as a percentage
    // if known, or -1.0 to indicate indeterminate progress.
    //
    // This callback hangs the read loop so a smart implementation will
    // spend the least amount of time possible here before returning.
    //
    // One possible implementation is to push the progress message
    // atomically onto a queue managed by a secondary thread then
    // wake that thread up.  The queue manager thread then updates
    // the user interface progress bar.  This lets the read loop
    // continue as fast as possible.
    fun rbcProgressCallback(progress: Double)
}

class Download(remoteURL: String, localPath: String) :
    RBCWrapperDelegate {
    override fun rbcProgressCallback(progress: Double) {
        println(String.format("Downloading @ %.02f%%", progress))
    }

    private fun contentLength(url: URL): Int {
        val connection: HttpURLConnection
        var contentLength = -1
        try {
            HttpURLConnection.setFollowRedirects(false)
            connection = url.openConnection() as HttpURLConnection
            connection.setRequestMethod("HEAD")
            contentLength = connection.getContentLength()
        } catch (e: Exception) {
        }
        return contentLength
    }

    init {
        val fos: FileOutputStream
        val rbc: ReadableByteChannel
        val url: URL
        try {
            url = URL(remoteURL)
            rbc = RBCWrapper(
                Channels.newChannel(url.openStream()),
                contentLength(url).toLong(),
                this
            )
            fos = FileOutputStream(localPath)
            fos.channel.transferFrom(rbc, 0, Long.MAX_VALUE)
            fos.close()
        } catch (e: Exception) {
            System.err.println("Uh oh: " + e.message)
        }
    }
}

private class RBCWrapper internal constructor(
    private val rbc: ReadableByteChannel,
    private val expectedSize: Long,
    private val delegate: RBCWrapperDelegate
) :
    ReadableByteChannel {
    var readSoFar: Long = 0
        private set

    @Throws(IOException::class)
    override fun close() {
        rbc.close()
    }

    override fun isOpen(): Boolean {
        return rbc.isOpen
    }

    @Throws(IOException::class)
    override fun read(bb: ByteBuffer): Int {
        var n: Int
        val progress: Double
        if (rbc.read(bb).also { n = it } > 0) {
            readSoFar += n.toLong()
            progress =
                if (expectedSize > 0) readSoFar.toDouble() / expectedSize.toDouble() * 100.0 else -1.0
            delegate.rbcProgressCallback(progress)
        }
        return n
    }

}
package ca.damocles

object Defaults{
    val windowsPath: String = System.getenv("APPDATA").toString()+"\\.minecraft"

    /** I don't know if this path works. */
    val linuxPath: String = "~\\.minecraft\\"
}
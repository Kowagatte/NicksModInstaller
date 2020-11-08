package ca.damocles

import ca.damocles.graphics.Frame
import ca.damocles.graphics.panels.ConfirmationScreen
import ca.damocles.utilities.unzip
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.RandomAccessFile
import java.net.URL
import java.nio.channels.Channels

object Defaults{
    val defaultMinecraftPath: String = System.getenv("APPDATA").toString()+"\\.minecraft"
}

object Database{
    private val mongoClient: MongoClient = MongoClients.create("mongodb+srv://version-viewer:2a51E1WRBHSjgRoK@damocles.fcf0g.mongodb.net/Minecraft?retryWrites=true&w=majority")
    private val database: MongoDatabase = mongoClient.getDatabase("Minecraft")
    fun getVersion(version: String = "latest"): InstallationSettings{
        val document = database.getCollection("versions").find(eq("version", version)).first()
        if(document != null){
            return InstallationSettings.fromJson(document.toJson())
        }
        return InstallationSettings("empty", "empty", "empty", HashMap(), HashMap(), HashMap())
    }
}

fun main(args: Array<String>){
    Frame("Nicks Mod Installer!")
}

fun install(playerSettings: PlayerSettings, installSettings: InstallationSettings){
    if(installSettings.version.equals("empty", true)){
        return
    }
    deleteModsFolder()
    downloadFabricInstaller()
    downloadModZip()
    runFabricInstaller(minecraftVersion = installSettings.minecraft_version, fabricVersion = installSettings.fabric_version)
    unZipMods()
    deleteUsedFiles()
    removeMods(playerSettings, installSettings)
    val confirmationScreen = ConfirmationScreen(null)
    confirmationScreen.isVisible = true
}

fun deleteModsFolder(){
    File("${Defaults.defaultMinecraftPath}\\mods\\").deleteRecursively()
}

fun runFabricInstaller(minecraftFolder: String = Defaults.defaultMinecraftPath, minecraftVersion: String, fabricVersion: String){
    println("java -jar ./fabric-installer.jar client -dir \"$minecraftFolder\" -mcversion $minecraftVersion -loader $fabricVersion")
    val proc: Process = Runtime.getRuntime().exec("java -jar ./fabric-installer.jar client -dir \"$minecraftFolder\" -mcversion $minecraftVersion -loader $fabricVersion")
    proc.waitFor()
    val `is`: InputStream = proc.inputStream
    val b = ByteArray(`is`.available())
    `is`.read(b, 0, b.size)
    println(String(b))
}

fun downloadFile(url: String, fileName: String){
    val outputStream = FileOutputStream(fileName)
    outputStream.channel.transferFrom(Channels.newChannel(URL(url).openStream()), 0, Long.MAX_VALUE)
    outputStream.close()
}

fun deleteUsedFiles(){
    File("./fabric-installer.jar").delete()
    File("./mods.zip").delete()
}

fun downloadFabricInstaller(){
    downloadFile("https://maven.fabricmc.net/net/fabricmc/fabric-installer/0.6.1.51/fabric-installer-0.6.1.51.jar", "./fabric-installer.jar")
}

fun downloadModZip(version: String = "latest"){
    downloadFile("https://github.com/Kowagatte/NicksModInstaller/raw/master/src/main/resources/versions/$version.zip", "./mods.zip")
}

fun removeMods(playerSettings: PlayerSettings, installSettings: InstallationSettings){
    val modsToRemove: MutableList<String> = mutableListOf()
    for(mod: String in installSettings.optional.values){
        modsToRemove.add(mod)
    }
    for(mod: String in playerSettings.selectedMods){
        if(installSettings.optional.containsKey(mod)){
            installSettings.optional[mod]?.let { modsToRemove.remove(it) }
        }
    }
    if(playerSettings.performanceMod.equals("optifabric", true)){
        installSettings.optional["phosphor"]?.let { modsToRemove.add(it) }
        installSettings.optional["sodium"]?.let { modsToRemove.add(it) }
    }else{
        installSettings.optional["optifabric"]?.let { modsToRemove.add(it) }
    }
    for(fileName: String in modsToRemove){
        File("${Defaults.defaultMinecraftPath}\\mods\\$fileName").delete()
    }
}

fun unZipMods(minecraftFolder: String = Defaults.defaultMinecraftPath){
    unzip("./mods.zip", "$minecraftFolder\\mods\\")
}
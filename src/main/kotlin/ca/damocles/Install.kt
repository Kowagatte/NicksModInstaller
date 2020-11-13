package ca.damocles

import ca.damocles.fileutils.downloadFile
import ca.damocles.graphics.panels.ConfirmationScreen
import ca.damocles.settings.InstallationSettings
import ca.damocles.settings.PlayerSettings
import java.io.File
import java.io.InputStream

class Install(val installSettings: InstallationSettings, val playerSettings: PlayerSettings){
    lateinit var directory: String
    val modsToInstall = mutableListOf<String>()

    init{
        if(playerSettings.performanceMod.equals("optifabric", true)){
            installSettings.performance["optifabric"]?.let { modsToInstall.add(it) }
            installSettings.performance["optifine"]?.let { modsToInstall.add(it) }
        }else{
            installSettings.performance["phosphor"]?.let { modsToInstall.add(it) }
            installSettings.performance["sodium"]?.let { modsToInstall.add(it) }
        }
        installSettings.required.forEach { (_, u) ->  modsToInstall.add(u)}
        installSettings.optional.forEach { (t, u) -> if(playerSettings.selectedMods.contains(t)){ modsToInstall.add(u)} }
    }

    fun windows(): Install{
        directory = Defaults.windowsPath
        return this
    }

    fun linux(): Install{
        directory = Defaults.linuxPath
        return this
    }

    fun deleteOldMods(): Install{
        val installedMods = mutableListOf<String>()
        val alreadyInstalled = mutableListOf<String>()
        File("$directory\\mods\\").listFiles()?.forEach {
            if(it.isFile){
                installedMods.add(it.name)
            }
        }
        for(mod in modsToInstall){
            if(installedMods.contains(mod)){
                println("Already found mod $mod skipping.")
                alreadyInstalled.add(mod)
                installedMods.remove(mod)
            }
        }
        for(mod in installedMods){
            println("$mod is a old or non nick related mod, removing it.")
            File("$directory\\mods\\$mod").delete()
        }
        for(mod in alreadyInstalled){
            modsToInstall.remove(mod)
        }
        return this
    }

    fun fabric(): Install{
        downloadFile(installSettings.fabricdownload, "./fabric-installer.jar")
        println("java -jar ./fabric-installer.jar client -dir \"$directory\" -mcversion ${installSettings.minecraft_version} -loader ${installSettings.fabric_version}")
        val proc: Process = Runtime.getRuntime().exec("java -jar ./fabric-installer.jar client -dir \"$directory\" -mcversion ${installSettings.minecraft_version} -loader ${installSettings.fabric_version}")
        proc.waitFor()
        val `is`: InputStream = proc.inputStream
        val b = ByteArray(`is`.available())
        `is`.read(b, 0, b.size)
        println(String(b))
        return this
    }

    fun installMods(): Install{
        for(mod in modsToInstall){
            downloadFile("https://storage.googleapis.com/modfiles/${installSettings.version}/$mod.jar", "$directory\\mods\\$mod")
        }
        return this
    }

    fun finish(){
        println("Deleting temp files.")
        File("./fabric-installer.jar").delete()
        File("./mods.zip").delete()
        println("Done. Program will now close.")
        val confirmationScreen = ConfirmationScreen(null)
        confirmationScreen.isVisible = true
    }


}
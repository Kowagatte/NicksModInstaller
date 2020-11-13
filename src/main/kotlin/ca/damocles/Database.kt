package ca.damocles

import ca.damocles.settings.InstallationSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters

object Database{
    //This account only has access to the one database and is only a viewer, feel free to utilize these credentials.
    private val mongoClient: MongoClient = MongoClients.create("mongodb+srv://version-viewer:2a51E1WRBHSjgRoK@damocles.fcf0g.mongodb.net/Minecraft?retryWrites=true&w=majority")
    private val database: MongoDatabase = mongoClient.getDatabase("Minecraft")

    /**
     * Gets the InstallationSettings from the MongoDB
     * @param version: Version to get from the database, defaults to "latest"
     * @return: The InstallationSettings returned, returns an InstallationSettings with empty values if none is found.
     */
    fun getVersion(version: String = "latest"): InstallationSettings {
        val document = database.getCollection("versions").find(Filters.eq("version", version)).first()
        if(document != null){
            return InstallationSettings.fromJson(document.toJson())
        }
        return InstallationSettings(
            "empty",
            "empty",
            "empty",
            "N/A",
            "N/A",
            HashMap(),
            HashMap(),
            HashMap()
        )
    }
}
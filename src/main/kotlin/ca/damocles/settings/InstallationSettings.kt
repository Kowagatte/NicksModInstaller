package ca.damocles.settings

import com.google.gson.Gson

data class InstallationSettings(val version: String, val minecraft_version: String, val fabric_version: String, val fabricdownload: String, val moddownload: String, val required: Map<String, String>, val optional: Map<String, String>, val performance: Map<String, String>) {
    companion object{
        fun fromJson(json: String): InstallationSettings {
            return Gson().fromJson(json, InstallationSettings::class.java)
        }
    }
    override fun toString(): String = Gson().toJson(this)
}
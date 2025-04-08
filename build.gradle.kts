import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    id("vkid.manifest.placeholders") version "1.1.0" apply true
}

vkidManifestPlaceholders {
    init(
        clientSecret = "ZbVWny2MQpZ94tXTzaYz",
        clientId = "53405674"
    )
}

extra.apply {
    set("mapKitKey", getMapKitKey())
    set("staticKey", getStaticKey())
}

private fun getMapKitKey(): String {
    val properties = Properties()
    val file = rootProject.file("local.properties")
    properties.load(FileInputStream(file))

    return properties.getProperty("MAPKIT_API_KEY", "")
}

private fun getStaticKey(): String {
    val properties = Properties()
    val file = rootProject.file("local.properties")
    properties.load(FileInputStream(file))

    return properties.getProperty("STATIC_API_KEY", "")
}
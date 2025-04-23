import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    id("vkid.manifest.placeholders") version "1.1.0" apply true
    id("com.google.devtools.ksp") version "2.0.21-1.0.25" apply false
}

val properties = Properties()
val file = rootProject.file("local.properties")
properties.load(FileInputStream(file))

vkidManifestPlaceholders {
    init(
        clientSecret = getVkSecretKey(),
        clientId = getVkClientId()
    )
}

extra.apply {
    set("mapKitKey", getMapKitKey())
    set("staticKey", getStaticKey())
    set("yandexClientId", getYandexClientId())
    set("googleClientId", getGoogleClientId())
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

private fun getVkClientId(): String {
    return properties.getProperty("VK_CLIENT_ID", "")
}

private fun getVkSecretKey(): String {
    return properties.getProperty("VK_SECRET_KEY", "")
}

private fun getYandexClientId(): String {
    return properties.getProperty("YANDEX_CLIENT_ID", "")
}

private fun getGoogleClientId(): String {
    return properties.getProperty("GOOGLE_CLIENT_ID", "")
}
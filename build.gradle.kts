import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}

extra.apply {
    set("mapKitKey", getMapKitKey())
}

private fun getMapKitKey(): String {
    val properties = Properties()
    val file = rootProject.file("local.properties")
    properties.load(FileInputStream(file))

    return properties.getProperty("MAPKIT_API_KEY", "")
}
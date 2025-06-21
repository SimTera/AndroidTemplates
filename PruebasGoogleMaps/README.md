# README para PruebasGoogleMaps - Proyecto Android con Google Maps

## 📁 Estructura del Proyecto

PruebasGoogleMaps/
├── app/
│ ├── build.gradle.kts # Configuración del módulo principal
│ ├── src/main/
│ │ ├── AndroidManifest.xml # Configuración de permisos y componentes
│ │ ├── java/ # Código fuente Kotlin
│ │ └── res/ # Recursos (layouts, strings, etc)
├── build.gradle.kts # Configuración global del proyecto
├── settings.gradle.kts # Definición de módulos
├── gradle/
│ └── libs.versions.toml # Centralización de versiones de dependencias
├── secrets.properties # Clave API de Google Maps (NO SUBIR A GIT)
└── local.defaults.properties # Clave dummy por defecto


---

## 🔐 Configuración Clave de Google Maps

Crea `secrets.properties` en la raíz del proyecto con:

```properties
MAPS_API_KEY=TU_CLAVE_AQUÍ
Añade al .gitignore:

secrets.properties
En AndroidManifest.xml:

<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="${MAPS_API_KEY}" />
📦 Configuración de Dependencias (libs.versions.toml)

En gradle/libs.versions.toml:

[versions]
androidx-compose-bom = "2023.08.00"
maps-compose = "4.4.1"
play-services-maps = "18.2.0"
play-services-location = "21.2.0"

[libraries]
# Jetpack Compose
androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "androidx-compose-bom" }
androidx-ui = { group = "androidx.compose.ui", name = "ui" }
androidx-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
androidx-ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
androidx-ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
androidx-material3 = { group = "androidx.compose.material3", name = "material3" }

# Google Maps
maps-compose = { group = "com.google.maps.android", name = "maps-compose", version.ref = "maps-compose" }
play-services-maps = { group = "com.google.android.gms", name = "play-services-maps", version.ref = "play-services-maps" }
play-services-location = { group = "com.google.android.gms", name = "play-services-location", version.ref = "play-services-location" }

[plugins]
android-application = { id = "com.android.application", version = "8.3.0" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version = "1.9.20" }
🛠 build.gradle.kts (Nivel Proyecto)

buildscript {
    dependencies {
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}
📱 build.gradle.kts (Módulo app)

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.example.pruebasgooglemaps"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.pruebasgooglemaps"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {
    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Google Maps
    implementation(libs.maps.compose)
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
}

secrets {
    propertiesFileName = "secrets.properties"
    defaultPropertiesFileName = "local.defaults.properties"
}
🧾 AndroidManifest.xml

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <!-- Permisos esenciales -->
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

    <application
        android:allowBackup="true"
        android:theme="@style/Theme.PruebasGoogleMaps">
        
        <!-- API Key de Google Maps -->
        <meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="${MAPS_API_KEY}" />

        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:label="@string/app_name">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
🗺 Ejemplo de Código (MainActivity.kt)

@Composable
fun MapScreen() {
    val madrid = LatLng(40.4168, -3.7038)
    val markerState = rememberMarkerState(position = madrid)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(madrid, 12f)
    }
    
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(mapType = MapType.HYBRID),
        uiSettings = MapUiSettings(zoomControlsEnabled = true)
    ) {
        Marker(
            state = markerState,
            title = "Madrid",
            snippet = "Capital de España"
        )
    }
}
✅ Flujo de Trabajo Recomendado

Configura API Key en secrets.properties
Sincroniza Gradle: Android Studio > File > Sync Project
Ejecuta en dispositivo físico (requiere Google Play Services)
Para desarrollo rápido desde terminal:

./gradlew installDebug
✍️ Estructura de Commits

git commit -m "feat: add google maps integration"
git commit -m "refactor: update dependencies using libs.versions"
git commit -m "docs: update readme with setup instructions"

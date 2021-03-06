val ktorVersion = "1.5.0"
val kotlinVersion = "1.4.21"
val logbackVersion = "1.2.1"
val koinVersion = "2.2.2"

plugins {
    application
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
    id("org.flywaydb.flyway") version "5.2.4"
}

group = "com.project.hotel"
version = "0.0.1"

application {
    mainClassName = "com.project.hotel.ApplicationKt"
}

flyway {
    user = System.getenv("DB_USER") ?: "urpjzzwxegxrcs"
    val port = 5432
    password = System.getenv("DB_PASSWORD") ?: "269fa2d5f7c7697c06b11946e0ef803c4dd459b8a384db1a50fc35db1f1afd17"
    val uri = "ec2-54-89-49-242.compute-1.amazonaws.com"
    val dbName = "d6csd792km2rhk"
    url = System.getenv("DB_URL") ?: "postgres://$user:$password@$uri:$port/$dbName"
    baselineOnMigrate = true
    locations = arrayOf("filesystem:resources/db/migration")
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
    maven { url = uri("https://kotlin.bintray.com/kotlin-js-wrappers") }
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")

    koinDependencies()
    ktorDependencies()
    databaseDependencies()

    //serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.jetbrains:kotlin-css-jvm:1.0.0-pre.31-kotlin-1.2.41")
}


fun DependencyHandlerScope.koinDependencies() {
    implementation("org.koin:koin-ktor:$koinVersion")
    testImplementation("org.koin:koin-test:$koinVersion")
}

fun DependencyHandlerScope.ktorDependencies() {
    implementation("io.ktor:ktor-websockets:$ktorVersion")
    implementation("io.ktor:ktor-network:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-locations:$ktorVersion")
    implementation("io.ktor:ktor-metrics:$ktorVersion")
    implementation("io.ktor:ktor-server-sessions:$ktorVersion")
    implementation("io.ktor:ktor-auth:$ktorVersion")
    implementation("io.ktor:ktor-auth-jwt:$ktorVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}

fun DependencyHandlerScope.databaseDependencies() {
    implementation("org.jetbrains.exposed:exposed:0.17.7")
    implementation("com.zaxxer:HikariCP:3.4.5")
    implementation("org.postgresql:postgresql:42.2.18")
    implementation("org.flywaydb:flyway-core:7.3.2")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")

tasks.create("stage") {
    dependsOn("installDist")
}
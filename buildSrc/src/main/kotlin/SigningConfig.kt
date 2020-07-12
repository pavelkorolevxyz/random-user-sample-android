import java.io.File
import java.util.Properties

/**
 * Data class describing keystore properties to use on signing
 */
data class SigningConfig(

    /**
     * Path to keystore file
     */
    val keystoreFile: File,

    /**
     * Keystore file password
     */
    val password: String,

    /**
     * Application alias
     */
    val alias: String,

    /**
     * Application alias password
     */
    val aliasPassword: String

)

/**
 * Loads [SigningConfig] from keystore.properties file
 */
fun loadSigningConfig(keystorePropertiesFile: File): SigningConfig? {
    if (!keystorePropertiesFile.exists()) return null

    val properties = Properties().apply {
        load(keystorePropertiesFile.inputStream())
    }
    val keystoreFilePath = properties.getProperty("keystore.path")
    val keystorePassword = properties.getProperty("keystore.password")
    val alias = properties.getProperty("keystore.alias")
    val aliasPassword = properties.getProperty("keystore.alias.password")

    return SigningConfig(
        File(keystoreFilePath),
        keystorePassword,
        alias,
        aliasPassword
    )
}

fun loadSigningConfigFromEnvironment(): SigningConfig {
    val keystoreFilePath = System.getenv("KEYSTORE_PATH")
    val keystorePassword = System.getenv("KEYSTORE_PASSWORD")
    val alias = System.getenv("KEYSTORE_ALIAS")
    val aliasPassword = System.getenv("KEYSTORE_ALIAS_PASSWORD")

    return SigningConfig(
        File(keystoreFilePath),
        keystorePassword,
        alias,
        aliasPassword
    )
}

package api.shmehdi.qouteapp.authorization

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private val algorithm = "HmacSHA1"
private val hashKey = System.getenv("HASH_KEY").toByteArray()
private val hmacKey = SecretKeySpec(hashKey, algorithm)

fun hash(password: String): String {
    val hmac = Mac.getInstance(algorithm).also {
        it.init(hmacKey)
    }
    return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
}
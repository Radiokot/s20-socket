package ua.com.radiokot.socket

import android.net.IpPrefix
import com.google.gson.annotations.SerializedName
import java.net.InetAddress

/**
 * Created by Oleg Koretsky on 15.09.2018.
 */
class Socket(
        @SerializedName("name")
        val name: String,
        @SerializedName("ip")
        val ip: InetAddress,
        @SerializedName("mac")
        val mac: ByteArray,
        @SerializedName("port")
        val port: Int = DEFAULT_PORT
) {
    companion object {
        const val DEFAULT_PORT = 10000
    }
}
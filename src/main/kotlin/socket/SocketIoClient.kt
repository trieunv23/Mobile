package socket

import io.socket.client.IO
import io.socket.client.Socket

class SocketIoClient {
    public lateinit var socket: Socket

    fun connect(url: String) {
        try {
            socket = IO.socket(url)

            socket.on(Socket.EVENT_CONNECT) {
                println("Connected to Socket.IO server")
            }.on(Socket.EVENT_DISCONNECT) {
                println("Disconnected from Socket.IO server")
            }.on("new-message-server") {
                args ->
                val message = args[0].toString()
                println("Received message: $message")
            }

            socket.connect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun sendMessage(event: String, message: String) {
        socket.emit(event, message)
    }
}
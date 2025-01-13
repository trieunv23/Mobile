import socket.SocketIoClient
import java.util.Scanner
import com.google.gson.Gson
import utils.GsonUtils
import java.util.ArrayList

data class LoginForm(
    val email: String,
    val password: String
)

data class Message(val socketId: String, val message: String)

fun main(args: Array<String>) {
    /*
    println("Enter email: ")

    val email = readLine() ?: ""

    println("222")

    println("Enter email: ")

    val password = readLine() ?: ""

    if (email == "" || password == "") {
        println("Error")
        return;
    }
    val loginForm = LoginForm(
        email = email,
        password = password
    )

    val gson = Gson()

    val jsonBody = gson.toJson(loginForm)
    val body = jsonBody.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
    val apiClient = ApiClient()

    val response = apiClient.callApi(
        url = "http://192.168.1.4:3000/auth/login",
        method = "POST",
        body = body
    )

    println(response)

     */


    val url = "http://192.168.1.4:3000"
    val client = SocketIoClient()

    client.connect(url)

    startMessaging(client)

    // Thread.sleep(10000)

    println("Program arguments: ${args.joinToString()}")


    /*
    val gsonUntil = GsonUtils()

    val message = Message(
        socketId = "12",
        message = "Hello"
    )

    val message2 = Message(
        socketId = "123",
        message = "Hello000"
    )

    val messageList = ArrayList<Message>()

    messageList.add(message)
    messageList.add(message2)

    val messageJson = gsonUntil.toJson(messageList)

    // val messageListObject = gsonUntil.fromJson(messageJson)

    println(messageJson)

     */
}

fun startMessaging(client: SocketIoClient) {
    val scanner = Scanner(System.`in`)
    val gson = Gson()


    while (true) {
        println("Enter message to send to server (or type 'exit' to quit):")
        val message = scanner.nextLine()
        if (message.equals("exit", ignoreCase = true)) {
            break
        }

        val jsonMessage = "{socketId:\"$12\",message:\"$message\"}"

        client.sendMessage("new-message-client", jsonMessage)
    }
}
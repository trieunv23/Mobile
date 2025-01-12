import api.ApiClient
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

data class LoginForm(
    val email: String,
    val password: String
)

fun main(args: Array<String>) {
    println("Enter email: ")

    val email = readln() ?: ""

    println("Enter email: ")

    val password = readln() ?: ""

    if (email == "" || password == "") {
        println("Error")
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

    println("Program arguments: ${args.joinToString()}")
}
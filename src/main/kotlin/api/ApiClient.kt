package api

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

data class ApiResponse(
    val statusCode: Int,
    val body: String?,
    val error: String?
)

class ApiClient {
    fun createOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder() .addInterceptor(logging) .build()
    }

    fun callApi(
        url: String,
        method: String,
        body: RequestBody? = null,
        headers: Map<String, String> = emptyMap()
    ): ApiResponse? {
        val client = createOkHttpClient()
        val requestBuilder = Request.Builder().url(url)

        headers.forEach { (key, value) ->
            requestBuilder.addHeader(key, value)
        }

        if (method == "POST" || method == "PUT") {
            body?.let {
                requestBuilder.method(method, body)
            }
        } else {
            requestBuilder.method(method, null)
        }

        val request = requestBuilder.build()

        return try {
            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                val responseBody = response.body?.string()

                ApiResponse(
                    statusCode = response.code,
                    body = responseBody,
                    error = null
                )
            } else {
                ApiResponse(
                    statusCode = response.code,
                    body = null,
                    error = "Error: ${response.message}"
                )
            }
        } catch (e: IOException) {
            ApiResponse(
                statusCode = -1,
                body = null,
                error = "IOException: ${e.message}"
            )
        }
    }
}

package com.testapp.dagger2test.network.auth


//Right way to user Retrofit
sealed class AuthResource <T>(
    val data : T? = null,
    val message : String? = null
) {
    class Authenticated <T>(data : T?) : AuthResource<T>(data)
    class LogOut<T>(data : T? = null) : AuthResource<T>(data)
    class Loading <T>(data : T? = null) : AuthResource<T>(data)
    class Error <T>(data : T?, message: String?) : AuthResource<T>(data, message)
}
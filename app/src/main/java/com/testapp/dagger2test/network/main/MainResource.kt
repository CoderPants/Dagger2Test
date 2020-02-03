package com.testapp.dagger2test.network.main

sealed class MainResource <T>(
    val data : T? = null,
    val message: String? = null
) {
    class Success <T>(data : T?) : MainResource<T>(data)
    class Loading <T>(data : T? = null) : MainResource<T>(data)
    class Error <T>(data : T?, message: String?) : MainResource<T>(data, message)
}
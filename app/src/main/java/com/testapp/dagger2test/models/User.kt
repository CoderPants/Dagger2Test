package com.testapp.dagger2test.models

import com.google.gson.annotations.SerializedName

data class User (
    val id : Int = -1,
    @SerializedName("name")
    val userName : String = "",
    val email : String = "",
    val website : String= ""
) {

}
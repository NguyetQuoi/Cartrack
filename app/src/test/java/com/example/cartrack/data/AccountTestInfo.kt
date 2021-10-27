package com.example.cartrack.data

object DataProvider {
    val userTestFail = AccountTestInfo("fail@gmail.com", "123456@qCC", "123456", "Fail", "fail")
    val userTestSuccess =
        AccountTestInfo("success@gmail.com", "123456@qCC", "123456", "Success", "success")
}

data class AccountTestInfo(
    val email: String,
    val pass: String,
    val confirmCode: String,
    val name: String,
    val result: String
)
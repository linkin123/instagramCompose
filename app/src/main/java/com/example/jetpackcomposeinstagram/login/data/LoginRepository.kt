package com.example.jetpackcomposeinstagram.login.data

import com.example.jetpackcomposeinstagram.login.data.network.LoginService

class LoginRepository {
    private val api = LoginService()

    suspend fun doLogin(user : String, passwrod : String) : Boolean{
        return api.doLogin(user, passwrod)
    }
}
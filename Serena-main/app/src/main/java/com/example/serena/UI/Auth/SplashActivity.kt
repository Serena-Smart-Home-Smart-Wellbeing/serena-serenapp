package com.example.serena.UI.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.serena.API.ILoginResponseApi
import com.example.serena.API.IResponseApiError
import com.example.serena.API.Service
import com.example.serena.R
import com.example.serena.UI.MainActivity
import com.example.serena.data.Authentication

class SplashActivity : AppCompatActivity() {

    private  lateinit var api: Service
    private lateinit var auth: Authentication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        api = Service(this)
        auth = Authentication(this)

        val welcome = Intent(this, WelcomeActivity::class.java)
        val home = Intent(this, MainActivity::class.java)
        val login = Intent(this, LoginActivity::class.java)

        val auth = Authentication(this)
        val email = auth.getEmail()
        val password = auth.getPassword()


        if (email == null) {
            startActivity(welcome)
        }else if(password == null) {
            startActivity(welcome)
        } else {
            api.login(email, password, object : Service.ApiCallback<ILoginResponseApi> {
                override fun onSuccess(status: ILoginResponseApi) {
                    auth.saveAccount(status.userId, email, password)
                    auth.setToken(status.accessToken)
                    startActivity(home)
                }
                override fun onFailure(message: IResponseApiError) {
                    Log.d("FAILURE", message.message)
                    startActivity(login)
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        checkUserStatus()
    }

    private fun checkUserStatus() {
        val welcome = Intent(this, WelcomeActivity::class.java)
        val home = Intent(this, MainActivity::class.java)
        val login = Intent(this, LoginActivity::class.java)

        val email = auth.getEmail()
        val password = auth.getPassword()

        if (email == null || password == null) {
            startActivity(welcome)
        } else {
            api.login(email, password, object : Service.ApiCallback<ILoginResponseApi> {
                override fun onSuccess(status: ILoginResponseApi) {
                    auth.saveAccount(status.userId, email, password)
                    auth.setToken(status.accessToken)
                    startActivity(home)
                }
                override fun onFailure(message: IResponseApiError) {
                    Log.d("FAILURE", message.message)
                    startActivity(login)
                }
            })
        }
    }

}
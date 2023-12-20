package com.example.serena.UI.Auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.serena.API.ILoginResponseApi
import com.example.serena.API.IResponseApiError
import com.example.serena.API.Service
import com.example.serena.R
import com.example.serena.UI.MainActivity
import com.example.serena.data.Authentication

class LoginActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private  lateinit var api: Service
    private  lateinit var auth: Authentication
    private lateinit var mainActivity: Intent
    private lateinit var welcomeActivity: Intent
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        api = Service(this)
        context = this
        inputEmail = findViewById(R.id.email)
        inputPassword = findViewById(R.id.password)
        btnLogin = findViewById(R.id.login)
        auth = Authentication(this)
        mainActivity = Intent(this, MainActivity::class.java)
        welcomeActivity = Intent(this, WelcomeActivity::class.java)

        val btnBack: Button = findViewById(R.id.back)
        btnBack.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }

        loadActivity()
    }

    private fun loadActivity() {
        btnLogin.setOnClickListener {
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()
            Log.d("EMAIL", email + password)
            api.login(email, password, object : Service.ApiCallback<ILoginResponseApi> {
                override fun onSuccess(status: ILoginResponseApi) {
                    Log.d("SUCCESS", status.accessToken)
                    auth.saveAccount(status.userId, email, password)
                    auth.setToken(status.accessToken)
                    startActivity(mainActivity)
                }
                override fun onFailure(message: IResponseApiError) {
                    runOnUiThread {
                        Toast.makeText(context, message.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
}
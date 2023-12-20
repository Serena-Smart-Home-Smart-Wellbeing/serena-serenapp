package com.example.serena.UI.Home


import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.serena.R
import com.example.serena.UI.MainActivity
import com.example.serena.data.Authentication

class SerenBoxSessionFragment(private val mainActivity: MainActivity) : Fragment(R.layout.fragment_seren_box_sesssion) {
    private lateinit var stopBtn: Button
    private lateinit var countDownMinutes: TextView
    private lateinit var countDownSeconds: TextView
    private lateinit var auth: Authentication

    val handler = android.os.Handler(Looper.getMainLooper())


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stopBtn = view.findViewById(R.id.stop)
        auth = Authentication(requireActivity())

        countDownMinutes = view.findViewById(R.id.countdown_minutes)
        countDownSeconds = view.findViewById(R.id.countdown_seconds)

        loadFragment()

    }

    private fun loadFragment() {
        stopBtn.setOnClickListener{
            mainActivity.serenBoxConfigure()
        }
        val configuration = auth.getSerenBoxConfiguration()
        startCountDown(configuration.duration)
    }


    private fun  startCountDown(duration: Int) {
        val minutesValue: Int = duration
        val secondValue: Int = countDownSeconds.text.toString().toInt()
        if(minutesValue > 0) {
            if(secondValue > 0) {
                countDownSeconds.text = (secondValue - 1).toString()
            } else {
                countDownMinutes.text = (minutesValue - 1).toString()
                countDownSeconds.text = (60 - 1).toString()
            }
            handler.postDelayed({
                startCountDown(minutesValue)
            }, 1000)
        } else {
            Log.d("SUCCESS", "TASK DONE")
        }
    }

}
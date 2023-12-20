package com.example.serena.UI.EmotionDetection


import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.serena.R
import com.example.serena.UI.MainActivity


class EmotionDetectionStartFragment(private val mainActivity: MainActivity) : Fragment(R.layout.fragment_emotion_detection_start) {
    private lateinit var btnStart: Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnStart = view.findViewById(R.id.start)
        btnStart.setOnClickListener {
            mainActivity.backToPhotoCaptureFragment()
        }
    }
}
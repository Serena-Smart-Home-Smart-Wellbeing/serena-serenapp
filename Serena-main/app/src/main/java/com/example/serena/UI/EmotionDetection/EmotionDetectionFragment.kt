package com.example.serena.UI.EmotionDetection


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.serena.R
import com.example.serena.UI.MainActivity


class EmotionDetectionFragment(private val mainActivity: MainActivity) : Fragment(R.layout.fragment_emotion_detection) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val emotionDetectionStart = EmotionDetectionStartFragment(mainActivity)
        loadFragment(emotionDetectionStart)
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}


package com.example.stm

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.stm.databinding.ActivityDetailBinding
import java.util.*

class DetailActivity : AppCompatActivity() ,TextToSpeech.OnInitListener{
    private lateinit var binding: ActivityDetailBinding
    private lateinit var textToSpeech: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val button = findViewById<Button>(R.id.button)


        textToSpeech = TextToSpeech(this, this)
        val bundle = intent.extras
        if (bundle != null) {
            button.setOnClickListener {
                val text = bundle.getString("Content")
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            }
            binding.detailTitle.text = bundle.getString("Title")
            binding.detailContent.text = bundle.getString("Content")
            binding.detailDate.text = bundle.getString("Date")

        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Language not supported
            }
        } else {
            // Initialization failed
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        textToSpeech.shutdown()
    }
}
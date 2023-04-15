package com.example.speechtotext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.TextView
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var btMicOn : Button
    lateinit var tvTextView : TextView
    private val REQUEST_CODE_SPEECH_INPUT =100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btMicOn = findViewById(R.id.btbutton)
        tvTextView = findViewById(R.id.tvTextView)

        btMicOn.setOnClickListener(){
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello Speak...!!!")
            startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == RESULT_OK && data!= null)
        {
            var res = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            var text = res?.get(0)
            tvTextView.text = text

        }
    }
}
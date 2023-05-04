package com.example.stm

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.stm.databinding.ActivityUploadBinding
import com.example.todolist.Note
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.saveButton.setOnClickListener {
            saveData()
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveData(){
        val title = binding.uploadTitle.text.toString()
        val content = binding.uploadContent.text.toString()
        val date = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = date.format(formatter)

        val dataClass = Note(title, content, formatted)
        Note.data.add(dataClass)
        val intent = Intent(this@UploadActivity, NotesActivity::class.java)
        startActivity(intent)
        finish()

    }
    private fun uploadData(){

    }
}
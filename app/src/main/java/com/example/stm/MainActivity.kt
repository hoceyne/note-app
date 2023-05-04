package com.example.stm

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {


    lateinit var flip: AnimatorSet
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {

        supportFragmentManager
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lunch)
        var scale = applicationContext.resources.displayMetrics.density

        val front = findViewById<CardView>(R.id.card_view)
        front.cameraDistance = 8000 * scale
        flip = AnimatorInflater.loadAnimator(applicationContext, R.anim.flip) as AnimatorSet
        flip.setTarget(front);
        flip.start()
        Handler().postDelayed({
            val changePage = Intent(this, NotesActivity::class.java)
                    startActivity(changePage)
        }, 2200)

    }
}
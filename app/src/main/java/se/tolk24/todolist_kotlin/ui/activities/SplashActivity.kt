package se.tolk24.todolist_kotlin.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import se.tolk24.todolist_kotlin.R

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        val startButton: Button = findViewById(R.id.btn_start)
        startButton.setOnClickListener {

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}
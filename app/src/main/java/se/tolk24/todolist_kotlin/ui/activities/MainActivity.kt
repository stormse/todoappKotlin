package se.tolk24.todolist_kotlin.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import se.tolk24.todolist_kotlin.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    fun setTitle(title: String) {
        supportActionBar?.let { it.title = title }
    }

    fun setBackButton(enabled: Boolean) {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(enabled)
            it.setHomeButtonEnabled(enabled)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
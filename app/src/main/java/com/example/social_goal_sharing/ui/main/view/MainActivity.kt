package com.example.social_goal_sharing.ui.main.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.main.view.sign_in_up.Sign_up
import com.example.social_goal_sharing.ui.main.view.sign_in_up.Sign_in

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnsignup = findViewById<Button>(R.id.btnsignup1)
        val btnsignin = findViewById<Button>(R.id.btnsignin)
        val toolBar = findViewById<Toolbar>(R.id.bottomnavigationtoolbar)

        setSupportActionBar(toolBar)

        btnsignup.setOnClickListener {
            startActivity(Intent(this, Sign_up::class.java))
        }
        btnsignin.setOnClickListener{
            startActivity(Intent(this, Sign_in::class.java))
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.disconnectIcon -> {
            startActivity(Intent(this, Sign_in::class.java))
            finishAffinity()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
package com.example.social_goal_sharing.ui.main.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.databinding.ActivityMainBinding
import com.example.social_goal_sharing.ui.main.view.sign_in_up.Sign_up
import com.example.social_goal_sharing.ui.main.view.sign_in_up.Sign_in
import com.example.social_goal_sharing.ui.main.view.toolbar_fragments.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame,fragment)
        fragmentTransaction.commitNow()
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        replaceFragment(Home())

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnlogout = findViewById<ImageButton>(R.id.logoutIcon)
        btnlogout.setOnClickListener(){
            startActivity(Intent(this, Sign_in::class.java))
            finishAffinity()
        }
        binding.bottomnavigationtoolbar1.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.logoutIcon -> {
                    startActivity(Intent(this, Sign_in::class.java))
                    finishAffinity()

                }
                else -> {}
            }
            true
        }
        binding.bottomnavigationtoolbar2.setOnItemSelectedListener {

            when(it.itemId){
                R.id.homeIcon -> replaceFragment(Home())
                R.id.profilIcon -> replaceFragment(profile())
                R.id.plusIcon -> replaceFragment(Add())
                R.id.searchIcon -> replaceFragment(LookFor())
                R.id.messageIcon -> replaceFragment(Discussion())
                else -> {}
            }
            true
        }
    }
}
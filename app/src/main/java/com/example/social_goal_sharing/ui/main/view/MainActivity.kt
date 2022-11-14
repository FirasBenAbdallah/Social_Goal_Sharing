package com.example.social_goal_sharing.ui.main.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.databinding.ActivityMainBinding
import com.example.social_goal_sharing.ui.main.view.sign_in_up.Sign_up
import com.example.social_goal_sharing.ui.main.view.sign_in_up.Sign_in
import com.example.social_goal_sharing.ui.main.view.toolbar_fragments.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var binding: ActivityMainBinding
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame,fragment)
        fragmentTransaction.commitNow()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

//        setContentView(R.layout.activity_main)
        binding.bottomnavigationtoolbar.setOnItemSelectedListener {

            when(it.itemId){

                R.id.homeIcon -> replaceFragment(Home())
                R.id.profilIcon -> replaceFragment(profile())
                R.id.plusIcon -> replaceFragment(Add())
                R.id.searchIcon -> replaceFragment(LookFor())
                R.id.messageIcon -> replaceFragment(Discussion())
                else -> {

                }

            }
            true
        }

        val btnsignup = findViewById<Button>(R.id.btnsignup1)
        val btnsignin = findViewById<Button>(R.id.btnsignin)
//        val toolBar = findViewById<Toolbar>(R.id.bottomnavigationtoolbar)

//        setSupportActionBar(toolBar)


        btnsignup.setOnClickListener {
            startActivity(Intent(this, Sign_up::class.java))
        }
        btnsignin.setOnClickListener{
            startActivity(Intent(this, Sign_in::class.java))
        }

    }


   /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile, menu)
        return super.onCreateOptionsMenu(menu)
    }
*/
    /*override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.disconnectIcon -> {
            startActivity(Intent(this, Sign_in::class.java))
            finishAffinity()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }*/
}
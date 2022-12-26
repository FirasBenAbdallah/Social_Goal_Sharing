package com.example.social_goal_sharing.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.main.adapter.ChatAdapter
import com.example.social_goal_sharing.ui.utils.SharedPreference
import com.google.android.material.tabs.TabLayout
import recycler.HomeAdapter

class Chat : AppCompatActivity() {

    lateinit var tableLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var sharedPreference: SharedPreference
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        sharedPreference = SharedPreference()
        tableLayout = findViewById(R.id.tabLay)
        viewPager = findViewById(R.id.viewP)
        drawerLayout = findViewById(R.id.drawer)

        tableLayout.addTab(
            tableLayout.newTab().setText("Contacts")
        )
        val adapter = ChatAdapter(this,supportFragmentManager, tableLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tableLayout)
        )
        tableLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    TODO("Not yet implemented")
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    TODO("Not yet implemented")
                }


            }
        )

    }
}
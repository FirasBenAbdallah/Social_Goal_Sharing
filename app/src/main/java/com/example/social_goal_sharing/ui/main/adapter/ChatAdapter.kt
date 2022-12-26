package com.example.social_goal_sharing.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.main.view.toolbar_fragments.ContactsFragment
import recycler.ChatList

class ChatAdapter(
    context: Context,
    fm: FragmentManager,
    internal var totalTabs : Int
): FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0){
            return ContactsFragment()
        }
        return ContactsFragment()
    }

}



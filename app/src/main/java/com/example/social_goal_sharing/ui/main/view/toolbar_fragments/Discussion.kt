package com.example.social_goal_sharing.ui.main.view.toolbar_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.main.adapter.ChatAdapter
import recycler.ChatList


class Discussion : Fragment() {
    lateinit var tabLay: TableLayout
    lateinit var viewP: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_discussion, container, false)
        tabLay = v.findViewById(R.id.tabLay)
        viewP = v.findViewById(R.id.viewP)

        return v
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)


        /*val r = v.findViewById<RecyclerView>(R.id.recycleDisc)

        val list = arrayListOf<ChatList>(
            ChatList(R.drawable.ic_baseline_account,"FirasBA","hey","3h"),
            ChatList(R.drawable.ic_baseline_account,"Firas","how are you ","1m"),
        )
        r.adapter = ChatAdapter(list)
        r.layoutManager = LinearLayoutManager(v.context)
    }
}
private fun chat(){

    tabLay.addTab(
        tabLay.newTab().setText("Contacts")
    )*/
    }

}
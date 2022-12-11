package com.example.social_goal_sharing.ui.main.view.toolbar_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.ui.main.view.Adapters.ChatAdapter
import recycler.Acc
import recycler.ChatList
import recycler.HomeAdapter

class Discussion : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discussion, container, false)
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)
        val r = v.findViewById<RecyclerView>(R.id.recycleDisc)

        val list = arrayListOf<ChatList>(
            ChatList(R.drawable.ic_baseline_account,"FirasBA","hey","3h"),
            ChatList(R.drawable.ic_baseline_account,"Firas","how are you ","1m"),
        )
        r.adapter = ChatAdapter(list)
        r.layoutManager = LinearLayoutManager(v.context)
    }
}
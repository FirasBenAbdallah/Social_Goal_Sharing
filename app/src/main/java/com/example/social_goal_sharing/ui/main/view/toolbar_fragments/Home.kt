package com.example.social_goal_sharing.ui.main.view.toolbar_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.social_goal_sharing.R
import recycler.Acc
import recycler.HomeAdapter


class Home : Fragment() {

lateinit var recyclerView: RecyclerView
/*lateinit var adapter: HomeAdapter
lateinit var homeitemsList : ArrayList<Acc>*/




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
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        val r = v.findViewById<RecyclerView>(R.id.recycleHome)

        val list = arrayListOf<Acc>(
           Acc(R.drawable.ic_baseline_account,"FirasBA","3h",R.drawable.fifa,"chaima",R.drawable.icons8_heart_24,R.drawable.ic_baseline_message_24,R.drawable.icons8_share_24),
           Acc(R.drawable.ic_baseline_account,"FirasBA","3h",R.drawable.fifa,"chaima",R.drawable.icons8_heart_24,R.drawable.ic_baseline_message_24,R.drawable.icons8_share_24),
           Acc(R.drawable.ic_baseline_account,"FirasBA","3h",R.drawable.fifa,"chaima",R.drawable.icons8_heart_24,R.drawable.ic_baseline_message_24,R.drawable.icons8_share_24)
        )
        r.adapter = HomeAdapter(list)
        return v
    }
/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }*/

}
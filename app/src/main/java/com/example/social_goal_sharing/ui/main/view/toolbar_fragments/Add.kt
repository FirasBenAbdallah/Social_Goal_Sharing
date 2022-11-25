package com.example.social_goal_sharing.ui.main.view.toolbar_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageButton
import android.widget.TextView
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.databinding.ActivityMainBinding
import com.example.social_goal_sharing.databinding.FragmentAddBinding
import com.google.android.material.datepicker.MaterialDatePicker

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [Add.newInstance] factory method to
 * create an instance of this fragment.
 */
class Add : Fragment(R.layout.fragment_add) {
     private  var _binding: FragmentAddBinding? = null
     private val binding get() = _binding !!
    val dateText = view?.findViewById<TextView>(R.id.dateEv)

    // TODO: Rename and change types of parameters





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
        _binding = FragmentAddBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Add.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Add().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun showDateRange() {
        val dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("Select Date")
            .build()

        dateRangePicker.show(
            parentFragmentManager,
            "date_range_picker"
        )
        dateRangePicker.addOnPositiveButtonClickListener { datePicked->
            val startDate = datePicked.first
            val endDate = datePicked.second
            dateText?.text ?:  "StartDate: $startDate" +"\nEndDate : $endDate"



        }
    }
}
package com.example.social_goal_sharing.ui.main.view.toolbar_fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import java.text.SimpleDateFormat
import java.util.*

class DatePickerFrag : DialogFragment(),DatePickerDialog.OnDateSetListener {
    private val calendar=  Calendar.getInstance()
    private var year = 0
    private var month:Int = 0
    private var day:Int = 0

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
         year = calendar.get(Calendar.YEAR)
         month = calendar.get(Calendar.MONTH)
         day = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireActivity(),this, year, month,day)
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR,year)
        calendar.set(Calendar.MONTH,month)
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)

        val slectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE).format(calendar.time)

        val slectedDateBundle = Bundle()
        slectedDateBundle.putString("SELECTED_DATE",slectedDate)

        setFragmentResult("REQUEST_KEY",slectedDateBundle)
    }


}

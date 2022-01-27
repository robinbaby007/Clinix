package com.emstell.clinix.ui.Adapters

import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.media.ResourceBusyException
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.emstell.clinix.R
import com.emstell.clinix.databinding.ItemClinicResourceBinding
import com.emstell.clinix.databinding.ItemClinicWorkingHourResourceBinding
import com.emstell.clinix.network.models.clinic.Workinghour
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log

class ClinicWorkingHoursAdapter(private val context:Context,private val workingHourList: List<Workinghour>): RecyclerView.Adapter<ClinicWorkingHoursAdapter.ClinicWorkingHoursViewHolder>() {

    class ClinicWorkingHoursViewHolder(val binding: ItemClinicWorkingHourResourceBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClinicWorkingHoursViewHolder {
        return ClinicWorkingHoursViewHolder(ItemClinicWorkingHourResourceBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ClinicWorkingHoursViewHolder, position: Int) {
        val workingHour=workingHourList[position]
        holder.binding.apply {
            val format= SimpleDateFormat("EEEE", Locale.getDefault())
            val dayName=format.format(Date()).toString().trim().lowercase()

            if (dayName == workingHour.name.trim().lowercase()) {
                idTextViewDay.setTextColor(ContextCompat.getColor(context,R.color.lite_blue))
                idTextViewFromTime.setTextColor(ContextCompat.getColor(context,R.color.lite_blue))
                idTextViewToTime.setTextColor(ContextCompat.getColor(context,R.color.lite_blue))
                idTextViewTo.setTextColor(ContextCompat.getColor(context,R.color.lite_blue))
            }
            idTextViewDay.text=workingHour.name
            idTextViewFromTime.text=workingHour.hour.split("-")[0]
            idTextViewToTime.text=workingHour.hour.split("-")[1]
        }
     }

    override fun getItemCount(): Int {
        return if (workingHourList.isEmpty())
            0
        else
            workingHourList.size
    }
}
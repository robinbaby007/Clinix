package com.emstell.clinix.ui.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emstell.clinix.databinding.ItemServiceResourceBinding
import com.emstell.clinix.network.models.home.ServiceInfo
import com.emstell.clinix.network.models.home.ServiceInfoX

class DoctorSpecialityAdapter(val context: Context, private val serviceList: List<ServiceInfoX>):RecyclerView.Adapter<DoctorSpecialityAdapter.DoctorSpecialityViewHolder>(){

class DoctorSpecialityViewHolder(val itemServiceResourceBinding: ItemServiceResourceBinding) : RecyclerView.ViewHolder(itemServiceResourceBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorSpecialityViewHolder {
        return DoctorSpecialityViewHolder(ItemServiceResourceBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DoctorSpecialityViewHolder, position: Int) {
        holder.itemServiceResourceBinding.idTextViewServiceName.text=serviceList[position].name
     }

    override fun getItemCount(): Int {
        return if (serviceList.isEmpty())
            0
        else
            serviceList.size
    }
}
package com.emstell.clinix.ui.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emstell.clinix.databinding.ItemClinicResourceBinding
import com.emstell.clinix.databinding.ItemServiceResourceBinding
import com.emstell.clinix.network.models.home.ServiceInfo

class ClinicServiceAdapter(val context: Context, private val serviceList: List<ServiceInfo>): RecyclerView.Adapter<ClinicServiceAdapter.ClinicServiceViewHolder>() {

    class ClinicServiceViewHolder(val binding: ItemServiceResourceBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClinicServiceViewHolder {
    return ClinicServiceViewHolder(ItemServiceResourceBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ClinicServiceViewHolder, position: Int) {
        holder.binding.idTextViewServiceName.text=serviceList[position].name
     }

    override fun getItemCount(): Int {
        return if (serviceList.isEmpty())
            0
        else
            serviceList.size
     }
}
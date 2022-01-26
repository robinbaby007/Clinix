package com.emstell.clinix.ui.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emstell.clinix.databinding.ItemDoctorResourceBinding
import com.emstell.clinix.network.ApiClient
import com.emstell.clinix.network.Utility
import com.emstell.clinix.network.models.home.Doctor

class DoctorAdapter(private val context: Context, private val doctorList:List<Doctor>): RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    class DoctorViewHolder(val itemDoctorResourceBinding: ItemDoctorResourceBinding) : RecyclerView.ViewHolder(itemDoctorResourceBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        return DoctorViewHolder(ItemDoctorResourceBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctorData=doctorList[position]
        holder.itemDoctorResourceBinding.apply {
            Glide.with(context).apply {
                load(Utility.IMAGE_URL+doctorData.image).into(idImageViewDoctorImage)
                load(Utility.IMAGE_URL+"hospital.png").into(idImageViewBagIcon)
                load(Utility.IMAGE_URL+"half-star.png").into(idImageViewRating)
                load(Utility.IMAGE_URL+"location.png").into(idImageViewLocationIcon)
            }
            idTextViewDoctorName.text=doctorData.name
            idTextViewHospitalName.text=doctorData.clinicname
            idTextViewLocationName.text=doctorData.location
            idRecyclerViewSpeciality.apply {
                layoutManager= GridLayoutManager(context,2)
                adapter=DoctorSpecialityAdapter(context,doctorData.service_info)
            }
        }
     }

    override fun getItemCount(): Int {
        return if (doctorList.isEmpty())
            0
        else
            doctorList.size
     }
}
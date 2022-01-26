package com.emstell.clinix.ui.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emstell.clinix.databinding.ItemClinicResourceBinding
import com.emstell.clinix.network.ApiClient
import com.emstell.clinix.network.ApiInterface
import com.emstell.clinix.network.Utility
import com.emstell.clinix.network.models.home.Clinic
import com.emstell.clinix.ui.Interface.ItemClickInterface

class ClinicAdapter(private val context:Context, private var clinicList:List<Clinic>,private var itemClickInterface: ItemClickInterface): RecyclerView.Adapter<ClinicAdapter.ClinicViewHolder>() {
    class ClinicViewHolder(val binding: ItemClinicResourceBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClinicViewHolder {
        return ClinicViewHolder(
            ItemClinicResourceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ClinicViewHolder, position: Int) {
        val clinicData = clinicList[position]
        holder.binding.apply {

            Glide.with(context).apply {
                load(Utility.IMAGE_URL + clinicData.image).into(idImageViewHospitalImage)
                load(Utility.IMAGE_URL + "location.png").into(idImageViewLocationIcon)
                load(Utility.IMAGE_URL + "time.png").into(idImageViewTimeIcon)
                load(Utility.IMAGE_URL + "half-star.png").into(idImageViewRating)
            }
            idTextViewHospitalName.text = clinicData.name
            idTextViewLocationName.text = clinicData.location
            if (clinicData.hour == "1") {
                idTextViewHoursAvailable.visibility = View.VISIBLE
                idImageViewTimeIcon.visibility = View.VISIBLE
            } else {
                idTextViewHoursAvailable.visibility = View.GONE
                idImageViewTimeIcon.visibility = View.GONE
            }
            idRecyclerViewServices.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = ClinicServiceAdapter(context, clinicData.service_info)
            }

        }
        holder.binding.idButtonViewMore.setOnClickListener {
            itemClickInterface.mClick(
                it,
                position
            )
        }
    }

    override fun getItemCount(): Int {
        return if (clinicList.isEmpty()) 0 else clinicList.size
    }
}

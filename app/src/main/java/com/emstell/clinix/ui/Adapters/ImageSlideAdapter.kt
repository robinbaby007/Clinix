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
import com.emstell.clinix.databinding.ItemImageSlideResourceBinding
import com.emstell.clinix.network.ApiClient
import com.emstell.clinix.network.ApiInterface
import com.emstell.clinix.network.Utility
import com.emstell.clinix.network.models.clinic.ClinicImage
import com.emstell.clinix.network.models.home.Clinic
import com.emstell.clinix.ui.Interface.ItemClickInterface

class ImageSlideAdapter(private val context:Context, private val imageList:List<ClinicImage>): RecyclerView.Adapter<ImageSlideAdapter.ImageSlideViewHolder>() {
    class ImageSlideViewHolder(val binding: ItemImageSlideResourceBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSlideViewHolder {
        return ImageSlideViewHolder(
            ItemImageSlideResourceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageSlideViewHolder, position: Int) {
        val imageData=imageList[position]
         holder.binding.apply {
            Glide.with(context).apply {
                load(Utility.IMAGE_URL + imageData.image).into(idImageViewImageSlide)
            }
        }

    }

    override fun getItemCount(): Int {
        return if (imageList.isEmpty()) 0 else imageList.size
    }
}

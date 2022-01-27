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
import com.emstell.clinix.databinding.ItemCommentsResourceBinding
import com.emstell.clinix.databinding.ItemImageSlideResourceBinding
import com.emstell.clinix.network.ApiClient
import com.emstell.clinix.network.ApiInterface
import com.emstell.clinix.network.Utility
import com.emstell.clinix.network.models.clinic.ClinicImage
import com.emstell.clinix.network.models.clinic.ClinicReview
import com.emstell.clinix.network.models.home.Clinic
import com.emstell.clinix.ui.Interface.ItemClickInterface

class CommentsAdapter(private val context:Context, private val reviewList:List<ClinicReview>): RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {
    class CommentsViewHolder(val binding: ItemCommentsResourceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder(
            ItemCommentsResourceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val imageData=reviewList[position]
         holder.binding.apply {
            Glide.with(context).apply {
                if (imageData.author_image.isNotEmpty())
                     load(Utility.IMAGE_URL + imageData.author_image).circleCrop().into(idImageViewUser)
            }
             idTextName.text=imageData.author
             idTextViewComment.text=imageData.text
             idRatingBar.rating=imageData.rating.toFloat()
        }

    }

    override fun getItemCount(): Int {
        return if (reviewList.isEmpty()) 0
        else reviewList.size
    }
}

package com.ihiviko.dogsapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ihiviko.dogsapi.Model.Local.Entities.DogDetailEntity
import com.ihiviko.dogsapi.databinding.ItemImagesBinding

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.VH>() {
    var listImages = listOf<DogDetailEntity>()
    val selectedImage = MutableLiveData<DogDetailEntity>()

    fun update(list : List<DogDetailEntity>){
        listImages = list
        notifyDataSetChanged()
    }

    fun selectedImage() : LiveData<DogDetailEntity> = selectedImage

    inner class VH(private val binding : ItemImagesBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener{
        fun bind(image : DogDetailEntity) {
            Glide.with(binding.root)
                .load(image.imageUrl)
                .fitCenter()
                .into(binding.imgDog)
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            selectedImage.value = listImages[adapterPosition]
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemImagesBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = listImages.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(listImages[position])
    }
}
package com.ihiviko.dogsapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ihiviko.dogsapi.Model.Local.Entities.DogEntity
import com.ihiviko.dogsapi.databinding.ItemDogBinding

class DogAdapter : RecyclerView.Adapter<DogAdapter.VH>() {
    private var listBreed = listOf<DogEntity>()
    private val selectedBreed = MutableLiveData<DogEntity>()

    fun update(list : List<DogEntity>){
        listBreed =list
        notifyDataSetChanged()
    }

    fun selectedBreed(): LiveData<DogEntity> = selectedBreed

    inner class VH(private val binding: ItemDogBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        fun bind(breed : DogEntity){
            binding.tvBreed.text = breed.breed
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            selectedBreed.value = listBreed[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemDogBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = listBreed.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(listBreed[position])
    }
}
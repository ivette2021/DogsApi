package com.ihiviko.dogsapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ihiviko.dogsapi.Model.Local.Entities.DogEntity
import com.ihiviko.dogsapi.databinding.ItemDogBinding

class DogAdapter(private val context: Context) : RecyclerView.Adapter<DogAdapter.VH>() {
    private var listBreed = listOf<DogEntity>()
    private val selectedBreed = MutableLiveData<DogEntity>()
    private lateinit var onBreedSelectedListener: OnBreedSelectedListener


    fun update(list : List<DogEntity>){
        listBreed =list
        notifyDataSetChanged()
    }

    fun selectedBreed(): LiveData<DogEntity> = selectedBreed

    fun setOnBreedSelectedListener(listener: OnBreedSelectedListener) {
        this.onBreedSelectedListener = listener
    }

    interface OnBreedSelectedListener {
        fun onBreedSelected(breed: DogEntity)
    }

    inner class VH(private val binding: ItemDogBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        fun bind(breed : DogEntity){
            binding.tvBreed.text = breed.breed
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val breed = listBreed[adapterPosition]
            selectedBreed.value = breed
            onBreedSelectedListener.onBreedSelected(breed)
            //selectedBreed.value = listBreed[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun getItemCount(): Int = listBreed.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(listBreed[position])
    }
}
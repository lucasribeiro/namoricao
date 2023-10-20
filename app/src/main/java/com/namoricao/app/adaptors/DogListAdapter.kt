package com.namoricao.app.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.namoricao.app.R
import com.namoricao.app.model.Dog

class DogListAdapter(private val context: Context, private val dogList: List<Dog>) :
    RecyclerView.Adapter<DogListAdapter.DogViewHolder>() {

    inner class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewDog: ImageView = itemView.findViewById(R.id.imageViewDog)
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewBreed: TextView = itemView.findViewById(R.id.textViewBreed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_dog_list, parent, false)
        return DogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val currentDog = dogList[position]

        holder.imageViewDog.setImageResource(currentDog.imageResource)
        holder.textViewName.text = currentDog.name
        holder.textViewBreed.text = currentDog.breed
    }

    override fun getItemCount() = dogList.size
}

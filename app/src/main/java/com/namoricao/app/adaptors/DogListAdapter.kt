package com.namoricao.app.adaptors

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.namoricao.app.R
import com.namoricao.app.interfaces.OnItemClickListener
import com.namoricao.app.model.Dog


private var onItemClickListener: ((Int) -> Unit)? = null
class DogListAdapter(private val context: Context,
                     private val dogList: List<Dog>,
                     private val tela: Screen,
                     private val listener: OnItemClickListener
) :
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

        val dog = dogList[position]

        // Configurar a imagem, nome do cão e informações
        val resourceId = context.resources.getIdentifier(dog.image, "drawable", context.packageName)
        holder.imageViewDog.setImageResource(resourceId)
        holder.textViewName.text = dog.name

        var dogInfo = "${dog.breed}, ${dog.city}"

        if (tela == Screen.MEUS_CAES) {
            dogInfo = dog.breed
        }

        val commaIndex = dogInfo.indexOf(",") // Encontra a posição da vírgula

        if (commaIndex != -1) {
            val spannable = SpannableString(dogInfo)
            spannable.setSpan(StyleSpan(Typeface.BOLD), 0, commaIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            holder.textViewBreed.text = spannable
        } else {
            holder.textViewBreed.text = dogInfo
        }

        holder.itemView.setOnClickListener {
            listener.onItemClick(dog.id)
        }


    }

    override fun getItemCount() = dogList.size

}

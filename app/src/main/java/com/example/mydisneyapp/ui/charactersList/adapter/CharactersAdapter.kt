package com.example.mydisneyapp.ui.charactersList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydisneyapp.R
import com.example.mydisneyapp.domain.model.CharacterModel

class CharactersAdapter(
    private var characters: List<CharacterModel>,
    private val onItemClick: (CharacterModel) -> Unit,
): RecyclerView.Adapter<CharactersViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_characters, parent, false)
        return CharactersViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
        holder.itemView.setOnClickListener{
            onItemClick(character)
        }
    }

    override fun getItemCount(): Int = characters.size

    fun updateList(newList: List<CharacterModel>){
        characters = newList
        notifyDataSetChanged()
    }
}
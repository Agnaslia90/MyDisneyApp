package com.example.mydisneyapp.ui.charactersList.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydisneyapp.R
import com.example.mydisneyapp.domain.model.CharacterModel
import javax.inject.Inject

class CharactersViewHolder @Inject constructor(view: View): RecyclerView.ViewHolder(view) {
    fun bind(character: CharacterModel) {
        itemView.findViewById<TextView>(R.id.characterName).text = character.name
    }
}

package com.example.opendotaapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.opendotaapi.R
import com.example.opendotaapi.model.Hero
import kotlin.random.Random

class HeroAdapter(private val heroList: List<Hero>) :
    RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

    class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val heroImage: ImageView = itemView.findViewById(R.id.img_char)
        val heroName: TextView = itemView.findViewById(R.id.tv_char_name)
        val heroStatus: TextView = itemView.findViewById(R.id.tv_char_status)
        val heroSpecies: TextView = itemView.findViewById(R.id.tv_char_species)
        val heroGender: TextView = itemView.findViewById(R.id.tv_char_gender)
    }
    // Daftar attack type untuk di-random
    private val attackTypes = listOf("Jungler", "Gold", "XP", "Tank", "Support")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hero, parent, false)
        return HeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = heroList[position]

        // Menampilkan data hero
        holder.heroName.text = hero.localizedName
        holder.heroStatus.text = "ID: ${hero.id}"
        holder.heroSpecies.text = "Name: ${hero.name}"

        // Menampilkan attack type secara random
        val randomAttackType = attackTypes[Random.nextInt(attackTypes.size)]
        holder.heroGender.text = "Attack Type: $randomAttackType"

        // Load hero image using Glide
        Glide.with(holder.itemView.context)
            .load(R.drawable.dota2_logo)
            .placeholder(R.color.primary)
            .into(holder.heroImage)
    }

    override fun getItemCount() = heroList.size
}

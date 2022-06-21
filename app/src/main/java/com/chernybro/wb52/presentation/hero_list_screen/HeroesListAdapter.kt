package com.chernybro.wb52.presentation.hero_list_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chernybro.wb52.databinding.ItemHeroBinding
import com.chernybro.wb52.domain.models.HeroItem
import com.squareup.picasso.Picasso

interface HeroClickHandler {
    fun onItemClick(item: HeroItem)
}

class HeroesListAdapter : RecyclerView.Adapter<HeroesListAdapter.HeroListViewHolder>() {
    private val data: MutableList<HeroItem> = ArrayList()
    private var heroClickHandler: HeroClickHandler? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroListViewHolder {
        val binding = ItemHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroListViewHolder(binding, heroClickHandler = heroClickHandler)
    }

    override fun onBindViewHolder(holder: HeroListViewHolder, position: Int) {
        holder.bind(item = data[position])
    }

    fun setData(items: List<HeroItem>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun attachClickHandler(heroClickHandler: HeroClickHandler) {
        this.heroClickHandler = heroClickHandler
    }

    class HeroListViewHolder(
        private val itemHeroBinding: ItemHeroBinding,
        private val heroClickHandler: HeroClickHandler?,
    ) : RecyclerView.ViewHolder(itemHeroBinding.root) {

        fun bind(item: HeroItem) {
            itemHeroBinding.apply {
                tvHeroName.text = item.name
                Picasso.get().load(item.image).fit().centerCrop().into(ivHeroImage)
                this.root.setOnClickListener { heroClickHandler?.onItemClick(item) }
            }
        }
    }
}
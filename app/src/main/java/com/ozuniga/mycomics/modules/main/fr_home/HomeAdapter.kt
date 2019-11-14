package com.ozuniga.mycomics.modules.main.fr_home

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozuniga.mycomics.Application
import com.ozuniga.mycomics.R
import com.ozuniga.mycomics.databinding.ItemHomeBinding
import com.ozuniga.mycomics.helpers.activity_helper.ActivityHelper
import com.ozuniga.mycomics.net.ComicData
import com.ozuniga.mycomics.utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*
import kotlin.math.abs

class HomeAdapter(private val list: MutableList<ComicData>, private val listener: HomeContracts.OnComicClickListener,
                  private val activity: ActivityHelper) : RecyclerView.Adapter<ViewHolderHome>() {

    private var minimumHeight: Int = 0
    private var minimumWidth: Int = 0

    init {
        if (Utils.isTablet()) {
            if (activity.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                minimumHeight = abs(Application.getContext().resources.displayMetrics.heightPixels / 2)
                minimumWidth = abs(Application.getContext().resources.displayMetrics.widthPixels / 3)
            } else {
                minimumHeight = abs(Application.getContext().resources.displayMetrics.heightPixels / 3) + 400
                minimumWidth = abs(Application.getContext().resources.displayMetrics.widthPixels / 5)
            }
        } else {
            minimumHeight = abs(Application.getContext().resources.displayMetrics.heightPixels / 1) - 400
            minimumWidth = abs(Application.getContext().resources.displayMetrics.widthPixels / 1)
        }
    }

    override fun getItemId(position: Int): Long {
        return list[position].digitalId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHome {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeBinding.inflate(inflater, parent, false)
        return ViewHolderHome(binding.root)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderHome, position: Int) {
        holder.itemView.txt_comic_title.text = list[position].title
        val thumbnail = list[position].thumbnail
        Picasso.get().load("${thumbnail.path}/portrait_incredible.${thumbnail.extension}").resize(minimumWidth, minimumHeight)
                .error(R.drawable.ic_launcher_background).into(holder.itemView.img_comic)
        holder.itemView.cdv_item_home.layoutParams = ViewGroup.LayoutParams(minimumWidth, minimumHeight)
        holder.itemView.cdv_item_home.setOnClickListener { listener.onComicClick(holder.itemView, position) }
    }
}

class ViewHolderHome(itemView: View) : RecyclerView.ViewHolder(itemView)
package com.lyl.buygame.ui.game

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lyl.buygame.R
import com.lyl.buygame.network.entity.Game
import com.lyl.buygame.utils.ImgUtils

/**
 * Author: lyl
 * Date Created : 2019/4/13.
 */
class GameListAdapter(context: Context) : RecyclerView.Adapter<GameListAdapter.ViewHolder>() {

    private var mContext: Context = context
    private var dataList: ArrayList<Game> = ArrayList()

    fun updateData(data: List<Game>) {
        dataList = data as ArrayList<Game>
        notifyDataSetChanged()
    }

    fun clearData(){
        dataList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_game, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = dataList.get(position)

        ImgUtils.load(mContext, game.image, holder.game_image)
        holder.game_title.text = game.name
        holder.game_price.text = mContext.resources.getString(R.string.price_text, game.price / 100F)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var game_image: ImageView = itemView.findViewById(R.id.item_game_image)
        var game_title: TextView = itemView.findViewById(R.id.item_game_title)
        var game_price: TextView = itemView.findViewById(R.id.item_game_price)
    }
}
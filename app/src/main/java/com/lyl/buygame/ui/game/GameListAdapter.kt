package com.lyl.buygame.ui.game

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.lyl.buygame.R
import com.lyl.buygame.network.entity.Game
import com.lyl.buygame.ui.base.OnItemClickListener
import com.lyl.buygame.utils.ImgUtils

/**
 * Author: lyl
 * Date Created : 2019/4/13.
 */
class GameListAdapter(context: Context) : RecyclerView.Adapter<GameListAdapter.ViewHolder>() {

    private var mContext: Context = context
    private var mDataList: ArrayList<Game> = ArrayList()
    private var mOnItemClickListener: OnItemClickListener? = null

    fun getDatas() : List<Game> {
        return mDataList
    }

    fun updateData(data: List<Game>) {
        mDataList = data as ArrayList<Game>
        notifyDataSetChanged()
    }

    fun clearData() {
        mDataList.clear()
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mOnItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_game, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = mDataList.get(position)

        ImgUtils.loadCache(mContext, game.image, holder.game_image)
        holder.game_title.text = game.name
        holder.game_price.text = mContext.resources.getString(R.string.game_price_text, game.price / 100F)

        // 如果有监听点击事件，再设置
        if (mOnItemClickListener != null) {
            holder.game_layout.setOnClickListener { mOnItemClickListener!!.onItemClick(holder.game_layout, position) }
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var game_layout: CardView = itemView.findViewById(R.id.item_game_layout)
        var game_image: ImageView = itemView.findViewById(R.id.item_game_image)
        var game_title: TextView = itemView.findViewById(R.id.item_game_title)
        var game_price: TextView = itemView.findViewById(R.id.item_game_price)
    }
}
package com.lyl.buygame.ui.game.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lyl.buygame.R
import com.lyl.buygame.network.entity.Comment
import com.lyl.buygame.utils.ImgUtils

/**
 * Author: lyl
 * Date Created : 2019/4/13.
 */
class GameDetailCommentAdapter(context: Context) : RecyclerView.Adapter<GameDetailCommentAdapter.ViewHolder>() {

    private var mContext: Context = context
    private var mDataList: ArrayList<Comment> = ArrayList()

    fun getDatas() : List<Comment> {
        return mDataList
    }

    fun updateData(data: List<Comment>) {
        mDataList = data as ArrayList<Comment>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_game_comment, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = mDataList.get(position)

        ImgUtils.loadCircle(mContext, comment.avatar, holder.comment_avatar)
        holder.comment_name.text = comment.name
        holder.comment_content.text = comment.comment
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var comment_layout: LinearLayout = itemView.findViewById(R.id.item_game_comment_layout)
        var comment_avatar: ImageView = itemView.findViewById(R.id.item_game_comment_avatar)
        var comment_name: TextView = itemView.findViewById(R.id.item_game_comment_name)
        var comment_content: TextView = itemView.findViewById(R.id.item_game_comment_content)
    }
}
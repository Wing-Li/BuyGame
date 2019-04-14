package com.lyl.buygame.network.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Author: lyl
 * Date Created : 2019/4/13.
 */

/**
 * 游戏信息
 */
@Parcelize
data class Game(
    var objectId: String,
    var name: String?,
    var image: String?,
    var price: Long,
    var gameId: Long,
    var createdAt: String,
    var updatedAt: String
) : Parcelable

/**
 * 评论信息
 */
data class Comment(
    var objectId: String,
    var name: String?,
    var avatar: String?,
    var comment: String?,
    var gameId: Long,
    var createdAt: String,
    var updatedAt: String
)
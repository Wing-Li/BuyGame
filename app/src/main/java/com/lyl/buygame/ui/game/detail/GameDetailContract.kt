package com.lyl.buygame.ui.game.detail

import com.lyl.buygame.network.entity.Comment
import com.lyl.buygame.ui.base.BasePresenter
import com.lyl.buygame.ui.base.BaseView

/**
 * Author: lyl
 * Date Created : 2019/4/14.
 */
interface GameDetailContract {

    interface View : BaseView<Presenter> {

        /**
         * 显示加载圈
         */
        fun showLoading()

        /**
         * 隐藏加载圈
         */
        fun hideLoading()

        /**
         * 更新列表
         */
        fun updateList(commentList: List<Comment>)

        /**
         * 加载错误提示
         */
        fun loadError(msg: String)

    }

    interface Presenter : BasePresenter {

        /**
         * 初始化数据
         */
        fun initData(gameId: Long)

        /**
         * 加载更多数据
         */
        fun loadMore(gameId: Long, start: Int, limit: Int)
    }

}

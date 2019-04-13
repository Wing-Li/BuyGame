package com.lyl.buygame.ui.game

import com.lyl.buygame.network.entity.Game
import com.lyl.buygame.ui.base.BasePresenter
import com.lyl.buygame.ui.base.BaseView

/**
 * Author: lyl
 * Date Created : 2019/4/14.
 */
interface GameContract {

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
        fun updateList(gameList: List<Game>)

        /**
         * 加载错误提示
         */
        fun loadError(msg: String)

    }

    interface Presenter : BasePresenter {
    }

}

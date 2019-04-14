package com.lyl.buygame.ui.game.detail

import com.lyl.buygame.network.entity.Comment
import com.lyl.buygame.network.parse.ParseImp

/**
 * Author: lyl
 * Date Created : 2019/4/14.
 */
class GameDetailPresenter(view: GameDetailContract.View) : GameDetailContract.Presenter {

    var mGameDetailView: GameDetailContract.View = view

    /**
     * 初始化数据
     */
    override fun initData(gameId: Long) {
        mGameDetailView.showLoading()

        val parse = ParseImp()
        parse.getGameComments(gameId, object :ParseImp.NetWorkCallBack<List<Comment>> {
            override fun onSuccess(obj: List<Comment>?) {
                mGameDetailView.hideLoading()

                mGameDetailView.updateList(obj!!)
            }

            override fun onFail(code: Int, msg: String?) {
                mGameDetailView.hideLoading()

                mGameDetailView.loadError(msg!!)
            }

        })
    }

    override fun loadMore(gameId: Long, start: Int, limit: Int) {
    }

    override fun start() {
    }

}

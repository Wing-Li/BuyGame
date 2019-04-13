package com.lyl.buygame.ui.game

import com.lyl.buygame.network.entity.Game
import com.lyl.buygame.network.parse.ParseImp

/**
 * Author: lyl
 * Date Created : 2019/4/14.
 */
class GamePresenter(view: GameContract.View) : GameContract.Presenter {

    var mGameView: GameContract.View = view


    override fun start() {
        mGameView.showLoading()

        val parse = ParseImp()
        parse.getGameList(object : ParseImp.NetWorkCallBack<List<Game>> {
            override fun onSuccess(obj: List<Game>?) {
                mGameView.hideLoading()

                mGameView.updateList(obj!!)
            }

            override fun onFail(code: Int, msg: String?) {
                mGameView.hideLoading()

                mGameView.loadError(msg!!)
            }
        })
    }

}

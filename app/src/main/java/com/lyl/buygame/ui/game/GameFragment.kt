package com.lyl.buygame.ui.game

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lyl.buygame.R
import com.lyl.buygame.network.entity.Game
import com.lyl.buygame.ui.base.BaseFragment
import com.lyl.buygame.ui.base.OnItemClickListener
import com.lyl.buygame.ui.game.detail.GameDetailActivity
import kotlinx.android.synthetic.main.fragment_game.*

/**
 * Author: lyl
 * Date Created : 2019/4/13.
 */
class GameFragment : BaseFragment(), GameContract.View {

    private lateinit var mGameAdapter: GameListAdapter
    private var mGamePresenter: GamePresenter = GamePresenter(this)

    override fun getLayoutResource(): Int {
        return R.layout.fragment_game
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        initListener()
        mGamePresenter.start()
    }

    /**
     * 初始化布局
     */
    private fun initView() {
        mGameAdapter = GameListAdapter(getmActivity())
        gameListView.layoutManager = LinearLayoutManager(getmActivity())
        gameListView.adapter = mGameAdapter
    }

    /**
     * 初始化监听事件
     */
    private fun initListener() {
        // 下拉刷新
        swipeRefreshLayout.setOnRefreshListener {
            mGameAdapter.clearData()
            mGamePresenter.start()
        }

        // 列表的点击事件
        mGameAdapter.setOnItemClickListener(
            listener = OnItemClickListener { view, pos ->
                run {
                    val game = mGameAdapter.getDatas().get(pos)

                    val intent = Intent(getmActivity(), GameDetailActivity::class.java)
                    intent.putExtra(GameDetailActivity.Intent.GAME, game)
                    skipActivity(intent, true)
                }
            })
    }

    /**
     * 显示加载圈
     */
    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    /**
     * 隐藏加载圈
     */
    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    /**
     * 更新列表数据
     */
    override fun updateList(gameList: List<Game>) {
        mGameAdapter.updateData(gameList)
    }

    /**
     * 请求数据出错的提示
     */
    override fun loadError(msg: String) {
        showToast(msg)
    }

    override fun setPresenter(presenter: GameContract.Presenter?) {
        mGamePresenter = presenter as GamePresenter
    }
}
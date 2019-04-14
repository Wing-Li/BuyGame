package com.lyl.buygame.ui.game.detail

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lyl.buygame.R
import com.lyl.buygame.network.entity.Comment
import com.lyl.buygame.network.entity.Game
import com.lyl.buygame.ui.base.BaseActivity
import com.lyl.buygame.utils.ImgUtils
import kotlinx.android.synthetic.main.activity_game_detail.*

/**
 * Author: lyl
 * Date Created : 2019/4/14.
 */
class GameDetailActivity : BaseActivity(), GameDetailContract.View {

    companion object Intent {
        const val GAME = "game"
    }

    private lateinit var mGameDetailCommentAdapter: GameDetailCommentAdapter
    private lateinit var mGame: Game

    private var mGameDetailPresenter: GameDetailPresenter = GameDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)

        initData()
        initView()
        initListener()

        mGameDetailPresenter.initData(mGame.gameId)
    }

    private fun initData() {
        mGame = intent.getParcelableExtra(GAME)
    }

    private fun initView() {
        // 设置标题栏
        gameDetailCollapsingBar.title = mGame.name
        gameDetailCollapsingBar.setExpandedTitleColor(Color.WHITE)
        gameDetailCollapsingBar.setCollapsedTitleTextColor(Color.WHITE)

        // 加载顶部图片
        ImgUtils.loadCache(mContext, mGame.image, gameDetailTopImage)

        // 设置评论列表
        mGameDetailCommentAdapter = GameDetailCommentAdapter(mContext)
        gameDetailListView.layoutManager = LinearLayoutManager(mContext)
        gameDetailListView.addItemDecoration(DividerItemDecoration(mContext, LinearLayout.VERTICAL))
        gameDetailListView.adapter = mGameDetailCommentAdapter
    }

    private fun initListener() {
        gameDetailToolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun hideLoading() {
        hideLoadingDialog()
    }

    override fun updateList(commentList: List<Comment>) {
        mGameDetailCommentAdapter.updateData(commentList)
    }

    override fun loadError(msg: String) {
        showToast(msg)
    }

    override fun setPresenter(presenter: GameDetailContract.Presenter?) {
        mGameDetailPresenter = presenter as GameDetailPresenter
    }
}
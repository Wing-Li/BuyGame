package com.lyl.buygame.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lyl.buygame.R
import com.lyl.buygame.ui.base.BaseActivity
import com.lyl.buygame.ui.card.CardFragment
import com.lyl.buygame.ui.func.FuncFragment
import com.lyl.buygame.ui.game.GameFragment
import com.lyl.buygame.ui.user.UserFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {


    private var gameFragment: GameFragment? = null
    private var funcFragment: FuncFragment? = null
    private var cardFragment: CardFragment? = null
    private var userFragment: UserFragment? = null

    private var oldFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lyl.buygame.R.layout.activity_main)

        initView()
        initMainFragment()
        initListener()
    }

    /**
     * 设置 View
     */
    private fun initView() {
        mainBottomBar.enableAnimation(false)
        mainBottomBar.enableShiftingMode(false)
        mainBottomBar.enableItemShiftingMode(false)
        mainBottomBar.setTextVisibility(true)
    }

    /**
     * 设置主页面的布局
     */
    private fun initMainFragment(){
        gameFragment = GameFragment()
        supportFragmentManager.beginTransaction().add(R.id.contentFrameLayout, gameFragment!!).commit()
        oldFragment = gameFragment
    }

    /**
     * 设置监听
     */
    private fun initListener() {
        mainBottomBar.onNavigationItemSelectedListener =
            object : BottomNavigationView.OnNavigationItemSelectedListener {
                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    val itemId = item.getItemId()
                    when (itemId) {

                        // 游戏按钮
                        R.id.menu_game -> {
                            if (gameFragment == null) {
                                gameFragment = GameFragment()
                            }

                            toFragment(gameFragment!!)
                        }

                        // 功能
                        R.id.menu_func -> {
                            if (funcFragment == null) {
                                funcFragment = FuncFragment()
                            }

                            toFragment(funcFragment!!)
                        }

                        // 银行卡
                        R.id.menu_card -> {
                            if (cardFragment == null) {
                                cardFragment = CardFragment()
                            }

                            toFragment(cardFragment!!)
                        }

                        // 用户
                        R.id.menu_user -> {
                            if (userFragment == null) {
                                userFragment = UserFragment()
                            }

                            toFragment(userFragment!!)
                        }

                    }
                    return true
                }
            }
    }

    /**
     * 切换Fragment
     *
     * @param to 下一个Fragment页面
     */
    private fun toFragment(to: Fragment) {
        if (to === oldFragment) return

        val transaction = supportFragmentManager.beginTransaction()
        // 先判断是否被add过
        if (!to.isAdded) {
            transaction.hide(oldFragment!!).add(R.id.contentFrameLayout, to).commit() // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction.hide(oldFragment!!).show(to).commit() // 隐藏当前的fragment，显示下一个
        }
        oldFragment = to
    }


}

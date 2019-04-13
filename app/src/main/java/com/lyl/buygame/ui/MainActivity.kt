package com.lyl.buygame.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lyl.buygame.R
import com.lyl.buygame.network.entity.Comment
import com.lyl.buygame.network.entity.Game
import com.lyl.buygame.network.parse.ParseImp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game.setOnClickListener {
            val imp = ParseImp()

            imp.getGameList(object : ParseImp.NetWorkCallBack<List<Game>> {
                override fun onSuccess(obj: List<Game>) {
                    System.out.print(obj.toString())
                }

                override fun onFail(code: Int, msg: String) {

                }
            })
        }

        comment.setOnClickListener {
            val imp = ParseImp()

            imp.getGameComments(1, object : ParseImp.NetWorkCallBack<List<Comment>>{
                override fun onSuccess(obj: List<Comment>?) {
                    System.out.print(obj.toString())
                }

                override fun onFail(code: Int, msg: String?) {

                }
            })
        }
    }


}

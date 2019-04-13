package com.lyl.buygame

import android.app.Application
import com.lyl.buygame.config.Constants
import com.parse.Parse

/**
 * Author: lyl
 * Date Created : 2019/4/13.
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initParse()
    }

    /**
     * 初始化 Parse 服务
     * 地址：https://parse-dashboard.back4app.com/apps/487b391a-fc75-4bec-9574-3c0e7bb16ffa/server-settings
     */
    private fun initParse() {
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(Constants.PARSE_APP_ID)
                .clientKey(Constants.PARSE_CLIENT_KEY)
                .server(Constants.PARSE_SERVER_URL)
                .build()
        )
    }
}
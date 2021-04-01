package com.wsg.xsybbs.flutter

import android.os.Bundle
import cn.bmob.v3.BmobUser
import com.wsg.xsybbs.bean.User
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

/**
 * Create by wangshengguo on 2021/3/25.
 */
class MomentDetailActivity : FlutterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
//        flutterEngine.let {
//            GeneratedPluginRegistrant.registerWith(it)
//        }

        MethodChannel(
            flutterEngine.dartExecutor,
            MomentBridge.BRIDGE_NAME
        ).setMethodCallHandler { call, result ->
            when (call.method) {
                MomentBridge.METHOD_GET_USER_INFO -> {
                    val user = BmobUser.getCurrentUser(User::class.java)

                    val map: HashMap<String, String> = hashMapOf()
                    map[MomentBridge.KEY_USER_ID] = user.objectId
                    map[MomentBridge.KEY_USER_NAME] = user.username
                    result.success(map)
                }
                else -> {

                }
            }
        }
    }
}
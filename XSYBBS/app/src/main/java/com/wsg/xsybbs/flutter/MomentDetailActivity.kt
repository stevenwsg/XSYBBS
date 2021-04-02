package com.wsg.xsybbs.flutter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import cn.bmob.v3.BmobUser
import com.wsg.xsybbs.bean.User
import com.wsg.xsybbs.module.persiondetail.view.PersionalDealActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

/**
 * Create by wangshengguo on 2021/3/25.
 */
class MomentDetailActivity : FlutterActivity() {

    companion object {
        const val TAG = "MomentDetailActivity"
    }

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
                MomentBridge.METHOD_JUMP_USER_PROFILE -> {
                    val uid = call.argument<String>(MomentBridge.KEY_USER_ID)
                    val intent = Intent(this, PersionalDealActivity::class.java)
                    intent.putExtra("id", uid)
                    startActivity(intent)

                    Log.i(TAG, "flutter jump to user profile page. uid is $uid")
                }
                else -> {

                }
            }
        }
    }
}
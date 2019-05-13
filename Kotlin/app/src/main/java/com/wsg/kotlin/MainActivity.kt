package com.wsg.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wsg.kotlin.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

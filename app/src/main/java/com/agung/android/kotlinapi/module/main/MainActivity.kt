package com.agung.android.kotlinapi.module.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.agung.android.kotlinapi.R
import com.agung.android.kotlinapi.module.base.BaseActivity
import com.agung.android.kotlinapi.utils.extensions.switchFragment

class MainActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBarTitle(getString(R.string.app_name))
        showActionBarHomeButton(false)

        if (savedInstanceState == null){
        }
    }
}

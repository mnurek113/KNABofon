package com.code.knab.knabofon.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.code.knab.knabofon.R
import com.code.knab.knabofon.ui.main.mvp.MainMVP

class MainActivity : AppCompatActivity(), MainMVP.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

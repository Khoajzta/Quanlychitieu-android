package com.example.quanlychitieu

import android.app.Application
import android.util.Log
import androidx.emoji2.bundled.BundledEmojiCompatConfig
import androidx.emoji2.text.EmojiCompat


class App : Application(){
    override fun onCreate() {
        super.onCreate()
        EmojiCompat.init(BundledEmojiCompatConfig(this))
        Log.d("App", "onCreate")
    }
}

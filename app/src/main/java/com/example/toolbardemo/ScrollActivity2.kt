package com.example.toolbardemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.toolbardemo.databinding.ActivityScroll2Binding
import com.google.android.material.appbar.AppBarLayout

class ScrollActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityScroll2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScroll2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolBarHeight = resources.getDimension(R.dimen.tool_bar_height)
        val appBarInitHeight = resources.getDimension(R.dimen.app_bar_height)
        
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            binding.toolBar.alpha = -verticalOffset / (appBarInitHeight - toolBarHeight)
        })
    }
}
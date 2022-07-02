package com.example.toolbardemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.toolbardemo.databinding.ActivityScroll1Binding
import com.google.android.material.appbar.AppBarLayout

class ScrollActivity1 : AppCompatActivity() {

    private lateinit var binding: ActivityScroll1Binding
    private var selfHeight = 0
    private var nameScaleX = 0F
    private var nameScaleY = 0F
    private var followScaleX = 0F
    private var followScaleY = 0F
    private var iconScaleY = 0F
    private var iconScaleX = 0F
    private val margin = 72

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScroll1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolBar
        val appBar = binding.appBar
        val ivHead = binding.ivHead
        val tvName = binding.tvName
        val tvFollow = binding.tvFollow

        val screenWidth = resources.displayMetrics.widthPixels
        val toolBarHeight = resources.getDimension(R.dimen.tool_bar_height)
        val appBarInitHeight = resources.getDimension(R.dimen.app_bar_height)

        /**
         * 基本原理：根据已经位移的距离占总距离的比例控制动画的进度，即 组件当前的动画 / 目的地动画 = AppBar 当前折叠的高度 / 总高度
         */
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->

            val rate = verticalOffset / (appBarInitHeight - toolBarHeight)

            if (selfHeight == 0) {
                selfHeight = tvName.height
                val distanceNameY = tvName.top - (toolBarHeight - tvName.height) / 2.0F
                val distanceFollowY =
                    tvFollow.y - ((toolBarHeight - ivHead.height) / 2.0F + tvName.height)
                val distanceFollowX = (screenWidth - tvName.width) / 2.0F - margin
                val distanceIconY = ivHead.y - (toolBarHeight - ivHead.height) / 2.0F
                val distanceIconX = tvName.width / 2.0F + margin

                nameScaleY = distanceNameY
                followScaleX = distanceFollowX
                followScaleY = distanceFollowY
                iconScaleY = distanceIconY
                iconScaleX = distanceIconX
            }

            toolbar.alpha = -rate

            ivHead.apply {
                scaleX = 1 - (-verticalOffset) / (appBarInitHeight - toolBarHeight) * 0.6F
                scaleY = 1 - (-verticalOffset) / (appBarInitHeight - toolBarHeight) * 0.6F
                translationX = iconScaleX * rate
                translationY = iconScaleY * rate
            }

            tvName.apply {
                translationY = nameScaleY * rate
            }

            tvFollow.apply {
                translationX = -followScaleX * rate
                translationY = followScaleY * rate
            }
        })
    }
}
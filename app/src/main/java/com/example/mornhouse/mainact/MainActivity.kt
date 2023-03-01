package com.example.mornhouse.mainact

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mornhouse.R
import com.example.mornhouse.fragments.ui.SecondFragment
import com.example.mornhouse.fragments.model.ClickerAdapter
import com.example.mornhouse.fragments.ui.MainPointFragment
import com.example.mornhouse.room.Point

class MainActivity : AppCompatActivity(), ClickerAdapter {

    companion object{
        const val POINT_BUNDLE = "keypoint"
    }
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val mainPointFragment = MainPointFragment()
        Router().openFragment(this,mainPointFragment)
    }

    override fun onclick(point: Point) {
        val bundle = Bundle()
        bundle.putSerializable(POINT_BUNDLE,point)
        Router().openFragmentChosen(this, SecondFragment::class.java,bundle)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()

    }


}
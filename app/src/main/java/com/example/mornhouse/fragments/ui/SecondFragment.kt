package com.example.mornhouse.fragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mornhouse.R
import com.example.mornhouse.mainact.MainActivity.Companion.POINT_BUNDLE
import com.example.mornhouse.room.Point


class SecondFragment : Fragment() {


    private lateinit var secondPointDescription: TextView
    private lateinit var piont: Point

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            piont = requireArguments().getSerializable(POINT_BUNDLE) as Point
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        secondPointDescription = view.findViewById(R.id.secondPointDescription)
        secondPointDescription.text = piont.pointFacts
    }
}
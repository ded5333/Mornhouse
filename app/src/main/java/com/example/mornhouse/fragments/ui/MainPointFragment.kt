package com.example.mornhouse.fragments.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.mornhouse.*
import com.example.mornhouse.fragments.model.InputFilterMinMax
import com.example.mornhouse.fragments.model.PointAdapter
import com.example.mornhouse.fragments.model.PointVW
import com.example.mornhouse.mainact.App
import com.example.mornhouse.mainact.MainActivity
import com.example.mornhouse.room.Point
import com.example.mornhouse.room.PointDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainPointFragment : Fragment() {

    private var pointVW: PointVW? = null
    private lateinit var editPoint: EditText
    private lateinit var btnGetFact: Button
    private lateinit var btnGetRandomFact: Button
    private lateinit var txtFacts: TextView
    private val db = App.database
    private val pointDAO: PointDAO? = db?.pointDAO()
    lateinit var pointAdapter: PointAdapter
    lateinit var rvHistory: RecyclerView
    var pointList = listOf<Point>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pointVW = ViewModelProvider(this)[PointVW::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_main_point, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editPoint = view.findViewById(R.id.editPoint)
        btnGetFact = view.findViewById(R.id.btnGetFact)
        btnGetRandomFact = view.findViewById(R.id.btnGetRandomFact)
        txtFacts = view.findViewById(R.id.txtFacts)
        rvHistory = view.findViewById(R.id.rvHistory)

        lifecycleScope.launch(Dispatchers.IO) {
            pointList = pointDAO?.loadPoint()!!
            pointList = pointDAO.loadPoint() as MutableList<Point>
            getlist()
        }

        editPoint.filters = arrayOf<InputFilter>(InputFilterMinMax(1, 10000))




        pointVW?.pointtMutableEmplLiveData?.observe(viewLifecycleOwner) {
            txtFacts.text = it.pointFacts
            lifecycleScope.launch(Dispatchers.IO) {
                pointDAO?.insertPointHistory(it)
                pointList = pointDAO?.loadPoint() as MutableList<Point>
            }
            pointAdapter = PointAdapter(pointList, activity as MainActivity)
            rvHistory.adapter = pointAdapter
            pointAdapter.notifyDataSetChanged()
        }

        btnGetFact.setOnClickListener {
            val pointChose = editPoint.text.toString()
            if (pointChose.isEmpty()) {
                txtFacts.text = getString(R.string.empty)
                return@setOnClickListener
            } else {
                pointVW?.getPoint(pointChose, false)
            }

        }
        btnGetRandomFact.setOnClickListener {
            pointVW?.getPoint("", true)

        }
    }

    private fun getlist() {
        lifecycleScope.launch(Dispatchers.Main) {
            pointAdapter = PointAdapter(pointList, activity as MainActivity)
            rvHistory.adapter = pointAdapter
        }

    }

}

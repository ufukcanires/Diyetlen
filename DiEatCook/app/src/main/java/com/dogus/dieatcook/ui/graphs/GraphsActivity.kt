package com.dogus.dieatcook.ui.graphs

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.androidplot.xy.*
import com.dogus.dieatcook.R
import com.dogus.dieatcook.database.DataBaseHelper
import com.dogus.dieatcook.database.Kullanici
import com.dogus.dieatcook.database.ProfilDataBaseHelper
import com.dogus.dieatcook.databinding.ActivityGraphsBinding
import com.dogus.dieatcook.databinding.ActivityGuestBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_graphs.*
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.*
import kotlin.collections.ArrayList

class GraphsActivity : AppCompatActivity() {

    private var binding : ActivityGraphsBinding? = null
    private var list1 : MutableList<Kullanici>?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_graphs)
        setupBarChartData1()

    }

    private fun setupBarChartData1() {

        val db = ProfilDataBaseHelper(this)
        list1 = db.readedData()



        // create BarEntry for Bar Group
        val barEntry = arrayListOf<BarEntry>()
        val labelModel = ArrayList<String>()

        list1?.forEachIndexed { index, dataItem ->
            barEntry.add(BarEntry(index.toFloat(), dataItem.bodyMass))
            labelModel.add(dataItem.tarih.toString())
        }

        val barDataSet = BarDataSet(barEntry,"")
        barDataSet.valueTextSize = 13F
        barDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)

        val barData = BarData(barDataSet)
        binding?.barChart1?.animateY(1500)
        binding?.barChart1?.setFitBars(true)
        binding?.barChart1?.data = barData
        binding?.barChart1?.xAxis?.setAvoidFirstLastClipping(false)
        binding?.barChart1?.xAxis?.granularity = 1f
        binding?.barChart1?.xAxis?.isGranularityEnabled = true
        binding?.barChart1?.xAxis?.setCenterAxisLabels(false)
        binding?.barChart1?.xAxis?.labelRotationAngle = -45f
        binding?.barChart1?.description?.text = "VÜCUT KİTLE ENDEKSİ GRAFİĞİ"
        binding?.barChart1?.xAxis?.labelCount = list1?.size ?:0
        binding?.barChart1?.xAxis?.valueFormatter = IndexAxisValueFormatter(labelModel)
        binding?.barChart1?.xAxis?.position = XAxis.XAxisPosition.BOTTOM

    }

}

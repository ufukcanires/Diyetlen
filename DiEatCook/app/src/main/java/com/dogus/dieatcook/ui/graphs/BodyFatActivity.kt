package com.dogus.dieatcook.ui.graphs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dogus.dieatcook.R
import com.dogus.dieatcook.database.Kullanici
import com.dogus.dieatcook.database.ProfilDataBaseHelper
import com.dogus.dieatcook.databinding.ActivityBodyFatBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class BodyFatActivity : AppCompatActivity() {

    private var binding : ActivityBodyFatBinding?=null
    private var listResult : MutableList<Kullanici>?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_body_fat)
        setupBarChartData1()
    }

    private fun setupBarChartData1() {

        val dbList = ProfilDataBaseHelper(this)
        listResult = dbList.readedData()

        // create BarEntry for Bar Group
        val barEntry = arrayListOf<BarEntry>()
        val labelModel = ArrayList<String>()

        listResult?.forEachIndexed { index, dataItem ->
            barEntry.add(BarEntry(index.toFloat(), dataItem?.bodyFat))
            labelModel.add(dataItem?.tarih.toString())
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
        binding?.barChart1?.description?.text = "VÜCUT YAĞ ORANI GRAFİĞİ"
        binding?.barChart1?.xAxis?.labelCount = listResult?.size ?:0
        binding?.barChart1?.xAxis?.valueFormatter = IndexAxisValueFormatter(labelModel)
        binding?.barChart1?.xAxis?.position = XAxis.XAxisPosition.BOTTOM

    }

}
package com.example.sheduller.presentation.CalendarAction

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheduller.R
import com.example.sheduller.databinding.ActivityItemCalendarBinding
import com.example.sheduller.presentation.EventsDay.EventsDay
import com.example.sheduller.presentation.EventsDay.EventsViewModel
import com.example.sheduller.presentation.MainActivity
import com.example.sheduller.presentation.ScreenApp
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemCalendarActivity : AppCompatActivity() {

    private var binding: ActivityItemCalendarBinding? = null
    private var monthAdapter: MonthAdapter? = null
    private var monthes: ArrayList<MonthModel>? = null


    private var daysJanuaru24: ArrayList<DayModel>? = null
    private var daysFebruary24: ArrayList<DayModel>? = null
    private var daysMarch24: ArrayList<DayModel>? = null
    private var daysApril24: ArrayList<DayModel>? = null
    private var daysMay24: ArrayList<DayModel>? = null
    private var daysJune24: ArrayList<DayModel>? = null
    private var daysJuly24: ArrayList<DayModel>? = null
    private var daysAugust24: ArrayList<DayModel>? = null
    private var daysSeptember24: ArrayList<DayModel>? = null
    private var daysOctober24: ArrayList<DayModel>? = null
    private var daysNovember24: ArrayList<DayModel>? = null
    private var daysDecember24: ArrayList<DayModel>? = null

    val eventsViewModel: EventsViewModel by viewModel()

    var groupId:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemCalendarBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.ItemCalndrRV?.text = intent.getStringExtra("nameCalendar")
        groupId = intent.getStringExtra("groupId")


        binding?.arrowBackItemCalndrRV?.setOnClickListener{

            val intent = Intent(this@ItemCalendarActivity, ScreenApp::class.java)
            startActivity(intent)
            finish()
        }

        //_________________________Napolnenie_calendarei____________________________
        monthes = ArrayList<MonthModel>()


        daysJanuaru24 = ArrayList<DayModel>()
        daysFebruary24 = ArrayList<DayModel>()
        daysMarch24 = ArrayList<DayModel>()
        daysApril24 = ArrayList<DayModel>()
        daysMay24 = ArrayList<DayModel>()
        daysJune24 = ArrayList<DayModel>()
        daysJuly24 = ArrayList<DayModel>()
        daysAugust24 = ArrayList<DayModel>()
        daysSeptember24 = ArrayList<DayModel>()
        daysOctober24 = ArrayList<DayModel>()
        daysNovember24 = ArrayList<DayModel>()
        daysDecember24 = ArrayList<DayModel>()

        monthes?.add(MonthModel("Январь", "2024", daysJanuaru24!!))
        monthes?.add(MonthModel("Февраль", "2024", daysFebruary24!!))
        monthes?.add(MonthModel("Март", "2024", daysMarch24!!))
        monthes?.add(MonthModel("Апрель", "2024", daysApril24!!))
        monthes?.add(MonthModel("Май", "2024", daysMay24!!))
        monthes?.add(MonthModel("Июнь", "2024", daysJune24!!))
        monthes?.add(MonthModel("Июль", "2024", daysJuly24!!))
        monthes?.add(MonthModel("Август", "2024", daysAugust24!!))
        monthes?.add(MonthModel("Сентябрь", "2024", daysSeptember24!!))
        monthes?.add(MonthModel("Октябрь", "2024", daysOctober24!!))
        monthes?.add(MonthModel("Ноябрь", "2024", daysNovember24!!))
        monthes?.add(MonthModel("Декабрь", "2024", daysDecember24!!))

//        for (item in 1..6) {
//            daysJanuaru24?.add(DayModel("0",1,2024))
//        }
        for (item in 1..31) {
            daysJanuaru24?.add(DayModel(item.toString(),10,2023))
        }

        for (item in 1..3) {
            daysFebruary24?.add(DayModel("0",11,2023))
        }

        for (item in 1..29) {
            daysFebruary24?.add(DayModel(item.toString(), 11,2023))
        }

        for (item in 1..4) {
            daysMarch24?.add(DayModel("0",12,2023))
        }

        for (item in 1..31) {
            daysMarch24?.add(DayModel(item.toString(), 12,2023))
        }

//        for (item in 1..6) {
//            daysApril24?.add(DayModel("0",10,2023))
//        }
        for (item in 1..30) {
            daysApril24?.add(DayModel(item.toString(),10,2023))
        }

        for (item in 1..2) {
            daysMay24?.add(DayModel("0",11,2023))
        }

        for (item in 1..31) {
            daysMay24?.add(DayModel(item.toString(), 11,2023))
        }

        for (item in 1..5) {
            daysJune24?.add(DayModel("0",12,2023))
        }

        for (item in 1..30) {
            daysJune24?.add(DayModel(item.toString(), 12,2023))
        }

//        for (item in 1..6) {
//            daysJuly24?.add(DayModel("0",10,2023))
//        }
        for (item in 1..31) {
            daysJuly24?.add(DayModel(item.toString(),10,2023))
        }

        for (item in 1..3) {
            daysAugust24?.add(DayModel("0",11,2023))
        }

        for (item in 1..31) {
            daysAugust24?.add(DayModel(item.toString(), 11,2023))
        }

        for (item in 1..6) {
            daysSeptember24?.add(DayModel("0",12,2023))
        }

        for (item in 1..30) {
            daysSeptember24?.add(DayModel(item.toString(), 12,2023))
        }

        for (item in 1..1) {
            daysOctober24?.add(DayModel("0",10,2023))
        }
        for (item in 1..31) {
            daysOctober24?.add(DayModel(item.toString(),10,2023))
        }

        for (item in 1..4) {
            daysNovember24?.add(DayModel("0",11,2023))
        }

        for (item in 1..30) {
            daysNovember24?.add(DayModel(item.toString(), 11,2023))
        }

        for (item in 1..6) {
            daysDecember24?.add(DayModel("0",12,2023))
        }

        for (item in 1..31) {
            daysDecember24?.add(DayModel(item.toString(), 12,2023))
        }
//____________________________________________________________________________________
        binding?.recyclerMonth?.layoutManager = LinearLayoutManager(this)
        monthAdapter = MonthAdapter(this, monthes ?: return,  { month: Int, year:Int,
                                                                day:String -> viewEventsDay(month, year, day) },
            { model: DayModel, sticker: TextView -> viewSticker(model, sticker) }
        )
        binding?.recyclerMonth?.adapter = monthAdapter




    }


    private fun viewEventsDay(month:Int, year: Int, day:String) {

        val bundle = Bundle()
        bundle.putInt("day", day.toInt())
        bundle.putInt("month", month)
        bundle.putInt("year", year)
        bundle.putInt("groupId", groupId?.toInt()!!)
        val eventsDay= EventsDay()
        eventsDay.arguments = bundle
        eventsDay.show(supportFragmentManager, "eventsDay")

    }


    private fun viewSticker(model:DayModel, sticker: TextView){

        eventsViewModel.loadEventsDay(model.day.toInt(), model.month, model.year, groupId?.toInt()!!).observe(this, Observer {


            if(it.isNotEmpty()) {
                //sticker.setImageResource(R.drawable.sticker)
                sticker.setBackgroundColor(Color.GREEN)
            }


        })

    }


    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, ScreenApp::class.java).apply {
        }
        startActivity(intent)
    }

}
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

    private var daysOctober23: ArrayList<DayModel>? = null
    private var daysNovember23: ArrayList<DayModel>? = null
    private var daysDecember23: ArrayList<DayModel>? = null

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


        daysOctober23 = ArrayList<DayModel>()
        daysNovember23 = ArrayList<DayModel>()
        daysDecember23 = ArrayList<DayModel>()

        monthes?.add(MonthModel("Октябрь", "2023", daysOctober23!!))
        monthes?.add(MonthModel("Ноябрь", "2023", daysNovember23!!))
        monthes?.add(MonthModel("Декабрь", "2023", daysDecember23!!))

        for (item in 1..6) {
            daysOctober23?.add(DayModel("0",10,2023))
        }
        for (item in 1..31) {
            daysOctober23?.add(DayModel(item.toString(),10,2023))
        }

        for (item in 1..2) {
            daysNovember23?.add(DayModel("0",11,2023))
        }

        for (item in 1..30) {
            daysNovember23?.add(DayModel(item.toString(), 11,2023))
        }

        for (item in 1..4) {
            daysDecember23?.add(DayModel("0",12,2023))
        }

        for (item in 1..31) {
            daysDecember23?.add(DayModel(item.toString(), 12,2023))
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
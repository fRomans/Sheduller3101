package com.example.sheduller.presentation.Groups

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import com.example.sheduller.R
import com.example.sheduller.databinding.ActivityAddEventGroupBinding
import com.example.sheduller.presentation.EventsDay.EventsViewModel
import com.example.sheduller.presentation.ScreenApp
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddEventGroup : AppCompatActivity() {

    private var binding:ActivityAddEventGroupBinding? = null
    val eventsViewModel: EventsViewModel by viewModel()
    private var idGroup:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEventGroupBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        idGroup = intent.getStringExtra("groupId")?.toInt()

        val listMonths = listOf("Январь", "Февраль", "Март", "Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь",
            "Ноябрь","Декабрь")

        val adapter = ArrayAdapter(this, R.layout.list_item, listMonths)

        binding?.enterMonth?.setAdapter(adapter)
        binding?.enterMonthNot?.setAdapter(adapter)

        binding?.createEvent?.setOnClickListener(View.OnClickListener {

            if(binding?.enterDay?.text?.toString() != "" &&
                binding?.enterMonth?.text?.toString() != "" &&
                binding?.enterYear?.text?.toString() != "" && binding?.enterTimeStart?.text.toString() != "" &&
                binding?.enterTimeEnd?.text?.toString() != "" && binding?.enterDescription?.text.toString() != ""){
                addEvent()
            }

            else{
                binding?.messageError?.text = getString(R.string.error_enter_info)
            }
        })
    }

    private fun addEvent(){

        var month:Int?=null
        var monthNot:Int?=null

        when(binding?.enterMonth?.text?.toString()){
            "Январь" -> {
                month = 1
            }
            "Февраль" -> {
                month = 2
            }
            "Март" -> {
                month = 3
            }
            "Апрель" -> {
                month = 4
            }
            "Май" -> {
                month = 5
            }
            "Июнь" -> {
                month = 6
            }
            "Июль" -> {
                month = 7
            }
            "Август" -> {
                month = 8
            }
            "Сентябрь" -> {
                month = 9
            }
            "Октябрь" -> {
                month = 10
            }
            "Ноябрь" -> {
                month = 11
            }
            "Декабрь" -> {
                month = 12
            }

            else -> {
                month = binding?.enterMonth?.text?.toString()?.toInt()
            }
        }

        when(binding?.enterMonthNot?.text?.toString()){
            "Январь" -> {
                monthNot = 1
            }
            "Февраль" -> {
                monthNot = 2
            }
            "Март" -> {
                monthNot = 3
            }
            "Апрель" -> {
                monthNot = 4
            }
            "Май" -> {
                monthNot = 5
            }
            "Июнь" -> {
                monthNot = 6
            }
            "Июль" -> {
                monthNot = 7
            }
            "Август" -> {
                monthNot = 8
            }
            "Сентябрь" -> {
                monthNot = 9
            }
            "Октябрь" -> {
                monthNot = 10
            }
            "Ноябрь" -> {
                monthNot = 11
            }
            "Декабрь" -> {
                monthNot = 12
            }

            else -> {
                monthNot = binding?.enterMonthNot?.text?.toString()?.toInt()
//                monthNot = binding?.enterMonth?.text?.toString()?.toInt()  - в исход-ке так
            }
        }

        eventsViewModel.createEvent(binding?.enterDay?.text?.toString()?.toInt(),month,
            binding?.enterYear?.text?.toString()?.toInt(),binding?.enterTimeStart?.text?.toString(),
        binding?.enterTimeEnd?.text?.toString(),binding?.enterDescription?.text?.toString(), idGroup,
            binding?.enterDayNot?.text?.toString()?.toInt(), monthNot,
            binding?.enterYearNot?.text?.toString()?.toInt(), this)

        val intent = Intent(this, ScreenApp::class.java)
        startActivity(intent)

    }

}
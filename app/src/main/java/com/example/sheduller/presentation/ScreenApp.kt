package com.example.sheduller.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheduller.R
import com.example.sheduller.data.models.GroupModel
import com.example.sheduller.databinding.ActivityScreenAppBinding
import com.example.sheduller.presentation.CalendarAction.ItemCalendarActivity
import com.example.sheduller.presentation.EventsDay.EventsViewModel
import com.example.sheduller.presentation.Groups.AddEventGroup
import com.example.sheduller.presentation.Groups.AddGroup
import com.example.sheduller.presentation.Groups.EditGroup
import com.example.sheduller.presentation.Groups.GroupsAdapter
import com.example.sheduller.presentation.Groups.GroupsViewModel
import com.example.sheduller.presentation.Groups.LoadContent
import com.example.sheduller.presentation.Groups.SearchGroup
import com.google.android.material.card.MaterialCardView
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScreenApp : AppCompatActivity(),LoadContent {


    private var binding: ActivityScreenAppBinding? = null

    //private var calendarAdapter: CalendarAdapter? = null вернуть
    private var groupsAdapter: GroupsAdapter? = null

    //private var calendars: ArrayList<CalendarModel>? = null  вернуть
    private var toggle: ActionBarDrawerToggle? = null

    val eventsViewModel: EventsViewModel by viewModel()
    val groupsViewModel: GroupsViewModel by viewModel()

    private var getPreferences: SharedPreferences? = null
    private var user: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenAppBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        getPreferences = this.getSharedPreferences(
            "User",
            Context.MODE_PRIVATE
        )


        user = getPreferences?.getString("key", "User")


        initRecyclerGroups()

        loadGroups()




        eventsViewModel.migration(this)
        groupsViewModel.migration(user ?: return, this)

        //binding?.fab?.hide()
        //binding?.fab?.setOnClickListener(this)// floatActionButton


//________________RecyclerView__start_________________________________

        //вернуть
//        calendars = ArrayList<CalendarModel>()
//
//        calendars?.add(CalendarModel("Календарь1"))
//        calendars?.add(CalendarModel("Календарь2"))
//        calendars?.add(CalendarModel("Календарь3"))
//        calendars?.add(CalendarModel("Календарь4"))
//        calendars?.add(CalendarModel("Календарь5"))
//        calendars?.add(CalendarModel("Календарь6"))
//        calendars?.add(CalendarModel("Календарь16"))
//        calendars?.add(CalendarModel("Календарь26"))
//        calendars?.add(CalendarModel("Календарь36"))
//        calendars?.add(CalendarModel("Календарь46"))
//        calendars?.add(CalendarModel("Календарь56"))
//        calendars?.add(CalendarModel("Календарь66"))
//        calendars?.add(CalendarModel("Календарь17"))
//        calendars?.add(CalendarModel("Календарь27"))
//        calendars?.add(CalendarModel("Календарь37"))
//        calendars?.add(CalendarModel("Календарь47"))
//        calendars?.add(CalendarModel("Календарь57"))
//        calendars?.add(CalendarModel("Календарь67"))


        //вернуть

//        binding?.catalogCalendars?.layoutManager = LinearLayoutManager(this)
//        calendarAdapter = CalendarAdapter(this, calendars!!)
//        binding?.catalogCalendars?.adapter = calendarAdapter


//
//________________RecyclerView__end_________________________________

        setSupportActionBar(binding?.topAppBar)

        toggle = ActionBarDrawerToggle(this, binding?.drawerLayout, R.string.open, R.string.close)

        binding?.topAppBar?.setOnMenuItemClickListener { menuItem: MenuItem ->

            when (menuItem.itemId) {

                R.id.searchTopMenu -> {
//                    val intent = Intent(this, SearchActivity::class.java)
//                    startActivity(intent)
//                    finish()
                    val searchPanel = SearchGroup()
                    searchPanel.show((this as FragmentActivity).supportFragmentManager, "search_panel")
                    true

                }

                R.id.calendar_month -> {
                    val intent = Intent(this, MonthActivity::class.java)
                    startActivity(intent)
                    finish()
                    true

                }

                R.id.logout_menu -> {

                    getPreferences = this.getSharedPreferences(
                        "User",
                        Context.MODE_PRIVATE
                    )

                    val editor = getPreferences?.edit()
                    editor?.putString("key", "User")
                    editor?.apply()

                    user = "User"


                    val activity = Intent(this, MainActivity::class.java)
                    startActivity(activity)
                    true

                }


                else -> false
            }

        }

        binding?.fab?.setOnClickListener(View.OnClickListener {
            //supportFragmentManager.beginTransaction().replace(R.id.content, AddGroup()).commit()
            val intent = Intent(this, AddGroup::class.java)
            startActivity(intent)

        })
    }


    private fun initRecyclerGroups(){
        binding?.groups?.layoutManager = LinearLayoutManager(this)
        groupsAdapter = GroupsAdapter({model:GroupModel -> infoGroup(model)},
            {card:MaterialCardView, message:TextView, butAddEvent:ImageButton, butEditGroup:ImageButton,
             model:GroupModel -> styleGroupAdmin(card,message, butAddEvent, butEditGroup, model)},
            {model:GroupModel -> addEvent(model)},  {model:GroupModel -> editGroup(model)})
        binding?.groups?.adapter = groupsAdapter
    }


    private fun loadGroups() {
        groupsViewModel.loadGroups().observe(this, Observer {
            groupsAdapter?.setList(it)
            groupsAdapter?.notifyDataSetChanged()

            if (it.count() > 0) {
                binding?.messageNotGroups?.visibility = View.GONE
                binding?.groups?.visibility = View.VISIBLE
            } else {
                binding?.messageNotGroups?.visibility = View.VISIBLE
                binding?.groups?.visibility = View.GONE
            }
        })


    }


    private fun infoGroup(model: GroupModel) {

        val intent = Intent(this, ItemCalendarActivity::class.java).apply {
            putExtra("nameCalendar", model.name)
            putExtra("groupId", model.id.toString())
        }
        startActivity(intent)

    }


    private fun styleGroupAdmin(
        card: MaterialCardView,
        message: TextView,
        buttonAddEvent: ImageButton,
        buttonEditGroup: ImageButton,
        model: GroupModel
    ) {
        if (model.admin == user){
            card.setBackgroundResource(R.color.orange)
            message.visibility = View.VISIBLE
            buttonAddEvent.visibility = View.VISIBLE
            buttonEditGroup.visibility = View.VISIBLE


        } else {
            card.setBackgroundResource(R.color.black)
            message.visibility = View.GONE
            buttonAddEvent.visibility = View.GONE
            buttonEditGroup.visibility = View.GONE
        }
    }


    private fun addEvent(model: GroupModel) {

        val intent = Intent(this, AddEventGroup::class.java).apply {
            putExtra("groupId", model.id.toString())
        }
        startActivity(intent)
    }

    private fun editGroup(model: GroupModel){

        val intent = Intent(this, EditGroup::class.java).apply {
            putExtra("idGroup", model.id.toString())
            putExtra("name", model.name)
            putExtra("admin", model.admin)
        }
        startActivity(intent)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.top_menu, menu)

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            android.R.id.home -> {

                if (toggle?.onOptionsItemSelected(item) == true) {
                    return true
                }

                return super.onOptionsItemSelected(item)

            }


        }

        return super.onOptionsItemSelected(item)

    }

//    override fun onClick(view: View?) {
//
//        when (view?.id) {
//
////            R.id.fab -> supportFragmentManager.beginTransaction()
////                .replace(R.id.content, ItemCalendarRV()).commit()
//////            R.id.display -> binding?.fab?.show()
////            R.id.hide -> binding?.fab?.hide()
////            R.id.fabExtended -> supportFragmentManager.beginTransaction().replace(R.id.content, Shop()).commit()
//
//        }
//    }


//
//    override fun onClickItem(calendarmodel: CalendarModel) {
//
////        val itemCalendarActivity = ItemCalendarActivity()
////        val parameters = Bundle()
////        parameters.putString("nameCalendar", calendarmodel.calendarName)
////        itemCalendarActivity.arguments = parameters
//
//        val intent = Intent(this, ItemCalendarActivity::class.java).apply {
//            putExtra("nameCalendar", calendarmodel.calendarName)
//        }
//        startActivity(intent)
//        finish()
//        true
//
//    }


    override fun onStart() {
        super.onStart()
        user = getPreferences?.getString("key", "User")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, this::class.java).apply {
        }
        startActivity(intent)
    }

    override fun loadContent(name: String) {
        loadSearchContent(name)
    }

    private fun loadSearchContent(name:String){

        groupsViewModel.loadSearchGroups(name).observe(this, Observer {
            groupsAdapter?.setList(it)
            groupsAdapter?.notifyDataSetChanged()

            if (it.count()>0){
                binding?.messageNotGroups?.visibility=View.GONE
                binding?.groups?.visibility = View.VISIBLE
            }

            else {
                binding?.messageNotGroups?.visibility=View.VISIBLE
                binding?.groups?.visibility = View.GONE
            }
        })

    }


}
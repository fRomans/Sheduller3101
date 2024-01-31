package com.example.sheduller.presentation.Groups

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheduller.R
import com.example.sheduller.databinding.EditGroupBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditGroup : AppCompatActivity() {
    private  var binding:EditGroupBinding? = null
    private  var idGroup:Int? = null
    private var editGroupContactsAdapter: EditGroupContactsAdapter? = null
    val groupsViewModel: GroupsViewModel by viewModel()
    var arrayListContacts:ArrayList<EditGroupContactsModel> = arrayListOf()
    private var getPreferences: SharedPreferences? = null
    private var user:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditGroupBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        idGroup = intent.getStringExtra("idGroup")?.toInt()

//        получаем id из предидущего активити при нажатии палочки
        binding?.idGroup?.text = idGroup?.toString()

        getPreferences = this.getSharedPreferences(
            "User",
            Context.MODE_PRIVATE
        )


        user = getPreferences?.getString("key", "User")

        initRecyclerGroupContacts()

        loadContacts()
    }


    private fun initRecyclerGroupContacts(){
        binding?.groupContacts?.layoutManager = LinearLayoutManager(this)
        editGroupContactsAdapter = EditGroupContactsAdapter (arrayListContacts,{model: EditGroupContactsModel -> deleteContact(model)}
        )
        binding?.groupContacts?.adapter = editGroupContactsAdapter
    }


    private fun loadContacts(){

        groupsViewModel.loadContactsGroup(user!!, idGroup!!).observe(this, Observer {


            val list:ArrayList<String> = arrayListOf(*it.joinToString(" ").toString().split(",").toTypedArray())



            for(cursor in list) {
                //println(cursor[0])
                arrayListContacts.add(EditGroupContactsModel(cursor))
            }

        })

    }


    private fun deleteContact(model:EditGroupContactsModel) {

    }
}
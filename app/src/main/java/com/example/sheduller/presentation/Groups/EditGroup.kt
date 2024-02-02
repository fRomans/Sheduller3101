package com.example.sheduller.presentation.Groups

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheduller.R
import com.example.sheduller.data.models.GroupModel
import com.example.sheduller.databinding.EditGroupBinding
import com.example.sheduller.presentation.ScreenApp
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditGroup : AppCompatActivity() {
    private  var binding:EditGroupBinding? = null
    private  var idGroup:Int? = null
    private  var name:String? = null
    private  var admin:String? = null
    private var editGroupContactsAdapter: EditGroupContactsAdapter? = null
    val groupsViewModel: GroupsViewModel by viewModel()
    var arrayListContacts:ArrayList<EditGroupContactsModel> = arrayListOf()
    private var getPreferences: SharedPreferences? = null
    private var user:String?=null

    val newContacts:MutableList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditGroupBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.editGroupArrowBackSearch?.setOnClickListener{

            val intent = Intent(this@EditGroup, ScreenApp::class.java)
            startActivity(intent)
            finish()
        }

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

        binding?.editContacts?.setOnClickListener(View.OnClickListener {


            val audit: MutableList<String> = arrayListOf()

// списочный масси из Recyclereview в список с преобразованием в строку
            arrayListContacts.map {
                audit.add(it.contact)
            }
                // все контакты минус те, которые помечены на удаление
            val filtered = audit.minus(newContacts)

            groupsViewModel.updateContactsGroupApi(idGroup,filtered.joinToString(","),this)

            groupsViewModel.updateContactsGroup(GroupModel(idGroup!!,name!!,admin!!,filtered.joinToString(",")))
            onBackPressed()//команда вернуться на предидущий экран(шаблонный метод)
        })

    }


    private fun initRecyclerGroupContacts(){
        binding?.groupContacts?.layoutManager = LinearLayoutManager(this)
        editGroupContactsAdapter = EditGroupContactsAdapter (arrayListContacts,{model: EditGroupContactsModel, tag:String -> actionsContact(model, tag)}
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

    private fun actionsContact(model:EditGroupContactsModel, tag:String) {


        if (tag=="add"){
            newContacts.add(model.contact)

        } else {
            newContacts.remove(model.contact)

        }


    }


}
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
        name = intent.getStringExtra("name")
        admin = intent.getStringExtra("admin")

        //binding?.idGroup?.text = idGroup?.toString() + " - " + name + " - " + admin

        getPreferences = this.getSharedPreferences(
            "User",
            Context.MODE_PRIVATE
        )


        user = getPreferences?.getString("key", "User")

        initRecyclerGroupContacts()

        loadContacts()

        binding?.editContacts?.setOnClickListener(View.OnClickListener {


            val audit: MutableList<String> = arrayListOf()


            arrayListContacts.map {
                audit.add(it.contact)
            }

            val filtered = audit.minus(newContacts)

            groupsViewModel.updateContactsGroupApi(idGroup,filtered.joinToString(","),this)

            groupsViewModel.updateContactsGroup(GroupModel(idGroup!!,name!!,admin!!,filtered.joinToString(",")))
            onBackPressed()
        })

        binding?.addContact?.setOnClickListener(View.OnClickListener {



            val intent = Intent(this, AddContactsEditGroup::class.java)


            intent.putExtra("name", name)
            intent.putExtra("admin", admin)
            intent.putExtra("id", idGroup.toString())

            intent.putExtra("contacts",binding?.contacts?.text)


            startActivity(intent)



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

                arrayListContacts.add(EditGroupContactsModel(cursor))
            }

            binding?.contacts?.text = list.joinToString(",")


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
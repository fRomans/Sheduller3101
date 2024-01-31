package com.example.sheduller.presentation.Groups

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sheduller.R
import com.example.sheduller.databinding.AddGroupBinding
import com.example.sheduller.presentation.ScreenApp
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddGroup : AppCompatActivity(), View.OnClickListener {

    private var binding:AddGroupBinding? = null
    private var contacts:String? = null
    private var getPreferences: SharedPreferences? = null
    private var user:String?=null

    val groupsViewModel: GroupsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddGroupBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        getPreferences = this.getSharedPreferences(
            "User",
            Context.MODE_PRIVATE
        )


        user = getPreferences?.getString("key", "User")

        contacts = intent.getStringExtra("contacts")
        actionsContacts()
        conditionButtonCreateGroup()

        binding?.actionsContacts?.setOnClickListener(this)
        binding?.back?.setOnClickListener(this)
        binding?.createGroup?.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.actions_contacts->{
                if (contacts==null){
                    val intent=Intent(this, NewGroup::class.java)
                    startActivity(intent)
                } else{
                    contacts = null
                }

                actionsContacts()

            }

            R.id.back -> {

                val intent=Intent(this, ScreenApp::class.java)
                startActivity(intent)

            }

            R.id.create_group ->{

                if(binding?.enterNameGroup?.text?.isEmpty()==true){
                    Toast.makeText(this, getString(R.string.enter_name_group), Toast.LENGTH_LONG).show()


                }

                else{
                    groupsViewModel.createGroup(binding?.enterNameGroup?.text?.toString(), user, contacts, this)
                    val intent = Intent(this, ScreenApp::class.java)
                    startActivity(intent)

                }


            }


        }
    }

    private fun actionsContacts(){

        if (contacts != null){
            contacts = intent.getStringExtra("contacts")
            binding?.messageAddedContacts?.text = contacts
            binding?.actionsContacts?.text = getString(R.string.clear_contacts)

        } else {

            binding?.messageAddedContacts?.text = getString(R.string.message_empty_contacts)
            binding?.actionsContacts?.text = getString(R.string.add_contacts)


        }

        conditionButtonCreateGroup()

    }

    private fun conditionButtonCreateGroup(){

        if (contacts == null){
            binding?.createGroup?.visibility = View.GONE
        }

        else {
            binding?.createGroup?.visibility = View.VISIBLE
        }

    }

    override fun onStart() {
        super.onStart()
        user = getPreferences?.getString("key", "User")
    }
}
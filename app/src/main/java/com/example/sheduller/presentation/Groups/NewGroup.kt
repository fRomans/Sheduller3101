package com.example.sheduller.presentation.Groups

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheduller.R
import com.example.sheduller.databinding.NewGroupBinding
import com.vmadalin.easypermissions.EasyPermissions
import pub.devrel.easypermissions.AppSettingsDialog


class NewGroup : AppCompatActivity(),EasyPermissions.PermissionCallbacks,View.OnClickListener {
    private var binding:NewGroupBinding? = null

    var arrayList:ArrayList<ContactModel> = arrayListOf()
    var listSelectedContacts:MutableList<String> = mutableListOf()
    var adapter = ContactsAdapter(arrayList,{model:ContactModel -> checked(model)})


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewGroupBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        listSelectedContacts.clear()

        conditionButtonAddContacts()

        if(checkContactPermissions()){
            binding.apply {
                binding?.listContacts.apply {
                    this!!.layoutManager = LinearLayoutManager(this@NewGroup)
                    adapter = ContactsAdapter(arrayList,{model:ContactModel -> checked(model)})

                }
            }
            getContactc()
        }

        binding?.addContacts?.setOnClickListener (this)
        binding?.back?.setOnClickListener (this)

    }


    //интеграция адресной книги на мой экран
    @SuppressLint("Range")
    private fun getContactc() {
        arrayList.clear()
        val cursor = this.contentResolver
            .query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER,

                    ),null,null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            )
        while (cursor!!.moveToNext()){
            val idContact = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID))
            val contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val contactModel =  ContactModel(idContact.toInt(), contactName, contactNumber, false)
            arrayList.add(contactModel)
        }
        adapter.notifyDataSetChanged()
        cursor.close()
    }

//шаблонные методы для взаимодействия с тлф книгой
    private fun checkContactPermissions():Boolean{
        if (PermissionTracking.hasCOntactPermissions(this)){
            return true
        }else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            EasyPermissions.requestPermissions(
                this,
                "You will need to accept the permission in order to run the application",
                100,
                android.Manifest.permission.READ_CONTACTS,
                android.Manifest.permission.WRITE_CONTACTS,
            )
            return true
        }else{
            return false
        }
    }

    //шаблонные методы для взаимодействия с тлф книгой
    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        TODO("Not yet implemented")
    }

    //шаблонные методы для взаимодействия с тлф книгой
    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            AppSettingsDialog.Builder(this).build().show()
        }else{
            checkContactPermissions()
        }
    }

    fun checked(model: ContactModel){

        if (model.condition==true){

            listSelectedContacts.add(model.phone)


        } else if (model.condition==false) {

            val searchElement = listSelectedContacts.find { it.equals(model.phone) }

            if (searchElement==model.phone){
                listSelectedContacts.remove(model.phone)
            }


        }

        conditionButtonAddContacts()


    }

    fun conditionButtonAddContacts() {
        if (listSelectedContacts.isEmpty()){
            binding?.addContacts?.visibility = View.GONE
        } else {
            binding?.addContacts?.visibility = View.VISIBLE
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.back -> {

                val intent= Intent(this, AddGroup::class.java)
                startActivity(intent)

            }

            R.id.add_contacts -> {
                val contacts:String=listSelectedContacts.joinToString()
                listSelectedContacts.clear()
                val convertorContacts = contacts.replace("[+ -]".toRegex(), "")
                //Toast.makeText(this,convertorContacts,Toast.LENGTH_LONG).show()

                val intent=Intent(this, AddGroup::class.java)
                intent.putExtra("contacts", convertorContacts)
                startActivity(intent)
            }
        }
    }


}
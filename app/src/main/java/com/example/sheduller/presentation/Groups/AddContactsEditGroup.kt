package com.example.sheduller.presentation.Groups

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheduller.R
import com.example.sheduller.data.models.GroupModel
import com.example.sheduller.databinding.ActivityAddContactsEditGroupBinding
import com.example.sheduller.databinding.NewGroupBinding
import com.example.sheduller.presentation.ScreenApp
import com.vmadalin.easypermissions.EasyPermissions
import org.koin.androidx.viewmodel.ext.android.viewModel
import pub.devrel.easypermissions.AppSettingsDialog

class AddContactsEditGroup : AppCompatActivity(),EasyPermissions.PermissionCallbacks,View.OnClickListener {


    private var binding: ActivityAddContactsEditGroupBinding? = null


    private  var id:String? = null
    private  var name:String? = null
    private  var admin:String? = null
    private  var getContacts:String? = null
    var converterGetContacts:Array<String>?=null




    var arrayList:ArrayList<ContactModel> = arrayListOf()
    var listSelectedContacts:MutableList<String> = mutableListOf()
    var adapter = ContactsAdapter(arrayList,{model:ContactModel -> checked(model)})
    val groupsViewModel: GroupsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactsEditGroupBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        name = intent.getStringExtra("name")
        admin = intent.getStringExtra("admin")
        getContacts = intent.getStringExtra("contacts")
        id = intent.getStringExtra("id")

        converterGetContacts = getContacts?.split(",")?.toTypedArray()



        conditionButtonAddContacts()

        if(checkContactPermissions()){
            binding.apply {
                binding?.listContacts.apply {
                    this!!.layoutManager = LinearLayoutManager(this@AddContactsEditGroup)
                    adapter = ContactsAdapter(arrayList,{model:ContactModel -> checked(model)})

                }
            }
            getContactc()
        }

        binding?.addContacts?.setOnClickListener (this)
        binding?.backFromAddContactsEditGroup?.setOnClickListener (this)
    }


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
            arrayList.add(contactModel) // это все контакты тел книги
        }
//contacts.replace("[+ -]".toRegex(), "")
        converterGetContacts?.map {
            val predicat = it // все контакты имеющиеся в группе

            // исключение контактов группы из  контактов тел книги, которые планируем добавить в группу
            arrayList.removeIf{(it.phone.trim().replace("[+ -]".toRegex(), "") + "/" + it.name.trim()) == predicat.trim()}
        }

        adapter.notifyDataSetChanged()
        cursor.close()
    }


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

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        TODO("Not yet implemented")
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            AppSettingsDialog.Builder(this).build().show()
        }else{
            checkContactPermissions()
        }
    }

    fun checked(model: ContactModel){

        if (model.condition==true){

            listSelectedContacts.add(model.phone+"/"+model.name)


        } else if (model.condition==false) {

            val searchElement = listSelectedContacts.find { it.substring(0,16).equals(model.phone) }!!

            if (searchElement.substring(0,16)==model.phone){
                listSelectedContacts.remove(model.phone+"/"+model.name)
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
            R.id.back_from_add_contacts_edit_group -> {

                onBackPressed() //кнопка возврата

            }

            R.id.add_contacts -> {

                var listContacts:MutableList<String> = mutableListOf()
                for (con in listSelectedContacts){
                    con.trim()
                    val tel = con.subSequence(0,16).replace("[+ -]".toRegex(), "")
                    val name = con.substring(17)
                    val telname = tel +"/"+ name
                    listContacts.add(telname)
                }

                val contacts:String=listContacts.joinToString()
                listSelectedContacts.clear()
                val convertorContacts = contacts


                val data = "$getContacts,$convertorContacts"



                groupsViewModel.updateContactsGroupApi(id?.toInt()!!,data,this)
                groupsViewModel.updateContactsGroup(GroupModel(id?.toInt()!!,name!!,admin!!,data))

                val intent = Intent(this, ScreenApp::class.java)
                startActivity(intent)

            }
        }
    }
}
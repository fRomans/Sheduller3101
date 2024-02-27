package com.example.sheduller.presentation.Groups

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.example.sheduller.databinding.ContactGroupItemBinding

class EditGroupContactsAdapter( private val contactsList:ArrayList<EditGroupContactsModel>,
                                private var actionsContact: (EditGroupContactsModel, String) -> Unit)
    : RecyclerView.Adapter<EditGroupContactsAdapter.GroupEditContactsHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupEditContactsHolder {

        val binding : ContactGroupItemBinding =
            ContactGroupItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GroupEditContactsHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: GroupEditContactsHolder, position: Int) {
        holder.bind(contactsList[position], actionsContact)

    }

//    fun setList(groups: List<GroupModel>) {
//        contactsList.clear()
//        contactsList.addAll(groups)
//
//    }




    class GroupEditContactsHolder(val binding: ContactGroupItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            model: EditGroupContactsModel, actionsContact: (EditGroupContactsModel, String) -> Unit

        ) {
            //binding.phoneContact.text = model.contact
// подстрока с 12-го  символа
            binding.nameContact.text = model.contact.trim().substring(12)
// подстрока с 1-го по 11-й  символ
            binding.phoneContact.text = model.contact.substring(0,11)


            binding.selectContact.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    actionsContact(model, "add")
                }

                else{
                    actionsContact(model, "delete")
                }



            })


        }


    }




}
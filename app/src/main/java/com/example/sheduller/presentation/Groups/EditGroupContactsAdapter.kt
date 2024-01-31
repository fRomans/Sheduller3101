package com.example.sheduller.presentation.Groups

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sheduller.databinding.ContactGroupItemBinding

class EditGroupContactsAdapter( private val contactsList:ArrayList<EditGroupContactsModel>,
                                private var deleteContact: (EditGroupContactsModel) -> Unit)
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
        holder.bind(contactsList[position], deleteContact)

    }

//    fun setList(groups: List<GroupModel>) {
//        contactsList.clear()
//        contactsList.addAll(groups)
//
//    }




    class GroupEditContactsHolder(val binding: ContactGroupItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            model: EditGroupContactsModel, deleteContact: (EditGroupContactsModel) -> Unit

        ) {

            binding.phoneContact.text = model.contact


            binding.deleteContact.setOnClickListener(View.OnClickListener {
                deleteContact(model)
            })


        }


    }



}
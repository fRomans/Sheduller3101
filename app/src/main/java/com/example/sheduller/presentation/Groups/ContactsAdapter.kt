package com.example.sheduller.presentation.Groups

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sheduller.databinding.ContactItemBinding


class ContactsAdapter(
    val contactList: ArrayList<ContactModel>, private var checked: (ContactModel) -> Unit
) : RecyclerView.Adapter<ContactsAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ContactItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val nameContact = binding.nameContact
        val phoneContact = binding.phoneContact


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ContactItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = contactList[position]

        holder.binding.phoneContact.text = item.phone

        holder.binding.selectContact.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                item.condition = true
                checked(item)
            }

            else{
                item.condition = false
                checked(item)
            }



        }
        )
        holder.binding.nameContact.text = item.name



    }

    override fun getItemCount(): Int = contactList.size


}
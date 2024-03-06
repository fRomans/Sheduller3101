package com.example.sheduller.presentation.Groups

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sheduller.data.models.EventModel
import com.example.sheduller.data.models.GroupModel
import com.example.sheduller.databinding.CalendarItemLayoutBinding
import com.example.sheduller.databinding.EventItemBinding
import com.example.sheduller.databinding.GroupItemBinding
import com.google.android.material.card.MaterialCardView

class GroupsAdapter( private var infoGroup: (GroupModel) -> Unit,
                     private var styleGroupAdmin: (MaterialCardView, TextView, ImageButton, ImageButton,ImageButton, GroupModel) -> Unit,
                     private var addEvent: (GroupModel) -> Unit, private var editGroup: (GroupModel) -> Unit,
                     private var deleteGroup: (GroupModel) -> Unit
) : RecyclerView.Adapter<GroupsAdapter.GroupsHolder>() {

    private val groupsList = ArrayList<GroupModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsHolder {

        val binding : GroupItemBinding =
            GroupItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GroupsHolder(binding)
    }

    override fun getItemCount(): Int {
        return groupsList.size
    }

    override fun onBindViewHolder(holder: GroupsHolder, position: Int) {
        holder.bind(groupsList[position], infoGroup, styleGroupAdmin, addEvent, editGroup, deleteGroup)

    }

    fun setList(groups: List<GroupModel>) {
        groupsList.clear()
        groupsList.addAll(groups)

    }




    class GroupsHolder(val binding: GroupItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            model: GroupModel, infoGroup: (GroupModel) -> Unit,
            styleGroupAdmin: (MaterialCardView, TextView, ImageButton, ImageButton,ImageButton, GroupModel) -> Unit,
            addEvent: (GroupModel) -> Unit, editGroup: (GroupModel) -> Unit, deleteGroup: (GroupModel) -> Unit

        ) {

            binding.nameGroup.text = model.name
            binding.adminGroup.text = "Администратор - " + model.admin

            binding.cardGroup.setOnClickListener(View.OnClickListener {
                infoGroup(model)
            })

            styleGroupAdmin(binding.cardGroup, binding.roleGroup, binding.addEvent, binding.editGroup, binding.deleteGroup, model)

            binding.addEvent.setOnClickListener(View.OnClickListener {
                addEvent(model)
            })

            binding.editGroup.setOnClickListener(View.OnClickListener {
                editGroup(model)
            })

            binding.deleteGroup.setOnClickListener(View.OnClickListener {
                deleteGroup(model)
            })



        }


    }



}
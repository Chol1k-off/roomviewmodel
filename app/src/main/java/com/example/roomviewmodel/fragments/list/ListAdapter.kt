package com.example.roomviewmodel.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomviewmodel.R
import com.example.roomviewmodel.data.User

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.castom_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = userList[position]
        val idtxt = holder.itemView.findViewById<TextView>(R.id.idtxt)
        val firstnametat = holder.itemView.findViewById<TextView>(R.id.firstnametat)
        val lastnametxt = holder.itemView.findViewById<TextView>(R.id.lastnametxt)
        val agetxt = holder.itemView.findViewById<TextView>(R.id.agetxt)
        val row_layout = holder.itemView.findViewById<ConstraintLayout>(R.id.row_layout)

        idtxt.text = currentUser.id.toString()
        firstnametat.text = currentUser.firstName.toString()
        lastnametxt.text = currentUser.lastName.toString()
        agetxt.text = currentUser.age.toString()

        row_layout.setOnClickListener {
        val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentUser)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
     return userList.size
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}
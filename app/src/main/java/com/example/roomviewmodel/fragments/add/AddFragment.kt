package com.example.roomviewmodel.fragments.add

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomviewmodel.R
import com.example.roomviewmodel.databinding.FragmentAddBinding
import com.example.roomviewmodel.data.User
import com.example.roomviewmodel.viewmodel.UserViewModel

class AddFragment : Fragment() {

    private lateinit var bind:FragmentAddBinding
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentAddBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        bind.addBtn.setOnClickListener {
            insertDataToDatabase()
        }
        return bind.root
    }
    private fun insertDataToDatabase(){
        val firstName = bind.addName.text.toString()
        val lastName = bind.addSurname.text.toString()
        val age = bind.addAge.text

    if(inputCheck(firstName, lastName, age)){
        val user = User(0, firstName, lastName, age.toString().toInt())
        mUserViewModel.addUser(user)
        Toast.makeText(requireContext(), "Запись добавлена в базу", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }else{
        Toast.makeText(requireContext(), "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show()
    }
    }
    private fun inputCheck(firstName: String, lastName:String,age:Editable):Boolean{
        return !(firstName.isEmpty()||lastName.isEmpty()||age.isEmpty())
    }

}
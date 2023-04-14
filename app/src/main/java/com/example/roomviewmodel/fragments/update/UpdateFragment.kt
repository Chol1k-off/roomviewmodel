package com.example.roomviewmodel.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomviewmodel.R
import com.example.roomviewmodel.data.User
import com.example.roomviewmodel.databinding.FragmentUpdateBinding
import com.example.roomviewmodel.viewmodel.UserViewModel

class UpdateFragment : Fragment() {

    private lateinit var bind: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentUpdateBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        bind.updateSurname.setText(args.carentuser.lastName)
        bind.updateName.setText(args.carentuser.firstName)
        bind.updateAge.setText(args.carentuser.age.toString())

        bind.updateBtn.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return bind.root
    }


    private fun updateItem() {
        val firstName = bind.updateName.text.toString()
        val lastName = bind.updateSurname.text.toString()
        val age = bind.updateAge.text.toString()

        if (inputCheck(firstName, lastName, age)) {
            val user = User(args.carentuser.id, firstName, lastName, age.toString().toInt())
            mUserViewModel.updateUser(user)
            Toast.makeText(requireContext(), "Запись изменена", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Необходимо заполнить все поля", Toast.LENGTH_SHORT)
                .show()
        }
    }


    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !(firstName.isEmpty() || lastName.isEmpty() || age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.deletemenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_delete) {
            println(item.itemId)
            deleteUser()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да") { _, _ ->
            mUserViewModel.deleteUser(args.carentuser)
            Toast.makeText(requireContext(), "Запись успешно удалена", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Нет") { _, _ ->}

        builder.setTitle("Удалить запись")
        builder.setMessage("Вы действительно хотите удалить запис: ${args.carentuser.firstName} ${args.carentuser.lastName}")
        builder.create().show()

    }
}
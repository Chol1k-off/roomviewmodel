package com.example.roomviewmodel.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roomviewmodel.data.User
import com.example.roomviewmodel.data.Userdao

class UserRepository(private val userDao: Userdao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.adduser(user)
    }
    suspend fun updateUser(user: User)
    {
        userDao.updateUser(user)
    }
    suspend fun deleteUser(user: User)
    {
       userDao.deleteUser(user)
    }
    suspend fun deleteAllUsers()
    {
        userDao.deleteAllUsers()
    }

}
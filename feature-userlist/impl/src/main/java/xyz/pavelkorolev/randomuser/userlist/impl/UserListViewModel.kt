package xyz.pavelkorolev.randomuser.userlist.impl

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class UserListViewModel : ViewModel() {

    fun onAddButtonClick() {
        load()
    }

    private fun load() {
        // TODO load saved users
    }
}

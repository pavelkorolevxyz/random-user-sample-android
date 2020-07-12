package xyz.pavelkorolev.randomuser.userlist.impl

import androidx.lifecycle.ViewModel

class UserListViewModel : ViewModel() {

    init {
        load()
    }

    fun onAddButtonClick() {
        // TODO route to add
    }

    private fun load() {
        // TODO load saved users
    }
}

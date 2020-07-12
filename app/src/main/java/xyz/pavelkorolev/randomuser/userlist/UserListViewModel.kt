package xyz.pavelkorolev.randomuser.userlist

import androidx.lifecycle.ViewModel

class UserListViewModel : ViewModel() {

    init {
        load()
    }

    private fun load() {
        // TODO load saved users
    }
}

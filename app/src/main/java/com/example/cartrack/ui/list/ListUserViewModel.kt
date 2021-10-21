package com.example.cartrack.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cartrack.base.BindingViewModel
import com.example.cartrack.data.AppDataRepository
import com.example.cartrack.data.model.UserObject
import com.example.cartrack.extention.plusAssign
import com.example.cartrack.manager.UserManager
import com.example.cartrack.util.rx.SchedulerProvider
import io.reactivex.functions.Consumer
import kotlinx.coroutines.launch

/**
 * ListUserViewModel for [ListUserActivity]
 * @author n.quoi
 * @date 10.21.2021
 */

class ListUserViewModel(
    userManager: UserManager,
    private val appDataRepository: AppDataRepository,
    private val schedulerProvider: SchedulerProvider,
) : BindingViewModel(userManager) {

    private var users: MutableLiveData<List<UserObject>> = MutableLiveData(emptyList())

    val userAdapter = UserAdapter()

    init {
        getUserList()
    }

    private fun getUserList() {
        showLoadingDialog()

        viewModelScope.launch {
            appDataRepository.getUsers()
                .observeOn(schedulerProvider.ui())
                .doOnComplete { dismissLoadingDialog() }
                .subscribe(Consumer {
                    if (it.isNotEmpty()) {
                        users.value = it
                    }
                }, commonOnErrorConsumer) += disposeBag
        }
    }

    fun setData() {
        users.value?.let {
            setUsers(it)
        }
    }

    /**
     * Set category data for view model
     * @param users [List<UserObject>]
     */
    private fun setUsers(users: List<UserObject>) {
        userAdapter.itemsSource.addAll(users.map {
            UserItemViewModel(it)
        })
    }

    /**
     * Notify data set changed
     */
    fun notifyDataSetChange() {
        userAdapter.notifyDataSetChanged()
    }

    private fun getSelectedCategories(): List<UserObject> {
        return userAdapter.selectedItems.map { it.user }
    }
}

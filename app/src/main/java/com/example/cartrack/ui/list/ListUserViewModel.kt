package com.example.cartrack.ui.list

import android.graphics.Color
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cartrack.base.BaseRecyclerViewAdapter
import com.example.cartrack.base.BindingViewModel
import com.example.cartrack.base.Navigable
import com.example.cartrack.data.AppDataRepository
import com.example.cartrack.data.model.UserObject
import com.example.cartrack.extention.plusAssign
import com.example.cartrack.manager.UserManager
import com.example.cartrack.util.rx.SchedulerProvider
import io.reactivex.functions.Consumer
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * ListUserViewModel for [ListUserActivity]
 * @author n.quoi
 * @date 10.21.2021
 */

class ListUserViewModel(
    private val navigable: Navigable,
    userManager: UserManager,
    private val appDataRepository: AppDataRepository,
    private val schedulerProvider: SchedulerProvider,
) : BindingViewModel(userManager) {

    private var users: MutableLiveData<List<UserObject>> = MutableLiveData(emptyList())

    val userAdapter = UserAdapter().apply {
        setItemClickListener(object : BaseRecyclerViewAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                val user = getItem(position)
            }
        })
    }

    init {
        getUserList()
    }

    private fun getUserList() {
        showLoadingDialog()
        appDataRepository.getUsers()
            .observeOn(schedulerProvider.ui())
            .subscribe(Consumer { userList ->
                dismissLoadingDialog()
                if (userList.isNotEmpty()) {
                    users.value = userList
                    setUsers(userList)
                }
            }, commonOnErrorConsumer) += disposeBag
    }

    /**
     * Set user data for adapter
     * @param users [List<UserObject>]
     */
    private fun setUsers(users: List<UserObject>) {
        userAdapter.itemsSource.clear()
        userAdapter.itemsSource.addAll(users.map {
            UserItemViewModel(it)
        })
        notifyDataUserChange()
    }

    /**
     * Notify data set changed
     */
    fun notifyDataUserChange() {
        userAdapter.notifyDataSetChanged()
        showToast(userAdapter.itemCount.toString())
    }
}

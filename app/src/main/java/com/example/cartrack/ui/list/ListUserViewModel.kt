package com.example.cartrack.ui.list

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.cartrack.base.BaseRecyclerViewAdapter
import com.example.cartrack.base.BindingViewModel
import com.example.cartrack.data.AppDataRepository
import com.example.cartrack.data.model.UserObject
import com.example.cartrack.extention.plusAssign
import com.example.cartrack.global.NavigationItem
import com.example.cartrack.global.TransactionAnimation
import com.example.cartrack.manager.UserManager
import com.example.cartrack.ui.detail.UserDetailActivity
import com.example.cartrack.ui.login.LoginActivity
import com.example.cartrack.ui.login.LoginViewModel
import com.example.cartrack.util.rx.SchedulerProvider
import io.reactivex.functions.Consumer

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

    val userAdapter = UserAdapter().apply {
        setItemClickListener(object : BaseRecyclerViewAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                val user = getItem(position)
                startActivity(
                    UserDetailActivity::class.java,
                    UserDetailActivity.createBundleExtra(user.user),
                    animation = TransactionAnimation.FROM_BOTTOM_TO_TOP
                )
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
    }

    fun onLogOutClicked() {
        userManager.signOut()
        startActivity(LoginActivity::class.java, finish = true, clearTask = true)
    }
}

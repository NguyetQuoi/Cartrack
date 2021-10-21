package com.example.cartrack.ui.list

import com.example.cartrack.R
import com.example.cartrack.base.BaseRecyclerViewAdapter

/**
 * Adapter for User recycle view
 * @author n.quoi
 * @date 10.21.2021
 */

class UserAdapter : BaseRecyclerViewAdapter<UserItemViewModel>() {
    override fun getLayoutId(viewType: Int): Int = R.layout.item_user
}
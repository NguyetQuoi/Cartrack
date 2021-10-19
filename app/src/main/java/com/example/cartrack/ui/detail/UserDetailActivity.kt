package com.example.cartrack.ui.detail

import android.location.Location
import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.cartrack.R
import com.example.cartrack.base.BaseActivity
import com.example.cartrack.base.BaseMapActivity
import com.example.cartrack.databinding.ActivityUserDetailBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * [UserDetailActivity] - show the detail of a location
 * @author n.quoi
 * @date 10.19.2021
 */

class UserDetailActivity : BaseActivity<UserDetailViewModel, ActivityUserDetailBinding>() {
    override val viewModel: UserDetailViewModel by viewModel()

    override val layoutId: Int = R.layout.activity_user_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.showToast("haha")
    }
}

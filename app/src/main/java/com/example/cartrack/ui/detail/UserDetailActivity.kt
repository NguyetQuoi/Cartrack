package com.example.cartrack.ui.detail

import android.graphics.Color
import android.location.Location
import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.cartrack.R
import com.example.cartrack.base.BaseActivity
import com.example.cartrack.base.BaseMapActivity
import com.example.cartrack.data.model.UserObject
import com.example.cartrack.databinding.ActivityUserDetailBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * [UserDetailActivity] - show the detail of a location
 * @author n.quoi
 * @date 10.19.2021
 */

class UserDetailActivity : BaseMapActivity<UserDetailViewModel, ActivityUserDetailBinding>() {
    override val viewModel: UserDetailViewModel by viewModel()

    override val layoutId: Int = R.layout.activity_user_detail

    var user: UserObject? = null
    var location: LatLng? = null

    companion object {
        private const val USER_EXTRA = "user_extra"
        fun createBundleExtra(user: UserObject): Bundle {
            return bundleOf(USER_EXTRA to user)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = bundle?.getSerializable(USER_EXTRA) as UserObject
        this.user = user
        this.location = LatLng(user.address.geo.lat.toDouble(), user.address.geo.lng.toDouble())
        viewModel.setData(user)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        super.onMapReady(googleMap)
        location?.let {
            val locationLatLng = LatLng(it.latitude, it.longitude)
            map?.addMarker(MarkerOptions().position(locationLatLng).title(user?.name))
            map?.moveCamera(CameraUpdateFactory.newLatLngZoom(locationLatLng, 3f))
        }
    }

    override fun locationEnabled() {}
    override fun lastLocationUpdated(latLng: LatLng) {}
}

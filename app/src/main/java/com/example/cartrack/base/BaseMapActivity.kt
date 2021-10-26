package com.example.cartrack.base

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import com.example.cartrack.R
import com.example.cartrack.util.PermissionUtils
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import timber.log.Timber


/**
 * A base map activity
 * @author n.quoi
 * @date 10.19.2021
 * @param VM view-model
 * @param VDB view-data-binding
 */

abstract class BaseMapActivity<VM : BindingViewModel, VDB : ViewDataBinding> :
    BaseActivity<VM, VDB>(),
    OnMapReadyCallback,
    ActivityCompat.OnRequestPermissionsResultCallback {

    protected var map: GoogleMap? = null

    var gpsStatus: Boolean = false

    private val myLocationRequestCode = 999

    private var locationRequest: LocationRequest = LocationRequest.create().apply {
        interval = 100
        fastestInterval = 50
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        maxWaitTime = 100
    }
    protected var lastLocation: MutableLiveData<LatLng> = MutableLiveData()
    private var fusedLocationClient: FusedLocationProviderClient? = null

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap
        map?.let {
            requestLocationPermission()
        }
    }

    abstract fun lastLocationUpdated(latLng: LatLng)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onPause() {
        fusedLocationClient?.removeLocationUpdates(locationCallback)
        super.onPause()
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient?.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.myLooper()
            )
        } else {
            showDialog(
                PermissionUtils.RationaleDialog.newInstance(
                    myLocationRequestCode, false,
                    R.string.location_request_sms, R.string.location_request_title
                ), "RationaleDialog"
            )
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == myLocationRequestCode) {
            if (permissions.size == 1 &&
                permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationClient?.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.myLooper()
                )
            } else {
                showDialog(
                    PermissionUtils.PermissionDeniedDialog.newInstance(
                        false,
                        R.string.location_permission_denied,
                        R.string.location_denied
                    ), "PermissionDeniedDialog"
                )
            }
        }
    }

    private var locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult?.locations?.let { result ->
                if (result.size > 0) {

                    //The last location in the list is the newest
                    val location = result[result.size - 1]
                    lastLocation.value = LatLng(location.latitude, location.longitude)
                    lastLocation.value?.let {
                        lastLocationUpdated(it)
                    }
                }
            }
        }
    }

    fun locationEnabled() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        gpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}

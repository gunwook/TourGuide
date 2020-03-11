package com.gunwook.tourguide.utils

import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.core.content.ContextCompat

class LocationUtils(var mContext : Context  , var callback : LocationCallback) : LocationListener {

    // 현재 GPS 사용유무
    var isGPSEnabled = false

    // 네트워크 사용유무
    var isNetworkEnabled = false

    // GPS 상태값
    var isGetLocation = false

    var location: Location? = null

    var lat : Double? = null
    var lon : Double? = null


    // 최소 GPS 정보 업데이트 거리 10미터
    private val MIN_DISTANCE_CHANGE_FOR_UPDATES: Float = 10f

    // 최소 GPS 정보 업데이트 시간 밀리세컨이므로 1분
    private val MIN_TIME_BW_UPDATES : Long= (1000 * 60 * 1).toLong()

    var locationManager: LocationManager? = null


    init {
        getLocation()
    }

    @TargetApi(23)
    fun getLocation(){
        if ( Build.VERSION.SDK_INT >= 23 &&
            ContextCompat.checkSelfPermission(
                mContext, android.Manifest.permission.ACCESS_FINE_LOCATION )
            != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            callback.onSuccess(false , this)
            return
        }

        try {
            locationManager = mContext
                .getSystemService(Context.LOCATION_SERVICE) as LocationManager

            // GPS 정보 가져오기
            isGPSEnabled = locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) ?: false

            // 현재 네트워크 상태 값 알아오기
            isNetworkEnabled = locationManager?.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ?: false

            if (!isGPSEnabled && !isNetworkEnabled) {
                // GPS 와 네트워크사용이 가능하지 않을때 소스 구현
                mContext.startActivity(Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                callback.onSuccess(false , this)
            } else {
                this.isGetLocation = true
                // 네트워크 정보로 부터 위치값 가져오기 (gps 가져올경우 느림)
                if (isNetworkEnabled) {
                    locationManager?.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this)

                    if (locationManager != null) {
                        location = locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                        if (location != null) {
                            // 위도 경도 저장
                            lat = location?.latitude
                            lon = location?.longitude
                        }
                    }
                }

                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager?.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this)
                        if (locationManager != null) {
                            location = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                            if (location != null) {
                                lat = location?.latitude
                                lon = location?.longitude
                            }
                        }
                    }
                }
                callback.onSuccess(true , this)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            callback.onSuccess(false , this)
        }
    }

    /**
     * GPS 종료
     */
    fun stopUsingGPS() {
        locationManager?.removeUpdates(this@LocationUtils)
    }

    /**
     * 위도값을 가져옵니다.
     */
    fun getLatitude(): Double? {
        location?.let {
            lat = it.latitude
        }
        return lat
    }

    /**
     * 경도값을 가져옵니다.
     */
    fun getLongitude(): Double? {
        location?.let {
            lon = it.longitude
        }
        return lon
    }


    /**
     * GPS 나 wife 정보가 켜져있는지 확인합니다.
     */
    fun isGetLocation(): Boolean? {
        return this.isGetLocation
    }

    /**
     * GPS 정보를 가져오지 못했을때
     * 설정값으로 갈지 물어보는 alert 창
     */
    fun showSettingsAlert() {
        val alertDialog = AlertDialog.Builder(mContext)

        alertDialog.setTitle("GPS 사용유무셋팅")
        alertDialog.setMessage("GPS 설정이 되어 있지 않습니다. \n 설정창으로 가시겠습니까?")

        // OK 를 누르게 되면 설정창으로 이동합니다.
        alertDialog.setPositiveButton("Settings",
            DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                mContext.startActivity(intent)
            })
        // Cancle 하면 종료 합니다.
        alertDialog.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        alertDialog.show()
    }
    override fun onLocationChanged(location: Location?) {

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

    interface LocationCallback  {
        fun onSuccess(isSuccess: Boolean , view : LocationUtils)
    }
}
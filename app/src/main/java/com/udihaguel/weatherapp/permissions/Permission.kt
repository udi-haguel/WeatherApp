package com.udihaguel.weatherapp.permissions

import android.Manifest.permission.*

sealed class Permission(vararg val permissions: String) {

    // Grouped permissions
    object Location : Permission(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)

    companion object {
        fun from(permission: String) = when (permission) {
            ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION -> Location
            else -> throw IllegalArgumentException("Unknown permission: $permission")
        }
    }
}
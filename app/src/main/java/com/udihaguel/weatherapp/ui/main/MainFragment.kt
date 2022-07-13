package com.udihaguel.weatherapp.ui.main

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.udihaguel.weatherapp.databinding.FragmentMainBinding
import com.udihaguel.weatherapp.permissions.Permission
import com.udihaguel.weatherapp.permissions.PermissionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*


@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private val permissionManager = PermissionManager.from(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater,container, false)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        collect()


    }

    override fun onResume() {
        super.onResume()
        checkForPermission()
    }


    private fun collect(){
        lifecycleScope.launch {
            viewModel.weatherDataStateFlow.collect{
                binding.tvCelsius.text = it.tempAsCelsius()
                binding.tvWeatherDescription.text = it.summary
                binding.tvLocationName.text = viewModel.getAddress(requireContext(), it.lat, it.lng)
                Picasso.get().load(it.getIconUrl()).into(binding.ivWeatherIcon)
            }
        }
    }




    private fun checkForPermission() {
        permissionManager
            // Check all permissions without bundling them
            .request(Permission.Location)
            .rationale("This app requires location permission")
            .checkDetailedPermission { result ->
                if (result.all { it.value }) {
                    viewModel.fetchData(32.1602438, 34.8073898)
                } else {
                    showErrorDialog(result)
                }
            }
    }



    private fun showErrorDialog(result: Map<Permission, Boolean>) {
        val message = result.entries.fold("") { message, entry ->
            message + "Missing location permission"
        }
        Log.i("TAG", message)
        AlertDialog.Builder(requireContext())
            .setTitle("Missing permissions")
            .setMessage(message)
            .setPositiveButton("Settings"
            ) { _, _ ->
                openSettings()
            }
            .show()
    }

    private fun openSettings(){
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromParts("package", activity?.packageName, null)
        intent.data = uri
        startActivity(intent)
    }

}
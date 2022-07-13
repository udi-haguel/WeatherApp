package com.udihaguel.weatherapp.ui.map

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import com.udihaguel.weatherapp.R
import com.udihaguel.weatherapp.databinding.FragmentMapsBinding
import com.udihaguel.weatherapp.model.WeatherData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapsFragment : Fragment() {

    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MapsViewModel

    private val callback = OnMapReadyCallback { googleMap ->
        binding.btnClearMap.setOnClickListener {
            binding.cvWeatherDetails.visibility = View.GONE
            googleMap.clear()
        }

        googleMap.setOnMapClickListener {
            googleMap.clear()
            googleMap.addMarker(MarkerOptions().position(it))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(it))
            viewModel.fetchData(it.latitude, it.longitude)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MapsViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        collect()
    }

    private fun collect(){
        lifecycleScope.launch {
            viewModel.markerLocationSharedFlow.collect{
                updateUi(it)
            }
        }
    }

    private fun updateUi(weather: WeatherData) {
        binding.tvLocationNameInMap.text = viewModel.getAddress(requireContext(), weather.lat, weather.lng)
        binding.tvCelsiusAndDescriptionInMap.text = "${weather.tempAsCelsius()}, ${weather.summary}"
        Picasso.get().load(weather.getIconUrl()).into(binding.ivWeatherIconMap)
        binding.cvWeatherDetails.visibility = View.VISIBLE
    }
}

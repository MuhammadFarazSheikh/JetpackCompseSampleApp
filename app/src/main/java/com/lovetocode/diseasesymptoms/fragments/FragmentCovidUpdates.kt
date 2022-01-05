package com.lovetocode.diseasesymptoms.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import com.lovetocode.diseasesymptoms.R
import com.lovetocode.diseasesymptoms.databinding.FragmentCovidUpdatesBinding
import com.lovetocode.diseasesymptoms.models.CountryCovidUpdatesDAO
import com.lovetocode.diseasesymptoms.viewmodels.CountryCovidUpdatesViewModel
import com.montymobile.callsignature.networking.Resource
import com.montymobile.callsignature.utils.KeyUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@AndroidEntryPoint
class FragmentCovidUpdates : Fragment(),Observer<Resource<ArrayList<CountryCovidUpdatesDAO>>> {

    val viewModel:CountryCovidUpdatesViewModel by viewModels()
    private lateinit var binding:FragmentCovidUpdatesBinding
    private lateinit var mContext: Context
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCovidUpdatesBinding.inflate(layoutInflater)
        mContext = binding.root.context
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupUIData(countryCovidUpdatesDAO: CountryCovidUpdatesDAO)
    {
        binding.dataDTO = countryCovidUpdatesDAO

        lifecycleScope.launchWhenCreated {
            val mapFragment: SupportMapFragment =
                childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
            googleMap=mapFragment.awaitMap()
            googleMap.addMarker {
                title(countryCovidUpdatesDAO.country)
                position(LatLng(
                    countryCovidUpdatesDAO.latitude.toDouble(),countryCovidUpdatesDAO.longitude.toDouble()
                ))
            }
            googleMap.apply {
                moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(
                            countryCovidUpdatesDAO.latitude.toDouble(),countryCovidUpdatesDAO.longitude.toDouble()
                        ),
                        50f
                    )
                )
                uiSettings.setAllGesturesEnabled(false)
            }
        }
    }

    private fun setupListeners()
    {
        arguments?.getString(KeyUtils.SELECTED_COUNTRY_NAME)?.let {
                viewModel.getCovidUpdatesByCountryName(it).observe(viewLifecycleOwner,this)
            }
    }

    override fun onChanged(t: Resource<ArrayList<CountryCovidUpdatesDAO>>) {
        when(t)
        {
            is Resource.Success->
            {
                setupUIData(t.value.first())
            }
            is Resource.Failure->
            {

            }
        }
    }
}
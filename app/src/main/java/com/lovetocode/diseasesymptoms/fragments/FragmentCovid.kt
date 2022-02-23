package com.lovetocode.diseasesymptoms.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.gms.maps.model.LatLng
import com.lovetocode.diseasesymptoms.R
import com.lovetocode.diseasesymptoms.adapters.CovidDataAdapter
import com.lovetocode.diseasesymptoms.databinding.FragmentCovidBinding
import com.lovetocode.diseasesymptoms.interfaces.OnLocationSelected
import com.lovetocode.diseasesymptoms.utils.*
import com.lovetocode.diseasesymptoms.viewmodels.CommonViewModel
import com.montymobile.callsignature.networking.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class FragmentCovid : Fragment(), View.OnClickListener,OnLocationSelected {

    private lateinit var binding: FragmentCovidBinding
    private lateinit var mContext: Context
    val commonViewModel:CommonViewModel by viewModels()

    @Inject
    lateinit var mostCommon: CovidDataAdapter

    @Inject
    lateinit var lessCommon: CovidDataAdapter

    @Inject
    lateinit var serious: CovidDataAdapter

    @Inject
    lateinit var covidPreventions: CovidDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentCovidBinding.inflate(layoutInflater)
        mContext = binding.root.context
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstances()
        populateSymptomsList()
        setupListeners()

        binding.isShowData = false

        if (RuntimePermissionUtils.checkIfLocationPermissionsGranted(mContext)) {
            LocationUtils.getLiveLocation(mContext,this)
        } else {
            var permissionsRequest =
                registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions(),
                    ActivityResultCallback {
                        var check = false
                        it.values.forEach {
                            check = it
                        }
                        if (check) {
                            LocationUtils.getLiveLocation(mContext,this)
                        }
                    })
            RuntimePermissionUtils.grantLocationPermission(permissionsRequest)
        }
    }

    private fun initInstances() {
        binding.mostCommon = mostCommon
        binding.lessCommon = lessCommon
        binding.serious = serious
        binding.covidPreventions = covidPreventions
    }

    private fun getDataForCovid()
    {
        lifecycleScope.launch {
            PreferenceDataStoreUtils.readLatLngData(mContext).let {
                if (!it.isNullOrEmpty() && !it.startsWith("null")) {
                    LocationUtils.getCountryNameByLocation(
                        mContext, LatLng(
                            it.split("/").get(0).toDouble(), it.split("/").get(1).toDouble())
                    )
                        .let {countryName ->
                            if(!it.isNullOrEmpty())
                            {
                                    /*commonViewModel.getCovidUpdatesByCountryName(countryName).observe(viewLifecycleOwner)
                                    {
                                        when(it)
                                        {
                                            is Resource.Success->
                                            {
                                                if(!it.value.isNullOrEmpty())
                                                {
                                                    binding.dataDTO = it.value.get(0)
                                                    binding.isShowData = true
                                                }
                                            }
                                        }
                                    }*/
                            }
                        }
                }
            }
        }
    }

    private fun setupListeners() {
        binding.btnCovidUpdates.setOnClickListener(this)
    }

    private fun populateSymptomsList() {
        mostCommon.addItems(CommonUtils.getMostCommonSymptomsList(mContext))
        lessCommon.addItems(CommonUtils.getLessCommonSymptomsList(mContext))
        serious.addItems(CommonUtils.getSeriousSymptomsList(mContext))
        covidPreventions.addItems(CommonUtils.getCovidPreventionsList(mContext))
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnCovidUpdates -> v.findNavController().navigate(R.id.countoriesListFragment)
        }
    }

    override fun onLocationSelected() {
        getDataForCovid()
    }
}
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
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.work.*
import com.lovetocode.diseasesymptoms.R
import com.lovetocode.diseasesymptoms.adapters.CovidDataAdapter
import com.lovetocode.diseasesymptoms.backgroundprocesses.LocationWorkManager
import com.lovetocode.diseasesymptoms.databinding.FragmentCovidBinding
import com.lovetocode.diseasesymptoms.utils.CommonUtils
import com.lovetocode.diseasesymptoms.utils.RuntimePermissionUtils
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class FragmentCovid : Fragment(), View.OnClickListener, Observer<WorkInfo> {

    private lateinit var binding: FragmentCovidBinding
    private lateinit var mContext: Context

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

        if (RuntimePermissionUtils.checkIfLocationPermissionsGranted(mContext)) {
            WorkManager.getInstance(mContext)
                .enqueue(OneTimeWorkRequestBuilder<LocationWorkManager>().build().apply {
                    WorkManager.getInstance(mContext).getWorkInfoByIdLiveData(id).observe(viewLifecycleOwner,this@FragmentCovid)
                })
        } else {
            var permissionsRequest =
                registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions(),
                    ActivityResultCallback {
                        var check = false
                        it.values.forEach {
                            check = it
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

    override fun onChanged(inWorkInfo: WorkInfo) {
        inWorkInfo.let {
            it.progress
        }
    }
}
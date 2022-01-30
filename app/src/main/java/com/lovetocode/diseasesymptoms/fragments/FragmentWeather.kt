package com.lovetocode.diseasesymptoms.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lovetocode.diseasesymptoms.R
import com.lovetocode.diseasesymptoms.viewmodels.CommonViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@AndroidEntryPoint
class FragmentWeather : Fragment() {

    val viewModel:CommonViewModel by viewModels()
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstances()
        compositeDisposable.add(
            viewModel.getData("Pakistan")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { onSuccess, onError ->
                    if(onSuccess!=null)
                    {
                    }
                    if(onError!=null)
                    {

                    }
                }
        )
    }

    private fun initInstances()
    {
        compositeDisposable = CompositeDisposable()
    }

    override fun onDestroy() {

        if(::compositeDisposable.isInitialized)
        {
            compositeDisposable.dispose()
        }

        super.onDestroy()
    }
}
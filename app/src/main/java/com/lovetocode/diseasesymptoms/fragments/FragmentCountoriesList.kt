package com.lovetocode.diseasesymptoms.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lovetocode.diseasesymptoms.adapters.CountoriesListAdapter
import com.lovetocode.diseasesymptoms.databinding.FragmentCountoriesListBinding
import com.lovetocode.diseasesymptoms.utils.CommonUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentCountoriesList : Fragment(),TextWatcher {

    private lateinit var mContext: Context
    private lateinit var array:List<String>
    private lateinit var binding:FragmentCountoriesListBinding
    @Inject lateinit var countoriesListAdapter: CountoriesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCountoriesListBinding.inflate(layoutInflater)
        mContext = binding.root.context
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstances()
        updateData()
        setupListeners()
    }

    private fun setupListeners()
    {
        binding.countoriesSearch.addTextChangedListener(this)
    }

    private fun initInstances()
    {
        binding.adapter = countoriesListAdapter
        array = CommonUtils.getCountoriesList(mContext)
    }

    private fun updateData()
    {
        var tempList = arrayListOf<String>()
        tempList.addAll(array)
        countoriesListAdapter.addItems(tempList)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        var tempList = arrayListOf<String>()
        if(!TextUtils.isEmpty(s.toString()))
        {
            for (i in array)
            {
                if(i.lowercase().equals(s.toString().lowercase()))
                {
                    tempList.add(i)
                }
            }
            countoriesListAdapter.addItems(tempList)
        }
        else
        {
            tempList.addAll(array)
            countoriesListAdapter.addItems(tempList)
        }
    }

    override fun afterTextChanged(s: Editable?) {
    }
}
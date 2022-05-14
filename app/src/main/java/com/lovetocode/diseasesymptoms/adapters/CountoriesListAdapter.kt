package com.lovetocode.diseasesymptoms.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lovetocode.diseasesymptoms.R
import com.lovetocode.diseasesymptoms.databinding.RowRecyclerCountoriesListBinding
import com.montymobile.callsignature.utils.KeyUtils

class CountoriesListAdapter (): RecyclerView.Adapter<CountoriesListAdapter.DataViewHolder>(),
    View.OnClickListener
{
    var list = arrayListOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(RowRecyclerCountoriesListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.rowRecyclerCountoriesList.countoryName = list.get(position)
        holder.rowRecyclerCountoriesList.countoryData.setTag(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class DataViewHolder(var rowRecyclerCountoriesList:RowRecyclerCountoriesListBinding): RecyclerView.ViewHolder(rowRecyclerCountoriesList.root)
    {
        init {
            setupListeners()
        }

        private fun setupListeners()
        {
            rowRecyclerCountoriesList.countoryData.setOnClickListener(this@CountoriesListAdapter)
        }
    }

    public fun clear()
    {
        list.clear()
    }

    public fun updateData()
    {
        notifyDataSetChanged()
    }

    public fun addItems(list: List<String>)
    {
        clear()
        this.list.addAll(list)
        updateData()
    }

    override fun onClick(v: View) {
        when(v.id)
        {
            R.id.countoryData -> {
                v.findNavController().navigate(R.id.covidUpdatesFragment,bundleOf(KeyUtils.SELECTED_COUNTRY_NAME to list.get(v.getTag() as Int)))
            }
        }
    }
}
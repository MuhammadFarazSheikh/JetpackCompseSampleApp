package com.lovetocode.diseasesymptoms.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lovetocode.diseasesymptoms.databinding.RowRecyclerCovidDataBinding

class CovidDataAdapter constructor(): RecyclerView.Adapter<CovidDataAdapter.DataViewHolder>()
{
    var list = arrayListOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(RowRecyclerCovidDataBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.rowRecyclerDiseasesListBinding.symptomName = list.get(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class DataViewHolder(var rowRecyclerDiseasesListBinding: RowRecyclerCovidDataBinding): RecyclerView.ViewHolder(rowRecyclerDiseasesListBinding.root)

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
}
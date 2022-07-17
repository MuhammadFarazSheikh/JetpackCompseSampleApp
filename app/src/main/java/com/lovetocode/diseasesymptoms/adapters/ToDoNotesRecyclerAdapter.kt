package com.lovetocode.diseasesymptoms.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lovetocode.diseasesymptoms.databinding.ItemTodoNotesBinding
import com.lovetocode.diseasesymptoms.models.ToDoNotesDTO
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ToDoNotesRecyclerAdapter @Inject constructor(): PagingDataAdapter<ToDoNotesDTO, ToDoNotesRecyclerAdapter.DataViewHolder>(DIFF_CALLBACK)
{
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ToDoNotesDTO>() {
            override fun areItemsTheSame(oldItem: ToDoNotesDTO, newItem: ToDoNotesDTO): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ToDoNotesDTO, newItem: ToDoNotesDTO): Boolean =
                oldItem.equals(newItem)
        }
    }

    inner class DataViewHolder(var itemToDoNotesBinding:ItemTodoNotesBinding): RecyclerView.ViewHolder(itemToDoNotesBinding.root)

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        getItem(position)?.let { todoNotes->
            holder.itemToDoNotesBinding.text = todoNotes.noteText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(ItemTodoNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}
package com.example.journalkotlinapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.journalkotlinapp.databinding.ActivityJournalListBinding
import com.example.journalkotlinapp.databinding.JournalItemBinding

class JournalRecyclerAdapter(val context: Context,  var journalList: List<Journal>) :
    RecyclerView.Adapter<JournalRecyclerAdapter.MyViewHolder>() {

    lateinit var binding: JournalItemBinding


    class MyViewHolder(var binding: JournalItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(journal: Journal){
            binding.journal = journal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //val view: View = LayoutInflater.from(context)
        //    .inflate(R.layout.journal_item, parent, false)

        binding = JournalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return journalList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val journal: Journal = journalList[position]
        holder.bind(journal)


    }
}
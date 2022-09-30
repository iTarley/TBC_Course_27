package com.example.tbc_course_27.ui.adapter

import android.os.Build.VERSION_CODES.N
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_course_27.R
import com.example.tbc_course_27.databinding.GridViewBinding
import com.example.tbc_course_27.domain.model.Numbers


typealias onClick = (user: Numbers) -> Unit
typealias onRemoveClick = (user: Numbers) -> Unit

class NumberAdapter(private val content: List<Numbers>) :
    RecyclerView.Adapter<NumberAdapter.ViewHolder>() {

    lateinit var onClick: onClick
    lateinit var onRemoveClick: onRemoveClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberAdapter.ViewHolder =
        ViewHolder(
            GridViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )




    override fun onBindViewHolder(holder: NumberAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return content.size
    }

    inner class ViewHolder(private val binding: GridViewBinding) :
        RecyclerView.ViewHolder(binding.root) {


        private lateinit var currentItem: Numbers
        fun bind() {
            currentItem = content[adapterPosition]
            binding.apply {
                if (currentItem.sensor){
                    binding.imageButton.text = ""
                    binding.imageButton.setBackgroundResource(R.drawable.touch)
                }
                else if (currentItem.remove){
                    binding.imageButton.text = ""
                    binding.imageButton.setBackgroundResource(R.drawable.group)
                    binding.imageButton.setOnClickListener {
                        onRemoveClick(
                            currentItem
                        )
                    }
                }else{
                    binding.imageButton.text = currentItem.number.toString()
                    binding.imageButton.setOnClickListener {
                        onClick(
                            currentItem
                        )
                    }
                }

            }
        }
    }
}
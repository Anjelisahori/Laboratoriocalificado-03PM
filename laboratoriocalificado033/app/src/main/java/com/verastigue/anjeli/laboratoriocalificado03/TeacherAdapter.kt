package com.verastigue.anjeli.laboratoriocalificado03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.verastigue.anjeli.laboratoriocalificado03.databinding.ItemTeacherBinding

class TeacherAdapter(
    var teachers: List<Teacher>,
    val onTeacherClick: (Teacher) -> Unit,
    val onTeacherLongClick: (Teacher) -> Unit
) : RecyclerView.Adapter<TeacherAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(teacher: Teacher) {
            binding.tvName.text = "${teacher.name} ${teacher.lastName}"
            binding.tvEmail.text = teacher.email

            Glide.with(itemView.context)
                .load(teacher.imageUrl)
                .into(binding.ivPhoto)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return teachers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val teacher = teachers[position]
        holder.bind(teacher)

        holder.itemView.setOnClickListener {
            onTeacherClick(teacher)
        }

        holder.itemView.setOnLongClickListener {
            onTeacherLongClick(teacher)
            true
        }
    }
}
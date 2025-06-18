package com.verastigue.anjeli.laboratoriocalificado03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.verastigue.anjeli.laboratoriocalificado03.databinding.ItemProfesorBinding

class TeacherAdapter(
    private val teachers: List<Teacher>,
    private val onClick: (Teacher) -> Unit,
    private val onLongClick: (Teacher) -> Unit
) : RecyclerView.Adapter<TeacherAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemProfesorBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProfesorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val teacher = teachers[position]
        with(holder.binding) {
            textViewNombre.text = teacher.nombre
            textViewApellido.text = teacher.apellido
            Glide.with(imageViewFoto.context).load(teacher.foto).into(imageViewFoto)

            root.setOnClickListener { onClick(teacher) }
            root.setOnLongClickListener {
                onLongClick(teacher)
                true
            }
        }
    }

    override fun getItemCount(): Int = teachers.size
}

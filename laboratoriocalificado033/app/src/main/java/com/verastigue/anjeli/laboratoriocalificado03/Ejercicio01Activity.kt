package com.verastigue.anjeli.laboratoriocalificado03

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.verastigue.anjeli.laboratoriocalificado03.databinding.ActivityEjercicio01Binding

class Ejercicio01Activity : AppCompatActivity() {
    private lateinit var binding: ActivityEjercicio01Binding
    private lateinit var viewModel: Ejercicio01ViewModel
    private lateinit var adapter: TeacherAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(Ejercicio01ViewModel::class.java)
        setupRecyclerView()
        observeViewModel()
    }
    private fun setupRecyclerView() {
        adapter = TeacherAdapter(
            emptyList(),
            onTeacherClick = { teacher ->
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${teacher.phone}"))
                startActivity(intent)
            },
            onTeacherLongClick = { teacher ->
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(teacher.email))
                    putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                } else {
                    showMessage(getString(R.string.email_app_not_found))
                }
            }
        )
        binding.rvTeachers.layoutManager = LinearLayoutManager(this)
        binding.rvTeachers.adapter = adapter
    }
    private fun observeViewModel() {
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }
        viewModel.teacherList.observe(this) { teachers ->
            adapter.teachers = teachers
            adapter.notifyDataSetChanged()
        }
        viewModel.errorApi.observe(this) { error ->
            showMessage(error)
        }
    }
    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
package com.alinecruz.to_do_list_dio.presentation.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alinecruz.to_do_list_dio.R
import com.alinecruz.to_do_list_dio.databinding.ActivityNewTaskBinding
import com.alinecruz.to_do_list_dio.datasource.TaskDataSource
import com.alinecruz.to_do_list_dio.domain.entities.Task
import com.alinecruz.to_do_list_dio.utils.format
import com.alinecruz.to_do_list_dio.utils.text
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class NewTaskActivity : AppCompatActivity() {

    companion object {
        const val ID_TASK = "id_task"
    }

    private lateinit var binding: ActivityNewTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(ID_TASK)) {
            val idNewTask = intent.getIntExtra(ID_TASK, 0)
            TaskDataSource.findById(idNewTask)?.let {
                binding.inputLytNewTaskTitle.text = it.title.toString()
                binding.inputLytNewTaskDescription.text = it.description.toString()
                binding.inputLytNewTaskDate.text = it.date.toString()
                binding.inputLytNewTaskHour.text = it.hour.toString()

                binding.buttonNewTaskCreate.setText(getString(R.string.label_all_update_task))
            }
        }

        setupToolbar()
        setupListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarNewTask)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupListeners() {
        binding.inputLytNewTaskDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()

            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.inputLytNewTaskDate.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        binding.inputLytNewTaskHour.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            timePicker.addOnPositiveButtonClickListener {
                val minute: String =
                    if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute.toString()
                val hour: String =
                    if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour.toString()

                binding.inputLytNewTaskHour.text = "$hour:$minute"
            }

            timePicker.show(supportFragmentManager, null)
        }

        binding.buttonNewTaskCancel.setOnClickListener {
            onBackPressed()
        }

        binding.buttonNewTaskCreate.setOnClickListener {
            val task = Task(
                id = intent.getIntExtra(ID_TASK, 0),
                title = binding.inputEdtNewTaskTitle.text,
                description = binding.inputEdtNewTaskDescription.text,
                hour = binding.inputEdtNewTaskHour.text,
                date = binding.inputEdtNewTaskDate.text
            )

            TaskDataSource.insertTask(task)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

}



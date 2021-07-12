package com.alinecruz.to_do_list_dio.domain.entities

import android.text.Editable

data class Task(val id: Int = 0, val title: Editable?, val description: Editable?, val date: Editable?, val hour: Editable?)

package com.alinecruz.to_do_list_dio.domain.entities

import android.text.Editable

data class Task(val id: Int = 0, val title: Editable?, val description: Editable?, val date: Editable?, val hour: Editable?) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}

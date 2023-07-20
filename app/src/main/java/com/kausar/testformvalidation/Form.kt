package com.kausar.testformvalidation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable


@Composable
fun Form(state: FormState, fields: List<Field>) {
    state.fields = fields

    Column {
        fields.forEach {
            it.Content()
        }
    }
}


class FormState {
    var fields: List<Field> = listOf()

    fun validate(): Boolean {
        var valid = true
        for (field in fields) {
            if (!field.validate()) {
                valid = false
                break
            }
        }
        return valid
    }

    fun getData(): Map<String, String> = fields.map { it.name to it.text }.toMap()
}
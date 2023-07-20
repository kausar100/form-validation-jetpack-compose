package com.kausar.testformvalidation

import android.util.Patterns
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

class Field(
    val name: String,
    val focusDirection: FocusDirection?,
    private val keyboardType: KeyboardType,
    private val imeAction: ImeAction,
    private val fieldType: FieldType,
    val validators: List<Validator>
) {
    var text: String by mutableStateOf("")
    var errorMsg: String by mutableStateOf("")
    var hasError: Boolean by mutableStateOf(false)

    fun clear() {
        text = ""
    }

    private fun showError(error: String) {
        hasError = true
        errorMsg = error
    }

    private fun hideError() {
        errorMsg = ""
        hasError = false
    }

    @Composable
    fun Content() {
        val focusManager = LocalFocusManager.current
        TitleAndInputField(
            title = name,
            value = text,
            placeholderContent = "Enter $name",
            onInputChanged = {
                hideError()
                text = it
            },
            onChangeCompleted = {
                if (focusDirection != null) {
                    focusManager.moveFocus(focusDirection)
                } else {
                    focusManager.clearFocus(true)
                }
            },
            keyboardType = keyboardType,
            imeAction = imeAction,
            showError = hasError,
            errorMessage = errorMsg,
            type = fieldType
        )
    }

    fun validate(): Boolean {
        return validators.map {
            when (it) {
                is Email -> {
                    if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                        showError(it.message)
                        return@map false
                    }
                    true
                }

                is Required -> {
                    if (text.isEmpty()) {
                        showError(it.message)
                        return@map false
                    }
                    true
                }

                is Regex -> {
                    if (!it.regex.toRegex().containsMatchIn(text)) {
                        showError(it.message)
                        return@map false
                    }
                    true
                }

                is FixedLength -> {
                    if (text.length != it.length) {
                        showError(it.message)
                        return@map false
                    }
                    true
                }
            }
        }.all { it }
    }
}
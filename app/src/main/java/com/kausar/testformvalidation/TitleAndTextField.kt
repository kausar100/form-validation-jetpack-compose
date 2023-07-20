package com.kausar.testformvalidation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class FieldType{
    text, number, password
}

@Composable
fun TitleAndInputField(
    title: String,
    value: String,
    placeholderContent: String = "",
    modifier: Modifier = Modifier,
    onInputChanged: (String) -> Unit,
    onChangeCompleted: () -> Unit,
    editable: Boolean = true,
    fromRegistration: Boolean = false,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    showError: Boolean = false,
    errorMessage: String? = null,
    type: FieldType,
) {

    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = if (fromRegistration) 20.sp else 14.sp,
                fontWeight = FontWeight.Bold
            ),
        )
        CustomTextFields(
            inputValue = value,
            placeHolderText = placeholderContent,
            onInputChanged = onInputChanged,
            editable = editable,
            showError = showError,
            isPassField = type == FieldType.password,
            onChangeCompleted = onChangeCompleted,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
        )
        if (showError && errorMessage != null) {
            Text(
                text = errorMessage,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

    }
}
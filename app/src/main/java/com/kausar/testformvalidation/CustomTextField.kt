package com.kausar.testformvalidation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextFields(
    inputValue: String,
    editable: Boolean,
    showError: Boolean = false,
    placeHolderText: String = "",
    isPassField: Boolean = false,
    onInputChanged: (String) -> Unit,
    onChangeCompleted: () -> Unit,
    keyboardOptions: KeyboardOptions,
) {
    var showPassword by rememberSaveable {
        mutableStateOf(false)
    }
    BoxWithConstraints(
        modifier = Modifier
            .clipToBounds()
    ) {

        TextField(
            value = inputValue,
            enabled = editable,
            onValueChange = onInputChanged,
            singleLine = true,
            maxLines = 1,
            readOnly = !editable,
            visualTransformation = if (isPassField) {
                if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
            } else VisualTransformation.None,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions {
                onChangeCompleted()
            },
            placeholder = {

                Text(text = placeHolderText, color = Color.Gray)

            },
            trailingIcon = {
                if (isPassField) {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "show_password"
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { showPassword = true }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "hide_password"
                            )
                        }
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = if(editable) Color.Black else Color.Gray,
                disabledTextColor = Color.Gray,
                containerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Gray,
                focusedIndicatorColor = Color.DarkGray,

                ),
            isError = showError,
            modifier = Modifier
                .requiredWidth(maxWidth+16.dp)
                .offset(x = (-8).dp)
        )
    }


}
package com.kausar.testformvalidation

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat.FocusDirection
import com.kausar.testformvalidation.Regex
import com.kausar.testformvalidation.ui.theme.TestFormValidationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestFormValidationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val state by remember {
        mutableStateOf(FormState())
    }

    val context = LocalContext.current

    Column(Modifier.padding(horizontal = 16.dp)) {
        Form(
            state = state,
            fields = listOf(
                Field(
                    name = "Username",
                    focusDirection = androidx.compose.ui.focus.FocusDirection.Next,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    fieldType = FieldType.text,
                    validators = listOf(Required(message = "Please enter your username"))
                ),
                Field(
                    name = "Email",
                    focusDirection = androidx.compose.ui.focus.FocusDirection.Next,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    fieldType = FieldType.text,
                    validators = listOf(Required(message = "Please enter your email"), Email(message = "Please enter valid email address"))
                ),
                Field(
                    name = "Contact Number",
                    focusDirection = androidx.compose.ui.focus.FocusDirection.Next,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                    fieldType = FieldType.number,
                    validators = listOf(
                        Required(),
                        Regex(message = "Please enter a valid contact number", regex = "^01[0-9]*$"),
                        FixedLength(11, message = "Contact number should be exact 11 digit long!")
                    )
                ),
                Field(
                    name = "Server Address",
                    focusDirection = null,
                    keyboardType = KeyboardType.Ascii,
                    imeAction = ImeAction.Next,
                    fieldType = FieldType.text,
                    validators = listOf(Required(), Regex(message = "This is not a valid server address",regex = "(?i:http|https|rtsp)://"))
                )
            )
        )
        Box(Modifier.padding(16.dp)) {
            Button(onClick = {
                if (state.validate()) {
                    println(state.getData().toString())
                    context.toast("Our form works!")
                }
            }) {
                Text("Submit")
            }
        }
    }

}

fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()



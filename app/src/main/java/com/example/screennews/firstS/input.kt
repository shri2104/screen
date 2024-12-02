package com.example.screennews.firstS


import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun InputF(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLines: Int = 2,
    onImeaction:()->Unit={},
    onTextChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTextChange,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        ),
        colors = TextFieldDefaults.colors(
            Color.White),
        maxLines=maxLines,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeaction()
            keyboardController?.hide()
        }),
        modifier = modifier
    )
}
@Composable
fun Button(modifier:Modifier=Modifier,
           text: String,
           onClick:()->Unit,
           enabled:Boolean=true){
    Button(onClick = onClick, shape = CircleShape, modifier = modifier, enabled = enabled) {
        Text(text)
    }

}

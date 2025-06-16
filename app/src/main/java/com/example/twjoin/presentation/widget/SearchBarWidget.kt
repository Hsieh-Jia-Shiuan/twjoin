package com.example.twjoin.presentation.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.twjoin.R
import com.example.twjoin.ui.theme.ProjectColor
import com.example.twjoin.ui.theme.ProjectTextStyle

@Composable
fun SearchBarWidget(
    modifier: Modifier = Modifier,
    onInput: (String) -> Unit,
) {
    var currentInputText by remember { mutableStateOf("") }
    var isFocusing by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    TextField(
        value = currentInputText,
        onValueChange = {
            currentInputText = it
            onInput(it)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Ascii,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onInput(currentInputText)
                focusManager.clearFocus()
            }
        ),
        modifier = modifier
            .fillMaxWidth()
            .border(border = BorderStroke(width = 1.dp, color = ProjectColor.Black5),
                shape = RoundedCornerShape(8.dp))
            .onFocusChanged {
                isFocusing = it.isFocused
            },
        placeholder = {
            Text(
                text = stringResource(R.string.search_placeholder),
                style = ProjectTextStyle.H8,
                color = ProjectColor.Black50
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )
        },
        trailingIcon = {
            if (isFocusing || currentInputText.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = null,
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .clickable {
                            currentInputText = ""
                            onInput("")
                        },
                )
            }
        },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
        )
    )
}

@Preview()
@Composable
fun SearchBarWidgetPreview() {
    SearchBarWidget(
        modifier = Modifier.fillMaxWidth(),
        onInput = {}
    )
}
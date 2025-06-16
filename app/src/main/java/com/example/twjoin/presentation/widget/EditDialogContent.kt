package com.example.twjoin.presentation.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.twjoin.R
import com.example.twjoin.ui.theme.ProjectColor
import com.example.twjoin.ui.theme.ProjectTextStyle

@Composable
fun EditDialogContent(
    originalValue: String,
    onClose: () -> Unit,
    onConfirm: (String) -> Unit,
) {
    var editedValue by remember { mutableStateOf(originalValue) }
    val focusRequester = remember { FocusRequester() }
    var isFocusing by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = editedValue,
                onValueChange = {
                    editedValue = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = ProjectColor.Black5
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        isFocusing = it.isFocused
                    },
                placeholder = {
                    Text(
                        text = originalValue,
                        style = ProjectTextStyle.H8,
                        color = ProjectColor.Black50
                    )
                },
                trailingIcon = {
                    if (isFocusing || editedValue.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = null,
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                                .clickable {
                                    editedValue = ""
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            ) {
                Btn(stringResource(R.string.btn_close), onClose)

                Btn(stringResource(R.string.btn_confirm)) {
                    onConfirm(editedValue)
                }
            }
        }
    }
}

@Composable
fun Btn(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = ProjectColor.Black5
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = text,
            style = ProjectTextStyle.H8,
            color = ProjectColor.Black
        )
    }
}
@Preview(showSystemUi = true)
@Composable
fun PreviewEditDialogContent() {
    EditDialogContent(
        originalValue = "123",
        onClose = {},
        onConfirm = {}
    )
}

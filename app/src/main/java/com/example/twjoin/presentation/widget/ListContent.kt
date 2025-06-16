package com.example.twjoin.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.twjoin.data.entities.ListEntity
import com.example.twjoin.presentation.ListState
import com.example.twjoin.ui.theme.ProjectColor

@Composable
fun ListContent(
    listState: ListState,
    onEditItem: (Int, String) -> Unit,
    onDeleteItem: (Int) -> Unit,
    onSearch: (String) -> Unit,
    onReload: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var itemToEdit by remember { mutableStateOf<ListEntity?>(null) }
    val focusManager = LocalFocusManager.current

    Scaffold(
        containerColor = ProjectColor.BackgroundGray,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding()
                    )
                    .background(ProjectColor.White)
            ) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    SearchBarWidget(
                        onInput = {
                            onSearch(it)
                        }
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = ProjectColor.Black5
                )
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
        ) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(listState.datas) { index, item ->
                    ListItemWidget(
                        data = item,
                        onEdit = {
                            showDialog = true
                            itemToEdit = item
                        },
                        onDelete = {
                            onDeleteItem(item.id)
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }

                if (listState.datas.isEmpty()) {
                    item {

                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .width(48.dp)
                                .height(48.dp)
                                .clickable {
                                    onReload()
                                },
                        )
                    }
                }
            }
        }
    }

    if (showDialog && itemToEdit != null) {
        Dialog(
            onDismissRequest = {
                showDialog = false
                itemToEdit = null
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = false
            )
        ) {
            EditDialogContent(
                originalValue = itemToEdit?.name ?: "",
                onClose = {
                    showDialog = false
                    itemToEdit = null
                },
                onConfirm = { newName ->
                    itemToEdit?.let {
                        onEditItem(it.id, newName)
                        showDialog = false
                        itemToEdit = null
                    }
                }
            )
        }
    }
}

@Preview()
@Composable
fun ListPagePreview() {
    val previewState = ListState(
        datas = listOf(
            ListEntity(id = 0, name = "預覽項目 A", description = ""),
            ListEntity(id = 1, name = "預覽項目 B", description = ""),
            ListEntity(id = 2, name = "預覽項目 C", description = "")
        )
    )
    ListContent(
        listState = previewState,
        onEditItem = { _, _ -> },
        onDeleteItem = { _ -> },
        onSearch = {},
        onReload = {}
    )
}

@Preview()
@Composable
fun ListPageEmptyPreview() {

    ListContent(
        listState = ListState(datas = listOf()),
        onEditItem = { _, _ -> },
        onDeleteItem = { _ -> },
        onSearch = {},
        onReload = {}
    )
}
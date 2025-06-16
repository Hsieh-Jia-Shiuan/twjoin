package com.example.twjoin.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.example.twjoin.presentation.widget.ListContent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ListPage(
    viewModel: ListViewModel = koinViewModel<ListViewModel>()
) {
    val state = viewModel.container.stateFlow.collectAsState()
    val scope = rememberCoroutineScope()

    ListContent(
        listState = state.value,
        onEditItem = { index, newName ->
            scope.launch {
                viewModel.editData(index, newName)
            }
        },
        onDeleteItem = { index ->
            scope.launch {
                viewModel.deleteData(index)
            }
        },
        onSearch = { keyword ->
            scope.launch {
                viewModel.searchData(keyword)
            }
        },
        onReload = {
            scope.launch {
                viewModel.reloadData()
            }
        }
    )
}
package com.example.twjoin.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.twjoin.ui.theme.ProjectColor
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ListPage(
    viewModel: ListViewModel = koinViewModel<ListViewModel>()
) {
    Scaffold(
        modifier = Modifier
            .background(ProjectColor.White),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { padding ->
        val state = viewModel.collectAsState()
        Column(
            modifier = Modifier.padding(padding)
        ) {
            state.value.datas
        }

    }
}
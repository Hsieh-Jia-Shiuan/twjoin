package com.example.twjoin

import com.example.twjoin.domain.use_case.GetListUseCase
import com.example.twjoin.presentation.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListViewModel(get()) }
}

val useCaseModule = module {
    factory { GetListUseCase() }
}
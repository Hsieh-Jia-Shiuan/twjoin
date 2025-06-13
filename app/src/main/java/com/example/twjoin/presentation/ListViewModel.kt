package com.example.twjoin.presentation

import androidx.lifecycle.ViewModel
import com.example.twjoin.data.entities.ListEntity
import com.example.twjoin.domain.use_case.GetListUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.Syntax

data class ListState (
    val datas: List<ListEntity>
)

data object ListEffect

abstract class ListViewModel(private val getListUseCase: GetListUseCase): ViewModel(), ContainerHost<ListState, ListEffect>{
    suspend fun Syntax<ListState, ListEffect>.getDate() {
        val newData = getListUseCase.invoke()

        reduce{
            state.copy(datas = newData)
        }
    }
}

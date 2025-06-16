package com.example.twjoin.presentation

import androidx.lifecycle.ViewModel
import com.example.twjoin.data.entities.ListEntity
import com.example.twjoin.domain.use_case.GetListUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

data class ListState(
    val datas: List<ListEntity>
)

data object ListEffect

class ListViewModel(
    private val getListUseCase: GetListUseCase,
) : ViewModel(), ContainerHost<ListState, ListEffect> {
    private var originalAllDatas: List<ListEntity> = emptyList()

    override val container: Container<ListState, ListEffect> = container(
        initialState = ListState(
            datas = emptyList()
        )
    ) {
        loadInitialData()
    }

    private fun loadInitialData() = intent {
        val loadedData = getListUseCase.invoke()
        originalAllDatas = loadedData
        reduce {
            state.copy(datas = loadedData)
        }
    }

    fun editData(index: Int, newName: String) = intent {
        val updatedList = state.datas.map { item ->
            if (item.id == index) {
                item.copy(name = newName)
            } else {
                item
            }
        }

        originalAllDatas = updatedList
        reduce {
            state.copy(datas = updatedList)
        }
    }


    fun deleteData(index: Int) = intent {
        val newData = state.datas.toMutableList()
        newData.removeIf { it.id == index }
        originalAllDatas = newData

        reduce {
            state.copy(datas = newData)
        }
    }

    fun searchData(keyword: String) = intent {
        val filteredData = if (keyword.isBlank()) {
            originalAllDatas
        } else {
            originalAllDatas.filter { it.name.contains(keyword, ignoreCase = true) }
        }
        reduce {
            state.copy(datas = filteredData)
        }
    }

    fun reloadData() = intent {
        loadInitialData()
    }
}

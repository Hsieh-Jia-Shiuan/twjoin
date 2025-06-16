package com.example.twjoin.domain.use_case

import com.example.twjoin.data.database.ListRepository
import com.example.twjoin.data.entities.ListEntity

class GetListUseCase(private val listRepository: ListRepository): IGetListUseCase {
    override operator fun invoke() : List<ListEntity> {
        val freshList = listRepository.getMockListData()
        return freshList
    }
}
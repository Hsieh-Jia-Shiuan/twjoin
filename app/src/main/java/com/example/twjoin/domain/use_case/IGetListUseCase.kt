package com.example.twjoin.domain.use_case

import com.example.twjoin.data.entities.ListEntity

interface IGetListUseCase {
    operator fun invoke(): List<ListEntity>
}
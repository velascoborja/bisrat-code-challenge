package com.touch_surgery.digital_surgery.domain.usecase.favourites

import com.touch_surgery.digital_surgery.domain.repository.ProcedureRepository


class UpdateFavouriteUseCase(
    private val repository: ProcedureRepository
) {
    suspend operator fun invoke(uuid: String, isFavorite: Boolean) {
        repository.updateFavourite(uuid, isFavorite)
    }
}
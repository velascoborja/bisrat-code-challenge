package com.touch_surgery.digital_surgery.presentation.procedureDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.touch_surgery.digital_surgery.domain.repository.GetSingleProcedureUseCase
import com.touch_surgery.digital_surgery.domain.usecase.favourites.UpdateFavouriteUseCase
import com.touch_surgery.digital_surgery.domain.usecase.procedureDetail.FetchProcedureDetailsFromApiUseCase
import com.touch_surgery.digital_surgery.domain.usecase.procedureDetail.GetProcedureDetailUseCase
import com.touch_surgery.digital_surgery.presentation.state.ProcedureDetailState
import com.touch_surgery.utils.ResultWrapper.Error
import com.touch_surgery.utils.ResultWrapper.Loading
import com.touch_surgery.utils.ResultWrapper.Success
import com.touch_surgery.utils.getErrorResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProceduresDetailViewModel(
    private val fetchProcedureDetailsFromApiUseCase: FetchProcedureDetailsFromApiUseCase,
    private val getProcedureDetailUseCase: GetProcedureDetailUseCase,
    private val updateFavouriteUseCase: UpdateFavouriteUseCase,
    private val getSingleProcedureUseCase: GetSingleProcedureUseCase
) : ViewModel() {

    private val _procedureDetailState = MutableStateFlow(ProcedureDetailState())
    val procedureDetailState: StateFlow<ProcedureDetailState> = _procedureDetailState


    fun loadProcedureDetailFromDB(procedureID: String) {
        viewModelScope.launch {
            _procedureDetailState.value = _procedureDetailState.value.copy(loading = true)
            try {
                getProcedureDetailUseCase(procedureID).collect { (procedure,procedureDetail) ->

                    if (procedureDetail != null && procedure != null){
                        _procedureDetailState.value = _procedureDetailState.value.copy(
                            procedureDetail = procedureDetail,
                            procedure = procedure,
                            loading = false
                        )
                    }else {
                        getProcedureDetailFromApi(procedureID)
                    }
                }
            } catch (e: Exception) {
                _procedureDetailState.value = _procedureDetailState.value.copy(
                    loading = false,
                    error = e.localizedMessage
                )
            }

        }
    }



    fun getProcedureDetailFromApi(procedureID: String) {
        viewModelScope.launch {
            resetProcedureDetailState()
            when(val result = fetchProcedureDetailsFromApiUseCase(procedureID)){
                is Loading -> {
                    _procedureDetailState.value = _procedureDetailState.value.copy(loading = true)
                }
                is Success<*> -> {
                    loadProcedureDetailFromDB(procedureID)
                }
                is Error -> {
                    _procedureDetailState.value = _procedureDetailState.value.copy(
                        loading = false,
                        error = getErrorResult(result))
                }
            }

        }
    }

    fun getSingleProcedure(uuid: String){
        viewModelScope.launch {
            getSingleProcedureUseCase(uuid).collect{procedure->
                _procedureDetailState.value = _procedureDetailState.value.copy(
                    procedure = procedure
                )
            }
        }
    }

    fun updateFavouriteProcedure(uuid: String, isFavorite: Boolean) {
        viewModelScope.launch {
            updateFavouriteUseCase(uuid, isFavorite)
        }
    }


    fun resetProcedureDetailState(){
        _procedureDetailState.value = ProcedureDetailState()
    }
}

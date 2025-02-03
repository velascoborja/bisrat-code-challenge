package com.touch_surgery.digital_surgery.presentation.procedureDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.touch_surgery.digital_surgery.domain.model.Procedure
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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProceduresDetailViewModel(
    private val fetchProcedureDetailsFromApiUseCase: FetchProcedureDetailsFromApiUseCase,
    private val getProcedureDetailUseCase: GetProcedureDetailUseCase,
    private val updateFavouriteUseCase: UpdateFavouriteUseCase
) : ViewModel() {

    private val _procedureDetailState = MutableStateFlow(ProcedureDetailState())
    val procedureDetailState: StateFlow<ProcedureDetailState> = _procedureDetailState

    private val _procedure = MutableStateFlow<Procedure?>(null)
    val procedure: StateFlow<Procedure?> = _procedure.asStateFlow()


    fun loadProcedureDetailFromDB(procedureID: String) {
        viewModelScope.launch {
            _procedureDetailState.value = _procedureDetailState.value.copy(loading = true)
            try {
                getProcedureDetailUseCase(procedureID).collect { procedureDetail ->
                    if (procedureDetail != null){
                        _procedureDetailState.value = _procedureDetailState.value.copy(
                            procedureDetail = procedureDetail,
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


    fun updateFavouriteProcedure(uuid: String, isFavorite: Boolean) {
        viewModelScope.launch {
            updateFavouriteUseCase(uuid, isFavorite)
        }
    }


    fun resetProcedureDetailState(){
        _procedureDetailState.value = ProcedureDetailState()
    }
}

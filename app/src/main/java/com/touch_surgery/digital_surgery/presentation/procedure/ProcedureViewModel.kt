package com.touch_surgery.digital_surgery.presentation.procedure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.touch_surgery.digital_surgery.R
import com.touch_surgery.digital_surgery.domain.usecase.favourites.UpdateFavouriteUseCase
import com.touch_surgery.digital_surgery.domain.usecase.procedures.FetchProceduresFromApiUseCase
import com.touch_surgery.digital_surgery.domain.usecase.procedures.GetProceduresUseCase
import com.touch_surgery.digital_surgery.presentation.state.ProceduresState
import com.touch_surgery.utils.ResultWrapper.Error
import com.touch_surgery.utils.ResultWrapper.Loading
import com.touch_surgery.utils.ResultWrapper.Success
import com.touch_surgery.utils.getErrorResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProcedureViewModel(
    private val getProcedures: GetProceduresUseCase,
    private val fetchProceduresFromApiUseCase: FetchProceduresFromApiUseCase,
    private val updateFavouriteUseCase: UpdateFavouriteUseCase
) : ViewModel() {

    private val _proceduresState = MutableStateFlow(ProceduresState())
    val procedureState: StateFlow<ProceduresState> = _proceduresState

    private val _snackBarMessage = MutableStateFlow<Int?>(null)
    val snackBarMessage: StateFlow<Int?> = _snackBarMessage

    private val _splashCondition = MutableStateFlow(true)
    val splashCondition: StateFlow<Boolean>
        get() = _splashCondition


    init {
        loadProceduresFromDb()
        _splashCondition.value = false
    }


    private fun getDataFromApi(){
        viewModelScope.launch {
          when(val result = fetchProceduresFromApiUseCase()) {
             is Loading -> {
                 _proceduresState.value = _proceduresState.value.copy(loading = true)
             }
             is Success<*> -> loadProceduresFromDb()
             is Error -> {
                 _proceduresState.value = _proceduresState.value.copy(
                     error = getErrorResult(result),
                     loading = false
                 )
             }
          }
        }
    }

    private fun loadProceduresFromDb() {
        viewModelScope.launch {
            _proceduresState.value = _proceduresState.value.copy(loading = true)
            try {
                getProcedures().collect { procedures ->
                    if (procedures.isEmpty()){
                        getDataFromApi()
                    }else{
                        _proceduresState.value = _proceduresState.value.copy(
                            procedures = procedures,
                            loading = false
                        )
                    }
                }
            } catch (e: Exception) {
                _proceduresState.value = _proceduresState.value.copy(
                    error = e.localizedMessage,
                    loading = false
                )
            }
        }
    }

    fun updateFavouriteProcedure(uuid: String, isFavorite: Boolean) {
        viewModelScope.launch {
            try {
                updateFavouriteUseCase(uuid, isFavorite)
                _snackBarMessage.value = if (isFavorite)
                    R.string.favorites_added else R.string.favorites_removed
            } catch (e: Exception) {
                _proceduresState.value = _proceduresState.value.copy(
                    error = e.localizedMessage
                )
            }
        }
    }

    fun snackBarShown() {
        _snackBarMessage.value = null
    }
}
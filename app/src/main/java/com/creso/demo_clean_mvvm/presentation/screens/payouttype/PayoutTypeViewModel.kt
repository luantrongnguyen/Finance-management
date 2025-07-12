package com.creso.demo_clean_mvvm.presentation.screens.payouttype

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creso.demo_clean_mvvm.domain.model.PayoutType
import com.creso.demo_clean_mvvm.domain.usecase.PayoutType.CreatePayoutTypeUseCase
import com.creso.demo_clean_mvvm.domain.usecase.PayoutType.DeletePayoutTypeUseCase
import com.creso.demo_clean_mvvm.domain.usecase.PayoutType.EditPayoutTypeUseCase
import com.creso.demo_clean_mvvm.domain.usecase.PayoutType.GetPayoutTypeUseCase
import com.creso.demo_clean_mvvm.domain.usecase.PayoutType.GetListPayoutTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PayoutTypeViewModel @Inject constructor(
    private val getListPayoutTypeUseCase: GetListPayoutTypeUseCase,
    private val getPayoutTypeUseCase: GetPayoutTypeUseCase,
    private val createPayoutTypeUseCase: CreatePayoutTypeUseCase,
    private val editPayoutTypeUseCase: EditPayoutTypeUseCase,
    private val deletePayoutTypeUseCase: DeletePayoutTypeUseCase
): ViewModel() { //ViewModel quan sát Model và thực hiện thay đổi trên UI

    var PayoutTypes by mutableStateOf<List<PayoutType>>(emptyList()) // update lại UI khi PayoutTypes thay đổi
        private set // Có tể đọc mọi nơi nhưng chỉ set trong class
    var PayoutType by mutableStateOf<PayoutType>(PayoutType(0,"",""))
        private set

    fun loadList(){
        viewModelScope.launch{
            PayoutTypes = getListPayoutTypeUseCase()
        }
    }

    fun load(id :Int){
        viewModelScope.launch {
            PayoutType = getPayoutTypeUseCase(id)
        }
    }

    fun create(PayoutType: PayoutType){
        viewModelScope.launch {
            createPayoutTypeUseCase(PayoutType)
            loadList()
        }
    }

    fun edit(PayoutType: PayoutType){
        viewModelScope.launch {
            editPayoutTypeUseCase(PayoutType)
            loadList()
        }
    }

    fun delete(id: Int){
        viewModelScope.launch {
            deletePayoutTypeUseCase(id)
            loadList()
        }
    }

}
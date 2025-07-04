package com.creso.demo_clean_mvvm.presentation.payout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creso.demo_clean_mvvm.domain.model.Payout
import com.creso.demo_clean_mvvm.domain.usecase.Payout.CreatePayoutUseCase
import com.creso.demo_clean_mvvm.domain.usecase.Payout.DeletePayoutUseCase
import com.creso.demo_clean_mvvm.domain.usecase.Payout.EditPayoutUseCase
import com.creso.demo_clean_mvvm.domain.usecase.Payout.GetPayoutUseCase
import com.creso.demo_clean_mvvm.domain.usecase.Payout.GetListPayoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PayoutViewModel @Inject constructor(
    private val getListPayoutUseCase: GetListPayoutUseCase,
    private val getPayoutUseCase: GetPayoutUseCase,
    private val createPayoutUseCase: CreatePayoutUseCase,
    private val editPayoutUseCase: EditPayoutUseCase,
    private val deletePayoutUseCase: DeletePayoutUseCase
): ViewModel() { //ViewModel quan sát Model và thực hiện thay đổi trên UI

    var payouts by mutableStateOf<List<Payout>>(emptyList()) // update lại UI khi Payouts thay đổi
        private set // Có tể đọc mọi nơi nhưng chỉ set trong class
    var payout by mutableStateOf<Payout>(Payout(0,"",0.0,0))
        private set

    fun loadList(){
        viewModelScope.launch{
            payouts = getListPayoutUseCase()
        }
    }

    fun load(id :Int){
        viewModelScope.launch {
            payout = getPayoutUseCase(id)
        }
    }

    fun create(Payout: Payout){
        viewModelScope.launch {
            createPayoutUseCase(Payout)
            loadList()
        }
    }

    fun edit(Payout: Payout){
        viewModelScope.launch {
            editPayoutUseCase(Payout)
            loadList()
        }
    }

    fun delete(id: Int){
        viewModelScope.launch {
            deletePayoutUseCase(id)
            loadList()
        }
    }

}
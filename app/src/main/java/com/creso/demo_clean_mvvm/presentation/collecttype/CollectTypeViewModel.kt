package com.creso.demo_clean_mvvm.presentation.collecttype

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creso.demo_clean_mvvm.domain.model.CollectType
import com.creso.demo_clean_mvvm.domain.usecase.CollectType.CreateCollectTypeUseCase
import com.creso.demo_clean_mvvm.domain.usecase.CollectType.DeleteCollectTypeUseCase
import com.creso.demo_clean_mvvm.domain.usecase.CollectType.EditCollectTypeUseCase
import com.creso.demo_clean_mvvm.domain.usecase.CollectType.GetCollectTypeUseCase
import com.creso.demo_clean_mvvm.domain.usecase.CollectType.GetListCollectTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectTypeViewModel @Inject constructor(
    private val getListCollectTypeUseCase: GetListCollectTypeUseCase,
    private val getCollectTypeUseCase: GetCollectTypeUseCase,
    private val createCollectTypeUseCase: CreateCollectTypeUseCase,
    private val editCollectTypeUseCase: EditCollectTypeUseCase,
    private val deleteCollectTypeUseCase: DeleteCollectTypeUseCase
):ViewModel() {
    var collecTypes by mutableStateOf<List<CollectType>>(emptyList())
        private set
    var collectType by mutableStateOf<CollectType>(CollectType(0,"",""))
        private set

    fun loadList(){
        viewModelScope.launch {
            collecTypes = getListCollectTypeUseCase()
        }
    }

    fun load(id:Int) = viewModelScope.launch {
        collectType= getCollectTypeUseCase(id)
    }

    fun create(collectType: CollectType){
        viewModelScope.launch {
            createCollectTypeUseCase(collectType)
            loadList()
        }
    }

    fun edit(collectType: CollectType){
        viewModelScope.launch {
            editCollectTypeUseCase(collectType)
            loadList()
        }
    }

    fun delete(id: Int){
        viewModelScope.launch {
            deleteCollectTypeUseCase(id)
            loadList()
        }
    }
}
package com.luannguyentrong.minipos.presentation.collecttype

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luannguyentrong.minipos.domain.model.CollectType
import com.luannguyentrong.minipos.domain.usecase.CollectTypeType.CreateCollectTypeUseCase
import com.luannguyentrong.minipos.domain.usecase.CollectTypeType.DeleteCollectTypeUseCase
import com.luannguyentrong.minipos.domain.usecase.CollectTypeType.EditCollectTypeUseCase
import com.luannguyentrong.minipos.domain.usecase.CollectTypeType.GetCollectTypeUseCase
import com.luannguyentrong.minipos.domain.usecase.CollectTypeType.GetListCollectTypeUseCase
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

    fun load(id:Int){
        viewModelScope.launch {
            collectType= getCollectTypeUseCase(id)
        }
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
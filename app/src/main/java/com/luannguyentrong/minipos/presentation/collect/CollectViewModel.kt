package com.luannguyentrong.minipos.presentation.collect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luannguyentrong.minipos.domain.model.Collect
import com.luannguyentrong.minipos.domain.usecase.Collect.CreateCollectUseCase
import com.luannguyentrong.minipos.domain.usecase.Collect.DeleteCollectUseCase
import com.luannguyentrong.minipos.domain.usecase.Collect.EditCollectUseCase
import com.luannguyentrong.minipos.domain.usecase.Collect.GetCollectUseCase
import com.luannguyentrong.minipos.domain.usecase.Collect.GetListCollectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectViewModel @Inject constructor(
    private val getListCollectUseCase: GetListCollectUseCase,
    private val getCollectUseCase: GetCollectUseCase,
    private val createCollectUseCase: CreateCollectUseCase,
    private val editCollectUseCase: EditCollectUseCase,
    private val deleteCollectUseCase: DeleteCollectUseCase
): ViewModel() { //ViewModel quan sát Model và thực hiện thay đổi trên UI

    var collects by mutableStateOf<List<Collect>>(emptyList()) // update lại UI khi collects thay đổi
        private set // Có tể đọc mọi nơi nhưng chỉ set trong class
    var collect by mutableStateOf<Collect>(Collect(0,"",0.0,0))
        private set

    fun loadList(){
        viewModelScope.launch{
            collects = getListCollectUseCase()
        }
    }

    fun load(id :Int){
        viewModelScope.launch {
            collect = getCollectUseCase(id)
        }
    }

    fun create(collect: Collect){
        viewModelScope.launch {
            createCollectUseCase(collect)
            loadList()
        }
    }

    fun edit(collect: Collect){
        viewModelScope.launch {
            editCollectUseCase(collect)
            loadList()
        }
    }

    fun delete(id: Int){
        viewModelScope.launch {
            deleteCollectUseCase(id)
            loadList()
        }
    }

}
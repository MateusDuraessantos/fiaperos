package br.com.fiap.fluxor.ui.screens.compliances

import androidx.lifecycle.ViewModel
import br.com.fiap.fluxor.data.model.CategoriaCompliance
import br.com.fiap.fluxor.data.repository.ComplianceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CompliancesViewModel : ViewModel() {

    private val repository = ComplianceRepository()

    private val _categorias = MutableStateFlow<List<CategoriaCompliance>>(emptyList())

    val categorias: StateFlow<List<CategoriaCompliance>> = _categorias.asStateFlow()

    private val _categoriaSelecionada = MutableStateFlow<CategoriaCompliance?>(null)
    val categoriaSelecionada: StateFlow<CategoriaCompliance?> = _categoriaSelecionada.asStateFlow()

    init {
        carregarCategorias()
    }

    private fun carregarCategorias() {
        _categorias.value = repository.getCategorias()
    }

    fun selecionarCategoria(id: Int) {
        _categoriaSelecionada.value = repository.getCategoriaPorId(id)
    }


    fun limparSelecao() {
        _categoriaSelecionada.value = null
    }
}
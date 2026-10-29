
package com.example.barangbalik

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoanUiState(
    val items: List<LoanItem> = emptyList(),
    val message: String? = null
)

class LoanViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoanUiState())
    val uiState: StateFlow<LoanUiState> = _uiState.asStateFlow()

    private var nextId = 1

    fun addItem(itemName: String, borrowedBy: String) {
        if (itemName.isBlank() || borrowedBy.isBlank()) {
            _uiState.update {
                it.copy(message = "Nama barang dan peminjam harus diisi.")
            }
            return
        }

        val newItem = LoanItem(
            id = nextId++,
            itemName = itemName.trim(),
            borrowedBy = borrowedBy.trim()
        )

        _uiState.update {
            it.copy(
                items = it.items + newItem,
                message = null
            )
        }
    }

    fun toggleReturned(id: Int) {
        _uiState.update { state ->
            state.copy(
                items = state.items.map { item ->
                    if (item.id == id) {
                        item.copy(isReturned = !item.isReturned)
                    } else {
                        item
                    }
                },
                message = null
            )
        }
    }

    fun deleteItem(id: Int) {
        _uiState.update { state ->
            state.copy(
                items = state.items.filterNot { it.id == id },
                message = null
            )
        }
    }
}

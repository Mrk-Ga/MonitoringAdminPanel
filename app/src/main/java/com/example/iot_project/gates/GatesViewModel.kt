package com.example.iot_project.gates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iot_project.models.Gate
import com.example.iot_project.repositories.GateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GatesViewModel(
    private val repo: GateRepository
): ViewModel() {

    private val _mainState = MutableStateFlow<List<Gate>>(emptyList())
    val mainState: StateFlow<List<Gate>> = _mainState.asStateFlow()

    private val _showAddDialog = MutableStateFlow(false)
    val showAddDialog: StateFlow<Boolean> = _showAddDialog.asStateFlow()

    private val _showEditDialog = MutableStateFlow(false)
    val showEditDialog: StateFlow<Boolean> = _showEditDialog.asStateFlow()

    private val _selectedGate = MutableStateFlow<Gate?>(null)
    val selectedGate: StateFlow<Gate?> = _selectedGate.asStateFlow()
    
    private val _toast = MutableStateFlow("")
    val toast: StateFlow<String> = _toast.asStateFlow()


    fun onAddGateClicked() {
        _showAddDialog.value = true
    }

    fun onAddDialogDismiss() {
        _showAddDialog.value = false
    }

    fun onEditGateClicked(gate: Gate) {
        _selectedGate.value = gate
        _showEditDialog.value = true
    }

    fun onEditDialogDismiss() {
        _showEditDialog.value = false
        _selectedGate.value = null
    }
    
    fun onToast(message: String){
        _toast.value = "Wrong data input ("+message+")"
    }

    fun onToastShown() {
        _toast.value = ""
    }

    fun importGates() {
        viewModelScope.launch {
            _mainState.value = repo.getAll()
        }
    }

    fun createGate(name: String, buildingId: String) {
        viewModelScope.launch {
            val newGate = Gate(id = -1, name = name, building_id = buildingId.toIntOrNull() ?: 0)

            try{
                val response = repo.create(newGate)
            }catch (e: Exception){
                onToast(e.message.toString())
            }
            importGates()
            onAddDialogDismiss()
        }
    }

    fun updateGate(id: Int, name: String, buildingId: String) {
        viewModelScope.launch {
            val updatedGate = Gate(id = id, name = name, building_id = buildingId.toIntOrNull() ?: 0)
            repo.update(updatedGate)
            importGates()
            onEditDialogDismiss()
        }
    }

    fun deleteGate(gate: Gate) {
        viewModelScope.launch {
            repo.delete(gate.id)
            importGates()
        }
    }
}
package com.example.iot_project.buildings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iot_project.models.Building
import com.example.iot_project.repositories.BuildingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BuildingsViewModel(
    private val buildingRepository: BuildingRepository
) : ViewModel() {

    private val _buildings = MutableStateFlow<List<Building>>(emptyList())
    val buildings = _buildings.asStateFlow()

    private val _showAddDialog = MutableStateFlow(false)
    val showAddDialog: StateFlow<Boolean> = _showAddDialog.asStateFlow()

    private val _showEditDialog = MutableStateFlow(false)
    val showEditDialog: StateFlow<Boolean> = _showEditDialog.asStateFlow()

    private val _selectedBuilding = MutableStateFlow<Building?>(null)
    val selectedBuilding: StateFlow<Building?> = _selectedBuilding.asStateFlow()

    fun onAddBuildingClicked() {
        _showAddDialog.value = true
    }

    fun onAddDialogDismiss() {
        _showAddDialog.value = false
    }

    fun onEditBuildingClicked(building: Building) {
        _selectedBuilding.value = building
        _showEditDialog.value = true
    }

    fun onEditDialogDismiss() {
        _showEditDialog.value = false
        _selectedBuilding.value = null
    }

    fun importBuildings() {
        viewModelScope.launch {
            _buildings.value = buildingRepository.getAll()
        }
    }

    fun createBuilding(name: String, address: String) {
        viewModelScope.launch {
            val newBuilding = Building(id = 0, name = name, address = address)
            buildingRepository.create(newBuilding)
            importBuildings()
            onAddDialogDismiss()
        }
    }

    fun updateBuilding(id: Int, name: String, address: String) {
        viewModelScope.launch {
            val updatedBuilding = Building(id = id, name = name, address = address)
            buildingRepository.update(updatedBuilding)
            importBuildings()
            onEditDialogDismiss()
        }
    }

    fun deleteBuilding(building: Building) {
        viewModelScope.launch {
            buildingRepository.delete(building.id)
            importBuildings()
        }
    }
}
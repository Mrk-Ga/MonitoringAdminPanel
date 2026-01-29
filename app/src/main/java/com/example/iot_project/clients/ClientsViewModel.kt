package com.example.iot_project.clients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iot_project.models.Client
import com.example.iot_project.repositories.ClientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.math.BigInteger

class ClientsViewModel(
    private val repo: ClientRepository
): ViewModel() {

    private var _clients = MutableStateFlow<List<Client>>(emptyList())
    val clients: StateFlow<List<Client>> = _clients.asStateFlow()

    private val _showAddDialog = MutableStateFlow(false)
    val showAddDialog: StateFlow<Boolean> = _showAddDialog.asStateFlow()

    private val _showEditDialog = MutableStateFlow(false)
    val showEditDialog: StateFlow<Boolean> = _showEditDialog.asStateFlow()

    private val _selectedClient = MutableStateFlow<Client?>(null)
    val selectedClient: StateFlow<Client?> = _selectedClient.asStateFlow()

    fun onAddClientClicked() {
        _showAddDialog.value = true
    }

    fun onAddDialogDismiss() {
        _showAddDialog.value = false
    }

    fun onEditClientClicked(client: Client) {
        _selectedClient.value = client
        _showEditDialog.value = true
    }

    fun onEditDialogDismiss() {
        _showEditDialog.value = false
        _selectedClient.value = null
    }

    fun importClients(){
        viewModelScope.launch{
            _clients.value = repo.getAll()
        }
    }

    fun createClient(name: String, lastname: String, refId: String, email: String, indeks: String) {
        viewModelScope.launch {
            val newClient = Client(id = -1, name = name, lastname = lastname, rfid = refId.toBigInteger(), email = email, indeks = indeks)
            repo.create(newClient)
            importClients()
            onAddDialogDismiss()
        }
    }

    fun updateClient(id: Int, name: String, lastname: String, refId: String, email: String, indeks:String) {
        viewModelScope.launch {
            val updatedClient = Client(id = id, name = name, lastname = lastname, rfid = refId.toBigInteger(), email = email, indeks=indeks)
            repo.update(updatedClient)
            importClients()
            onEditDialogDismiss()
        }
    }

    fun deleteClient(client: Client) {
        viewModelScope.launch {
            repo.delete(client.id)
            importClients()
        }
    }
}
package com.example.iot_project.clients

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.iot_project.models.Client

@Composable
fun ClientsScreen(
    viewModel: ClientsViewModel
){
    val clients by viewModel.clients.collectAsState()
    val showAddDialog by viewModel.showAddDialog.collectAsState()
    val showEditDialog by viewModel.showEditDialog.collectAsState()

    LaunchedEffect(Unit){
        viewModel.importClients()
    }

    if (showAddDialog) {
        AddClientDialog(viewModel = viewModel)
    }

    if (showEditDialog) {
        viewModel.selectedClient.collectAsState().value?.let {
            EditClientDialog(viewModel = viewModel, client = it)
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onAddClientClicked() }) {
                Icon(Icons.Default.Add, contentDescription = "Add Client")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(clients) { client ->
                ClientCard(client = client, onLongClick = { viewModel.onEditClientClicked(client) })
            }
        }
    }
}

@Composable
fun AddClientDialog(viewModel: ClientsViewModel) {
    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var refId by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var indeks by remember { mutableStateOf("") }


    AlertDialog(
        onDismissRequest = { viewModel.onAddDialogDismiss() },
        title = { Text("Add New Client") },
        text = {
            Column {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = lastname, onValueChange = { lastname = it }, label = { Text("Lastname") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = refId, onValueChange = { refId = it }, label = { Text("Reference ID") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = indeks, onValueChange = { indeks= it }, label = { Text("Index") })
            }
        },
        confirmButton = {
            Button(onClick = { viewModel.createClient(name, lastname, refId, email, indeks)
                                viewModel.onAddDialogDismiss() }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = { viewModel.onAddDialogDismiss() }) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun EditClientDialog(viewModel: ClientsViewModel, client: Client) {
    var name by remember { mutableStateOf(client.name) }
    var lastname by remember { mutableStateOf(client.lastname) }
    var refId by remember { mutableStateOf(client.rfid.toString()) }
    var email by remember { mutableStateOf(client.email) }
    var indeks by remember { mutableStateOf(client.indeks) }


    AlertDialog(
        onDismissRequest = { viewModel.onEditDialogDismiss() },
        title = { Text("Edit Client") },
        text = {
            Column {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = lastname, onValueChange = { lastname = it }, label = { Text("Lastname") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = refId, onValueChange = { refId = it }, label = { Text("Reference ID") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = indeks, onValueChange = { indeks = it }, label = { Text("Index") })
            }
        },
        confirmButton = {
            Button(onClick = { viewModel.updateClient(client.id, name, lastname, refId, email, indeks)
                                viewModel.onEditDialogDismiss() }) {
                Text("Save")
            }
        },
        dismissButton = {
            Row {
                Button(onClick = { viewModel.deleteClient(client) }) {
                    Text("Delete")
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = { viewModel.onEditDialogDismiss() }) {
                    Text("Cancel")
                }
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClientCard(client: Client, onLongClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .combinedClickable(
                onClick = { /* Handle single click if needed */ },
                onLongClick = onLongClick
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${client.name} ${client.lastname}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Ref ID: ${client.rfid}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Client Icon",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
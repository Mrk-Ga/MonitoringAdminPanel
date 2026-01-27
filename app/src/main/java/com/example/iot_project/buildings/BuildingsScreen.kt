package com.example.iot_project.buildings

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
import androidx.compose.material.icons.filled.Apartment
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
import com.example.iot_project.models.Building

@Composable
fun BuildingsScreen(
    viewModel: BuildingsViewModel
) {
    val buildings by viewModel.buildings.collectAsState()
    val showAddDialog by viewModel.showAddDialog.collectAsState()
    val showEditDialog by viewModel.showEditDialog.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.importBuildings()
    }

    if (showAddDialog) {
        AddBuildingDialog(viewModel = viewModel)
    }

    if (showEditDialog) {
        viewModel.selectedBuilding.value?.let {
            EditBuildingDialog(viewModel = viewModel, building = it)
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onAddBuildingClicked() }) {
                Icon(Icons.Default.Add, contentDescription = "Add Building")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(buildings) { building ->
                BuildingCard(building = building, onLongClick = { viewModel.onEditBuildingClicked(building) })
            }
        }
    }
}

@Composable
fun AddBuildingDialog(viewModel: BuildingsViewModel) {
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { viewModel.onAddDialogDismiss() },
        title = { Text("Add New Building") },
        text = {
            Column {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = address, onValueChange = { address = it }, label = { Text("Address") })
            }
        },
        confirmButton = {
            Button(onClick = { viewModel.createBuilding(name, address) }) {
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
fun EditBuildingDialog(viewModel: BuildingsViewModel, building: Building) {
    var name by remember { mutableStateOf(building.name) }
    var address by remember { mutableStateOf(building.address) }

    AlertDialog(
        onDismissRequest = { viewModel.onEditDialogDismiss() },
        title = { Text("Edit Building") },
        text = {
            Column {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = address, onValueChange = { address = it }, label = { Text("Address") })
            }
        },
        confirmButton = {
            Button(onClick = { viewModel.updateBuilding(building.id, name, address) }) {
                Text("Save")
            }
        },
        dismissButton = {
            Row {
                Button(onClick = { viewModel.deleteBuilding(building) }) {
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
fun BuildingCard(building: Building, onLongClick: () -> Unit) {
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
                    text = building.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = building.address,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(
                imageVector = Icons.Default.Apartment,
                contentDescription = "Building Icon",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
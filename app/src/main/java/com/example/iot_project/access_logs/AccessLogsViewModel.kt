package com.example.iot_project.access_logs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iot_project.models.AccessLog
import com.example.iot_project.models.Client
import com.example.iot_project.repositories.AccessLogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AccessLogsViewModel(
    private val repo: AccessLogRepository
) : ViewModel(){

    private var _accessLogs = MutableStateFlow<List<AccessLog>>(emptyList())
    val accessLogs: StateFlow<List<AccessLog>> = _accessLogs

    fun importAccessLogs(){
        viewModelScope.launch{
            val logs = repo.getAll()
            _accessLogs.value = logs

        }
    }
}
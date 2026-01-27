package com.example.iot_project

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.iot_project.access_logs.AccessLogsScreen
import com.example.iot_project.access_logs.AccessLogsViewModel
import com.example.iot_project.api.RetrofitInstance
import com.example.iot_project.buildings.BuildingsScreen
import com.example.iot_project.buildings.BuildingsViewModel
import com.example.iot_project.clients.ClientsScreen
import com.example.iot_project.clients.ClientsViewModel
import com.example.iot_project.gates.GatesScreen
import com.example.iot_project.gates.GatesViewModel
import com.example.iot_project.repositories.AccessLogRepository
import com.example.iot_project.repositories.BuildingRepository
import com.example.iot_project.repositories.ClientRepository
import com.example.iot_project.repositories.GateRepository


@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavHost(navController: NavHostController){
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp)
                    .background(MaterialTheme.colorScheme.secondary)
                    ,
                contentAlignment = Alignment.Center
            ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center,

                    ) {
                        Text(
                            text = "Admin Panel",
                            color = Color.White,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                    }

                Spacer(Modifier.padding(10.dp))



            }
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },

        ) {  innerPadding ->

        val gatesVM = GatesViewModel(
            GateRepository(
                RetrofitInstance.createGateApi("RetrofitInstance.BASE_URL")
            )
        )
        val clientVM = ClientsViewModel(
            ClientRepository(
                RetrofitInstance.createClientApi("RetrofitInstance.BASE_URL")
            )
        )

        val accessLogVM = AccessLogsViewModel(
            AccessLogRepository(
                RetrofitInstance.createAccessLogApi("RetrofitInstance.BASE_URL")
            )
        )

        val buildingVM = BuildingsViewModel(
            BuildingRepository(
                RetrofitInstance.createBuildingApi("RetrofitInstance.BASE_URL")
            )
        )


/*        val buildingVM = BuildingViewModel(
            BuildingRepository(
                RetrofitInstance.createBuildingApi("RetrofitInstance.BASE_URL")
            )
        )*/


        NavHost(
            navController = navController,
            startDestination = "gates",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable("gates") {
                GatesScreen(gatesVM)
            }


            composable("clients"){
                ClientsScreen(clientVM)
            }

            composable("access_logs"){
                AccessLogsScreen(accessLogVM)
            }

            composable("buildings"){
                BuildingsScreen(buildingVM)
            }


        }

    }
}

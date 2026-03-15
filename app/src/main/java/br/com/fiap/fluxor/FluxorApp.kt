package br.com.fiap.fluxor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.fiap.fluxor.ui.navigation.AppRoutes
import br.com.fiap.fluxor.ui.screens.compliances.ComplianceDetailsScreen
import br.com.fiap.fluxor.ui.screens.compliances.CompliancesScreen
import br.com.fiap.fluxor.ui.screens.compliances.CompliancesViewModel
import br.com.fiap.fluxor.ui.screens.initial.InitialScreen
import br.com.fiap.fluxor.ui.screens.profile.ProfileScreen
import androidx.compose.material.icons.filled.ArrowBack
import br.com.fiap.fluxor.ui.screens.login.LoginScreen


data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FluxorApp() {
    val navController = rememberNavController()

    val compliancesViewModel: CompliancesViewModel = viewModel()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    val mostrarBarras = currentRoute != AppRoutes.Login.route


    val bottomNavItems = listOf(
        BottomNavItem("Início", Icons.Filled.Home, AppRoutes.Inicio.route),
        BottomNavItem("Complices", Icons.Filled.List, AppRoutes.Complices.route),
        BottomNavItem("Perfil", Icons.Filled.Person, AppRoutes.Perfil.route)
    )

    Scaffold(
        topBar = {
            if (mostrarBarras) {
            TopAppBar(
                title = { Text("FluxOR") },
                navigationIcon = {
                    val currentRoute = null
                    if (currentRoute == AppRoutes.ComplicesDetalhes.route) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Voltar",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notificações"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )}
        },
        bottomBar = {
            if (mostrarBarras) {
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 0.dp
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    bottomNavItems.forEach { item ->
                        val isSelected = if (item.route == AppRoutes.Complices.route) {

                            currentRoute == AppRoutes.Complices.route || currentRoute == AppRoutes.ComplicesDetalhes.route
                        } else {

                            currentRoute == item.route
                        }

                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.title) },
                            label = { Text(item.title) },
                            selected = isSelected,
                            onClick = {
                                if (currentRoute == AppRoutes.ComplicesDetalhes.route && item.route == AppRoutes.Complices.route) {
                                    navController.popBackStack(AppRoutes.Complices.route, inclusive = false)
                                } else {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = false
                                        }
                                        launchSingleTop = true
                                        restoreState = false
                                    }
                                }
                            },

                            modifier = Modifier.background(
                                if (isSelected) Color(0xFF1353A8) else Color.White
                            ),

                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                indicatorColor = Color.Transparent,

                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoutes.Login.route,
            modifier = Modifier.padding(if (mostrarBarras) innerPadding else PaddingValues(0.dp))
        ) {

            composable(AppRoutes.Login.route) {
                LoginScreen(
                    onLoginClick = {
                        navController.navigate(AppRoutes.Inicio.route) {
                            popUpTo(AppRoutes.Login.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(AppRoutes.Inicio.route) {
                InitialScreen()
            }


            composable(AppRoutes.Complices.route) {
                CompliancesScreen(
                    viewModel = compliancesViewModel,
                    onCategoriaClick = { id ->
                        compliancesViewModel.selecionarCategoria(id)
                        navController.navigate(AppRoutes.ComplicesDetalhes.route)
                    }
                )
            }


            composable(AppRoutes.ComplicesDetalhes.route) {
                ComplianceDetailsScreen(
                    viewModel = compliancesViewModel
                )
            }


            composable(AppRoutes.Perfil.route) {
                ProfileScreen(
                    onLogoutClick = {
                        navController.navigate(AppRoutes.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
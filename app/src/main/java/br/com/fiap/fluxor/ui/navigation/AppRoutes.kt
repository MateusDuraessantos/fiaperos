package br.com.fiap.fluxor.ui.navigation

sealed class AppRoutes(val route: String) {
    object Login : AppRoutes("login")
    object Inicio : AppRoutes("inicio")
    object Complices : AppRoutes("complices")
    object ComplicesDetalhes : AppRoutes("complices_detalhes")
    object Perfil : AppRoutes("perfil")
}
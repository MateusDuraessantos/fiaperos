package br.com.fiap.fluxor.ui.screens.compliances

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CompliancesScreen(
    viewModel: CompliancesViewModel,
    onCategoriaClick: (Int) -> Unit
) {

    val categorias by viewModel.categorias.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val categoriasFiltradas = categorias.filter { categoria ->
        categoria.titulo.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    ) {

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Pesquisar") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Ícone de pesquisa"
                )
            },
            shape = RoundedCornerShape(50),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                cursorColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(categoriasFiltradas) { categoria ->
                CategoriaItem(
                    titulo = categoria.titulo,
                    onClick = { onCategoriaClick(categoria.id) }
                )

                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }
    }
}

@Composable
fun CategoriaItem(titulo: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    }
}
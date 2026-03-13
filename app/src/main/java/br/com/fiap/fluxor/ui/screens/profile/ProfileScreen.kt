package br.com.fiap.fluxor.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.fluxor.ui.theme.poppinsFamily

@Composable
fun ProfileScreen(onLogoutClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))


        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(color = Color(0xFFE0E0E0), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Foto de perfil",
                    modifier = Modifier.size(80.dp),
                    tint = Color(0xFF757575)
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))


        Text(
            text = "Informações sobre o usuário",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFamily,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))


        ProfileInfoRow(label = "Nome", value = "Lucas da Silva")
        Spacer(modifier = Modifier.height(8.dp))

        ProfileInfoRow(label = "Matrícula", value = "5354928")
        Spacer(modifier = Modifier.height(8.dp))

        ProfileInfoRow(label = "RG", value = "500.000.200.22")
        Spacer(modifier = Modifier.height(8.dp))

        ProfileInfoRow(label = "Email", value = "lucasdasilva@email.com")

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onLogoutClick,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF0000))
        ) {
            Text("Sair da Conta")
        }
    }
}


@Composable
fun ProfileInfoRow(label: String, value: String) {
    Row {
        Text(
            text = "$label: ",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
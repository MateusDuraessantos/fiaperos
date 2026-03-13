package br.com.fiap.fluxor.ui.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.fluxor.R
import br.com.fiap.fluxor.ui.theme.poppinsFamily

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }


    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(Color(0xFF001D4D))
                .padding(horizontal = 24.dp)
                .padding(top = 35.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.fluxor2),
                contentDescription = "Logo escrito FluxOR",
                modifier = Modifier.size(70.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(2.dp, Color(0xFF001D4D)),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "GERENCIE SUAS OPERAÇÕES",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontFamily = poppinsFamily
                    )
                    Text(
                        text = "Faça o Login.",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontFamily = poppinsFamily
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            errorMessage = null
                        },
                        label = {
                            Text(
                                text = "E-mail ou Código Funcional",
                                fontFamily = poppinsFamily,
                                color = Color.Black.copy(alpha = 0.5f),
                                fontSize = 11.sp
                            )
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = null, tint = Color.Black.copy(alpha = 0.5f))
                        },
                        isError = errorMessage != null && email.isBlank(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = senha,
                        onValueChange = {
                            senha = it
                            errorMessage = null
                        },
                        label = {
                            Text(
                                text = "Senha",
                                fontFamily = poppinsFamily,
                                color = Color.Black.copy(alpha = 0.5f),
                                fontSize = 11.sp
                            )
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = null, tint = Color.Black.copy(alpha = 0.5f))
                        },
                        trailingIcon = {
                            Text(
                                text = if (passwordVisible) "Ocultar" else "Mostrar",
                                color = Color(0xFF001D4D),
                                fontWeight = FontWeight.Bold,
                                fontFamily = poppinsFamily,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .clickable { passwordVisible = !passwordVisible }
                            )
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        isError = errorMessage != null && senha.isBlank(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                    if (errorMessage != null) {
                        Text(
                            text = errorMessage!!,
                            color = MaterialTheme.colorScheme.error,
                            fontFamily = poppinsFamily,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    Button(
                        // 4. Lógica de validação ao clicar no botão
                        onClick = {
                            if (email.isBlank() || senha.isBlank()) {
                                errorMessage = "Por favor, preencha todos os campos."
                            } else {
                                errorMessage = null
                                onLoginClick()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF001D4D),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "ENTRAR",
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}
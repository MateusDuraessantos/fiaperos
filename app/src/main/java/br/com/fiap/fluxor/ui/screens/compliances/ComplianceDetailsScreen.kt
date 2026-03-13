package br.com.fiap.fluxor.ui.screens.compliances


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.fluxor.data.model.EtapaCompliance
import br.com.fiap.fluxor.data.model.StatusEtapa
import br.com.fiap.fluxor.ui.theme.poppinsFamily

@Composable
fun ComplianceDetailsScreen(
    viewModel: CompliancesViewModel
) {

    val categoria by viewModel.categoriaSelecionada.collectAsState()


    if (categoria == null) return

    val progresso = if (categoria!!.totalEtapas > 0) {
        categoria!!.etapasConcluidas.toFloat() / categoria!!.totalEtapas.toFloat()
    } else 0f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Compliances > ${categoria!!.titulo}",
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${categoria!!.etapasConcluidas}/${categoria!!.totalEtapas}",
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = " Etapas Concluídas",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { progresso },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = Color(0xFFD2DDFF)

        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categoria!!.etapas) { etapa ->
                EtapaItemCard(etapa)
            }
        }
    }
}


@Composable
fun EtapaItemCard(etapa: EtapaCompliance) {
    when (etapa.status) {
        StatusEtapa.CONCLUIDO -> {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Concluído",
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = etapa.titulo, fontFamily = poppinsFamily, fontWeight = FontWeight.SemiBold)
                    Text(text = "Concluído", color = Color(0xFF9E9E9E), fontSize = 12.sp)
                }
            }
        }
        StatusEtapa.EM_ANDAMENTO -> {
            Card(

                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
                shape = RoundedCornerShape(8.dp)
            ) {

                Column(modifier = Modifier.fillMaxWidth()) {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = Color(0xFFFFFFFF))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = etapa.titulo, fontWeight = FontWeight.Normal, color = Color(0xFFFFFFFF))
                        }
                        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "Acessar", tint = Color(0xFFFFFFFF))
                    }


                    if (etapa.descricao != null) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 16.dp)
                        ) {
                            Text(
                                text = etapa.descricao,
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
        StatusEtapa.PENDENTE -> {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Pendente",
                    tint = Color(0xFFB0BEC5),
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = etapa.titulo, color = Color.Gray, fontFamily = poppinsFamily, fontWeight = FontWeight.Normal)
                    Text(text = "Pendentes", color = Color(0xFFB0BEC5), fontSize = 12.sp)
                }
            }
        }
        StatusEtapa.ALERTA -> {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Info, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = etapa.titulo, fontFamily = poppinsFamily, fontWeight = FontWeight.Normal, color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}
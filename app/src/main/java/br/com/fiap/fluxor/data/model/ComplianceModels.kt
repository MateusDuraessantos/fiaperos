package br.com.fiap.fluxor.data.model

enum class StatusEtapa {
    CONCLUIDO,
    EM_ANDAMENTO,
    PENDENTE,
    ALERTA
}


data class EtapaCompliance(
    val id: Int,
    val titulo: String,
    val descricao: String? = null,
    val status: StatusEtapa
)


data class CategoriaCompliance(
    val id: Int,
    val titulo: String,
    val etapas: List<EtapaCompliance> = emptyList()
) {

    val etapasConcluidas: Int
        get() = etapas.count { it.status == StatusEtapa.CONCLUIDO }

    val totalEtapas: Int
        get() = etapas.size
}
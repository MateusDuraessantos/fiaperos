package br.com.fiap.fluxor.data.repository

import br.com.fiap.fluxor.data.model.CategoriaCompliance
import br.com.fiap.fluxor.data.model.EtapaCompliance
import br.com.fiap.fluxor.data.model.StatusEtapa

class ComplianceRepository {


    fun getCategorias(): List<CategoriaCompliance> {
        return listOf(
            CategoriaCompliance(
                id = 1,
                titulo = "Crédito pessoal - CDC",
                etapas = listOf(
                    EtapaCompliance(
                        id = 101,
                        titulo = "Atualizar Cadastro do Cliente",
                        status = StatusEtapa.CONCLUIDO
                    ),
                    EtapaCompliance(
                        id = 102,
                        titulo = "Solicitar Documentos e Dados do Veículo",
                        status = StatusEtapa.CONCLUIDO
                    ),
                    EtapaCompliance(
                        id = 103,
                        titulo = "Verificar Restrições",
                        descricao = "Consultar restrições do cliente e situação do veículo.",
                        status = StatusEtapa.EM_ANDAMENTO
                    ),
                    EtapaCompliance(
                        id = 104,
                        titulo = "Verificar Restrições (Internas)",
                        status = StatusEtapa.PENDENTE
                    ),
                    EtapaCompliance(
                        id = 105,
                        titulo = "Verificar Alienação",
                        status = StatusEtapa.PENDENTE
                    ),

                    EtapaCompliance(
                        id = 106,
                        titulo = "Complete as etapas obrigatórias antes de enviar a proposta.",
                        status = StatusEtapa.ALERTA
                    )
                )
            ),
            CategoriaCompliance(id = 2, titulo = "Prevenção à Lavagem de Dinheiro"),
            CategoriaCompliance(id = 3, titulo = "Conheça Seu Cliente"),
            CategoriaCompliance(id = 4, titulo = "Compliance Fiscal/Tributário"),
            CategoriaCompliance(id = 5, titulo = "Verificação fiscal"),
            CategoriaCompliance(id = 6, titulo = "Score de risco final")
        )
    }


    fun getCategoriaPorId(id: Int): CategoriaCompliance? {
        return getCategorias().find { it.id == id }
    }
}
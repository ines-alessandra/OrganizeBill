import api from "./http-common";

export async function getRelatorioByMeses(cpf, qntMes) {
    try {
      const response = await api.get(`/relatorio/usuario/${cpf}/${qntMes}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao obter receitas para o usuário ${cpf} na data ${qntMes}:`, error);
      throw error;
    }
  }

  export async function getRelatorioReceitaByMeses(cpf, qntMes) {
    try {
      const response = await api.get(`/relatorio/receita/usuario/${cpf}/${qntMes}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao obter receitas para o usuário ${cpf} na data ${qntMes}:`, error);
      throw error;
    }
  }

  export async function getRelatorioDespesaByMeses(cpf, qntMes) {
    try {
      const response = await api.get(`/relatorio/despesa/usuario/${cpf}/${qntMes}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao obter receitas para o usuário ${cpf} na data ${qntMes}:`, error);
      throw error;
    }
  }
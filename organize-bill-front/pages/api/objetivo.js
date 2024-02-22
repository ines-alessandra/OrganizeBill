import api from "./http-common";

export async function getAllObjetivo(cpf) {
  try {
    const response = await api.get(`/usuario/${cpf}/objetivoFinanceiro`);
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar objetivoFinanceiro:', error);
    throw error; 
  }
}

export async function getObjetivoById(id) {
  try {
    const response = await api.get(`/objetivoFinanceiro/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Erro ao obter objetivoFinanceiro com ID ${id}:`, error);
    throw error;
  }
}

export async function createObjetivo(objetivoData) {
  try {
    const response = await api.post('/objetivoFinanceiro', objetivoData, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao criar objetivoFinanceiro:', error);
    throw error;
  }
}

export async function updateObjetivo(id, objetivoData) {
  try {
    const response = await api.post(`/objetivoFinanceiro/${id}`, objetivoData);
    return response.data;
  } catch (error) {
    console.error(`Erro ao atualizar objetivoFinanceiro com ID ${id}:`, error);
    throw error;
  }
}

export async function deleteObjetivo(id) {
  try {
    await api.delete(`/objetivoFinanceiro/${id}`);
  } catch (error) {
    console.error(`Erro ao excluir objetivoFinanceiro com ID ${id}:`, error);
    throw error;
  }
}

export async function addValueObjetivo(id, value) {
    try {
      const response = await api.post(`/objetivoFinanceiro/${id}/valor/${value}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao depositar valor em objetivoFinanceiro com ID ${id}:`, error);
      throw error;
    }
  }



import api from "./http-common";

export async function getAllDespesa() {
  try {
    const response = await api.get('/despesas');
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar despesas:', error);
    throw error; 
  }
}

export async function getDespesaById(id) {
  try {
    const response = await api.get(`/despesas/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Erro ao obter despesas com ID ${id}:`, error);
    throw error;
  }
}

export async function createDespesa(despesaData) {
  try {
    const response = await api.post('/despesas', despesaData, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao criar despesas:', error);
    throw error;
  }
}

export async function updateDespesa(id, despesaData) {
  try {
    const response = await api.post(`/despesas/${id}`, despesaData);
    return response.data;
  } catch (error) {
    console.error(`Erro ao atualizar despesas com ID ${id}:`, error);
    throw error;
  }
}

export async function deleteDespesa(id) {
  try {
    await api.delete(`/despesas/${id}`);
  } catch (error) {
    console.error(`Erro ao excluir despesas com ID ${id}:`, error);
    throw error;
  }
}

export async function getDespesaByData(cpf, data, fixo) {
  try {
    const response = await api.get(`/despesas/usuario/${cpf}/${data}/${fixo}`);
    return response.data;
  } catch (error) {
    console.error(`Erro ao obter despesas para o usuário ${cpf} na data ${data}:`, error);
    throw error;
  }
}

export async function getTotalDespesaMensais(cpf, data, tipo) {
  try {
    const response = await api.get(`/usuario/${cpf}/totalDespesas/${data}/${tipo}`);
    return response.data;
  } catch (error) {
    console.error(`Erro ao obter o total de despesas mensais para o usuário ${cpf} na data ${data}, tipo ${tipo}:`, error);
    throw error;
  }
}

export async function getDespesaByCategoria(categoriaId,data, fixo) {
    try {
      const response = await api.get(`/despesas/categoria/${categoriaId}/${data}/${fixo}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao obter despesas com categoria ID ${categoriaId}:`, error);
      throw error;
    }
  }

  export async function getTotalDespesaByCategoria(categoriaId) {
    try {
      const response = await api.get(`/despesas/total/categoria/${categoriaId}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao obter despesas com categoria ID ${categoriaId}:`, error);
      throw error;
    }
  }

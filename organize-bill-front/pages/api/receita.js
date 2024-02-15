import api from "./http-common";

export async function getAllReceita() {
  try {
    const response = await api.get('/receita');
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar receitas:', error);
    throw error; 
  }
}

export async function getReceitaById(id) {
  try {
    const response = await api.get(`/receita/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Erro ao obter receita com ID ${id}:`, error);
    throw error;
  }
}

export async function createReceita(receitaData) {
  try {
    const response = await api.post('/receita', receitaData, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao criar receita:', error);
    throw error;
  }
}

export async function updateReceita(id, receitaData) {
  try {
    const response = await api.post(`/receita/${id}`, receitaData);
    return response.data;
  } catch (error) {
    console.error(`Erro ao atualizar receita com ID ${id}:`, error);
    throw error;
  }
}

export async function deleteReceita(id) {
  try {
    await api.delete(`/receita/${id}`);
  } catch (error) {
    console.error(`Erro ao excluir receita com ID ${id}:`, error);
    throw error;
  }
}

export async function getReceitasByData(cpf, data, fixo) {
  try {
    const response = await api.get(`/receita/usuario/${cpf}/${data}/${fixo}`);
    return response.data;
  } catch (error) {
    console.error(`Erro ao obter receitas para o usuário ${cpf} na data ${data}:`, error);
    throw error;
  }
}

export async function getTotalReceitasMensais(cpf, data, tipo) {
  try {
    const response = await api.get(`/usuario/${cpf}/totalReceitas/${data}/${tipo}`);
    return response.data;
  } catch (error) {
    console.error(`Erro ao obter o total de receitas mensais para o usuário ${cpf} na data ${data}, tipo ${tipo}:`, error);
    throw error;
  }
}

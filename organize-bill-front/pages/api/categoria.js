import api from "./http-common";

export async function getAllCategoria(cpf) {
  try {
    const response = await api.get(`/usuario/categoria/${cpf}`);
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar categorias:', error);
    throw error; 
  }
}

export async function getCategoriaById(id) {
  try {
    const response = await api.get(`/categoria/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Erro ao obter categoria com ID ${id}:`, error);
    throw error;
  }
}

export async function createReceita(categoriaData) {
  try {
    const response = await api.post('/categoria', categoriaData, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao criar categoria:', error);
    throw error;
  }
}

export async function updateCategoria(id, categoriaData) {
  try {
    const response = await api.post(`/categoria/${id}`, categoriaData);
    return response.data;
  } catch (error) {
    console.error(`Erro ao atualizar categoria com ID ${id}:`, error);
    throw error;
  }
}

export async function deleteCategoria(id) {
  try {
    await api.delete(`/categoria/${id}`);
  } catch (error) {
    console.error(`Erro ao excluir receita com ID ${id}:`, error);
    throw error;
  }
}



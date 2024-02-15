import api from "./http-common";

export async function getAllUsuario() {
  try {
    const response = await api.get('/usuario');
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar usuários:', error);
    throw error; 
  }
}

export async function getUsuarioByCpf(cpf) {
  try {
    const response = await api.get(`/usuario/${cpf}`);
    return response.data;
  } catch (error) {
    console.error(`Erro ao obter usuário com CPF ${cpf}:`, error);
    throw error;
  }
}

export async function createUsuario(usuarioData) {
  try {
    const response = await api.post('/usuario', usuarioData, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao criar usuário:', error);
    throw error;
  }
}

export async function updateUsuario(cpf, usuarioData) {
  try {
    const response = await api.post(`/usuario/${cpf}`, usuarioData);
    return response.data;
  } catch (error) {
    console.error(`Erro ao atualizar usuário com CPF ${cpf}:`, error);
    throw error;
  }
}

export async function deleteUsuario(cpf) {
  try {
    await api.delete(`/usuario/${cpf}`);
  } catch (error) {
    console.error(`Erro ao excluir usuário com CPF ${cpf}:`, error);
    throw error;
  }
}

export async function loginUsuario(email, senha) {
  try {
    const response = await api.post(`/usuario/login/${email}/senha/${senha}`);
    return response.data;
  } catch (error) {
    console.error(`Erro ao logar no usuário com Email ${email}:`, error);
    throw error;
  }
}





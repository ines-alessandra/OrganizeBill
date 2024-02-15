import { useState } from 'react';
import { useRouter } from 'next/router';
import { getAllUsuario, createUsuario } from "../api/usuario"


const novoUsuarioPage = () => {
  const router = useRouter();
  const [novoUsuario, setNovoUsuario] = useState({
    nome: '',
    email: '',
    senha: '',
    cpf: '',
    dataRegistro: '', 
  });

  const handleCreateUsuario = async () => {
    try {
      // Chama handleDataEntrada para definir a data de entrada antes de criar o usuário
      const novoUsuarioComDataEntrada = handleDataEntrada();
      console.log('Novo usuário com data de entrada:', novoUsuarioComDataEntrada);
      await createUsuario(novoUsuarioComDataEntrada);
      router.push('/usuario');
    } catch (error) {
      console.error('Erro ao criar usuário:', error);
    }
  };
  
  const handleDataEntrada = () => {
    const data = new Date();
    const ano = data.getFullYear();
    const mes = String(data.getMonth() + 1).padStart(2, '0'); // Convertendo para string antes de chamar padStart()
    const dia = String(data.getDate()).padStart(2, '0'); // Convertendo para string antes de chamar padStart()
    const dataFormatada = `${ano}-${mes}-${dia}`;
    console.log('Data formatada:', dataFormatada);
    
    // Retorna um novo objeto de usuário com a data de entrada definida
    return { ...novoUsuario, dataRegistro: dataFormatada };
  };
  

  return (
    <div>
      <h1>Criar Novo Usuário</h1>

      <label htmlFor="cpf">CPF:</label>
      <input
        type="text"
        id="cpf"
        name="cpf"
        value={novoUsuario.cpf}
        onChange={(e) =>
          setNovoUsuario({ ...novoUsuario, cpf: e.target.value })
        }
      />

      <label htmlFor="nome">Nome:</label>
      <input
        type="text"
        id="nome"
        name="nome"
        value={novoUsuario.nome}
        onChange={(e) =>
          setNovoUsuario({ ...novoUsuario, nome: e.target.value })
        }
      />

      <label htmlFor="email">Email:</label>
      <input
        type="text"
        id="email"
        name="email"
        value={novoUsuario.email}
        onChange={(e) =>
          setNovoUsuario({ ...novoUsuario, email: e.target.value })
        }
      />

      <label htmlFor="senha">Senha:</label>
      <input
        type="text"
        id="senha"
        name="senha"
        value={novoUsuario.senha}
        onChange={(e) =>
          setNovoUsuario({ ...novoUsuario, senha: e.target.value })
        }
      />

      
    

      <button onClick={handleCreateUsuario}>Criar Usuário</button>
    </div>
  );
};

export default novoUsuarioPage;
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
    dataEntrada: '', 
  });

  const handleCreateUsuario = async () => {
    try {
      await createUsuario(novoUsuario);
      router.push('/usuario');
    } catch (error) {
      console.error('Erro ao criar usuário:', error);
    }
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

      <label htmlFor="dataEntrada">Data entrada:</label>
      <input
        type="text"
        id="dataEntrada"
        name="dataEntrada"
        value={novoUsuario.dataEntrada}
        onChange={(e) =>
          setNovoUsuario({ ...novoUsuario, dataEntrada: e.target.value })
        }
      />

      <button onClick={handleCreateUsuario}>Criar Usuário</button>
    </div>
  );
};

export default novoUsuarioPage;
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
    <div className='flex h-screen w-screen place-items-center justify-center items-center max-w-7xl m-auto gap-14'>
      <div className='w-2/4 border px-10 py-10 rounded-2xl'>
        <h1 className='text-2xl font-black text-center mb-10'>Criar novo usuário</h1>

        <label 
          className='block mb-1 text-gray-900' 
          htmlFor="cpf">CPF:
        </label>
        <input
          className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500 block w-full p-1.5  "
          type="text"
          id="cpf"
          name="cpf"
          value={novoUsuario.cpf}
          onChange={(e) =>
            setNovoUsuario({ ...novoUsuario, cpf: e.target.value })
          }
        />

        <label 
          className='block mb-1 mt-2 text-gray-900' 
          htmlFor="nome">Nome:
        </label>
        <input
          className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500 block w-full p-1.5  "
          type="text"
          id="nome"
          name="nome"
          value={novoUsuario.nome}
          onChange={(e) =>
            setNovoUsuario({ ...novoUsuario, nome: e.target.value })
          }
        />

        <label 
          className='block mb-1 mt-2 text-gray-900' 
          htmlFor="email">Email:
        </label>
        <input
          className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500 block w-full p-1.5  "
          type="text"
          id="email"
          name="email"
          value={novoUsuario.email}
          onChange={(e) =>
            setNovoUsuario({ ...novoUsuario, email: e.target.value })
          }
        />

        <label 
          className='block mb-1 mt-2 text-gray-900' 
          htmlFor="senha">Senha:
        </label>
        <input
          className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500 block w-full p-1.5  "
          type="password"
          id="senha"
          name="senha"
          value={novoUsuario.senha}
          onChange={(e) =>
            setNovoUsuario({ ...novoUsuario, senha: e.target.value })
          }
        />

      

        <button 
          className='text-white bg-blue-700	border border-gray-300 focus:outline-none hover:bg-blue-800 focus:ring-4 focus:ring-gray-100 font-medium rounded-md text-sm px-5 py-2 me-2 mb-2 mt-10 w-full ' 
          onClick={handleCreateUsuario}>Criar usuário
        </button>
      </div>
    </div>
  );
};

export default novoUsuarioPage;
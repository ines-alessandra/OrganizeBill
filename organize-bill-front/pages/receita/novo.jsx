import { useRouter } from "next/router";
import { createReceita } from "../api/receita";
import { useEffect, useState } from "react";
import Link from "next/link";

const NovaReceitaPage = () => {
  const router = useRouter();

  const [novaReceita, setNovaReceita] = useState({
    codReceita: '',
    data: '',
    descricao: '',
    fixo: '',
    usuario: {},
    valor: '',
  });

  useEffect(() => {
    handleDataEntrada();
  }, []);

  const handleDataEntrada = () => {
    try {
      const data = new Date();
      const ano = data.getFullYear();
      const mes = String(data.getMonth() + 1).padStart(2, '0');
      const dia = String(data.getDate()).padStart(2, '0');
      const dataFormatada = `${ano}-${mes}-${dia}`;
      console.log('Data formatada:', dataFormatada);

      setNovaReceita((prevReceita) => ({ ...prevReceita, data: dataFormatada }));
    } catch (error) {
      console.error('Erro ao formatar data:', error);
    }
  };

  const handleCreateReceita = async () => {
    try {
      const usuario = JSON.parse(localStorage.getItem('usuario'));
      const novaReceitaComUsuario = { ...novaReceita, usuario };
      console.log('Nova receita com usuário:', novaReceitaComUsuario);

      await createReceita(novaReceitaComUsuario);
      router.push("/receita");
    } catch (error) {
      console.error("Erro ao criar receita:", error);
    }
  };

  return (
    <div className='flex h-screen w-screen place-items-center justify-center items-center max-w-7xl m-auto gap-14'>
      <div className='w-2/4 border px-10 py-10 rounded-2xl'>
        <h1 className='text-2xl font-black text-center mb-10'>Criar receita</h1>
        
        
        
        <label
          className='block mb-1 text-gray-900'
          htmlFor="descricao">Descrição:
        </label>
        <input
          className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500 block w-full p-1.5"
          type="text"
          id="descricao"
          name="descricao"
          value={novaReceita.descricao}
          onChange={(e) =>
            setNovaReceita({ ...novaReceita, descricao: e.target.value })
          }
        />



        <label
          className='block mt-2 text-gray-900'
          htmlFor="valor">Valor:
        </label>
        <input
          className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500 block w-full p-1.5"
          type="text"
          id="valor"
          name="valor"
          value={novaReceita.valor}
          onChange={(e) =>
            setNovaReceita({ ...novaReceita, valor: e.target.value })
          }
        />



        <label
          className='block mt-2 text-gray-900'
          htmlFor="fixo">Fixo:
        </label>
        <select
          className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500 block w-full p-1.5"
          id="fixo"
          name="fixo"
          value={novaReceita.fixo}
          onChange={(e) =>
            setNovaReceita({ ...novaReceita, fixo: e.target.value })
          }
        >
          <option value="">Selecione</option>
          <option value="true">Sim</option>
          <option value="false">Não</option>
        </select>


        
        <button
          className='text-white bg-blue-700 border border-gray-300 focus:outline-none hover:bg-blue-800 focus:ring-4 focus:ring-gray-100 font-medium rounded-md text-sm px-5 py-2 me-2 mb-2 mt-10 w-full'
          onClick={handleCreateReceita}>Criar receita
        </button>
      </div>
    </div>
  )
}

export default NovaReceitaPage;

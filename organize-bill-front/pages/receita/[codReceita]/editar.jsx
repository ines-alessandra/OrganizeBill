import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import Link from "next/link";
import { getReceitaById, updateReceita } from "@/pages/api/receita";

const EditarReceitaPage = ({ receita }) => {
  const router = useRouter();

  const [editReceita, setEditReceita] = useState({
    descricao: receita.descricao,
    valor: receita.valor,
    fixo: receita.fixo,
    usuario: receita.usuario,
    data: receita.data,
    codReceita: receita.codReceita
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEditReceita((prevReceita) => ({ ...prevReceita, [name]: value }));
  };

  const handleUpdateReceita = async () => {
    try {
      const usuario = JSON.parse(localStorage.getItem('usuario'));
      const data = new Date();
      const ano = data.getFullYear();
      const mes = String(data.getMonth() + 1).padStart(2, '0');
      const dia = String(data.getDate()).padStart(2, '0');
      const dataFormatada = `${ano}-${mes}-${dia}`;

      const receitaAtualizada = {
        ...editReceita,
        usuario,
        data: dataFormatada
      };

      await updateReceita(receita.codReceita, receitaAtualizada);
      router.push(`/receita/${receita.codReceita}`);
    } catch (error) {
      console.error("Erro ao atualizar receita:", error);
    }
  };

  return (
    <div className='flex h-screen w-screen place-items-center justify-center items-center max-w-7xl m-auto gap-14' >
      <div className='w-2/4 border px-10 py-10 rounded-2xl' >
        <h1 className='text-2xl font-black text-center mb-10'>Editar Receita</h1>

        <form>
          <label className='block mb-1 text-gray-900'>
            Descrição:
            <input
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500 block w-full p-1.5"
              type="text"
              name="descricao"
              value={editReceita.descricao}
              onChange={handleInputChange}
            />
          </label>

          <label className='block mt-2 text-gray-900'>
            Valor:
            <input
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500 block w-full p-1.5"
              type="text"
              name="valor"
              value={editReceita.valor}
              onChange={handleInputChange}
            />
          </label>

          <label
            className='block mt-2 text-gray-900'
            htmlFor="fixo">Fixo:
          </label>
          <select
            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500 block w-full p-1.5"
            id="fixo"
            name="fixo"
            value={editReceita.fixo}
            onChange={handleInputChange}
          >
            <option value="">Selecione</option>
            <option value="true">Sim</option>
            <option value="false">Não</option>
          </select>

          
          <button 
            className='text-white bg-blue-700 border border-gray-300 focus:outline-none hover:bg-blue-800 focus:ring-4 focus:ring-gray-100 font-medium rounded-md text-sm px-5 py-2 me-2 mb-2 mt-10 w-full'
            type="button" 
            onClick={handleUpdateReceita}>
            Atualizar Receita
          </button>
        </form>
      </div>
    </div>
  );
};

export async function getServerSideProps({ params }) {
  const { codReceita } = params;

  try {
    const receita = await getReceitaById(codReceita);

    return {
      props: {
        receita,
      },
    };
  } catch (error) {
    console.error("Erro ao obter dados da receita:", error);

    return {
      props: {
        receita: {},
      },
    };
  }
}

export default EditarReceitaPage;

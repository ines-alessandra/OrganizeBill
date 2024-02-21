import { getReceitaById } from "../../api/receita";
import useRouter from "next/router";
import Link from "next/link";

const ReceitaDetailPage = ({ receita }) => {
  
  return (
    <div className='flex flex-col h-screen w-screen place-items-center justify-center items-center max-w-7xl m-auto'>

      <div className="flex w-full justify-between">
        <h1 className='text-2xl font-black text-center mb-10' >Detalhes da receita</h1>
        <Link href={`/receita/${receita.codReceita}/editar`}>
          <button 
            className="mt-4 text-gray-900 hover:text-white border border-gray-800 hover:bg-gray-900 focus:ring-1 focus:outline-none focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2 text-center me-2 mb-2 dark:border-gray-600 dark:text-gray-400 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-800">
            Editar
          </button>
        </Link>
      </div>
      
      
      <div className='w-full border px-10 py-10 rounded-2xl' >


        <div className="w-full flex justify-between	">
          <div>
            <p className="font-black " >Descrição:</p>
            <p>{receita.descricao}</p>
          </div>

          <div>
            <p className="font-black">Valor:</p>
            <p>{receita.valor}</p>
          </div>
          
          <div>
            <p className="font-black">Data:</p>
            <p>{receita.data}</p>
          </div>
          
          {/* <p>Codigo receita: {receita.codReceita}</p> */}
        </div>
      </div>
    </div>
  );
};

export async function getServerSideProps({ params }) {
  const { codReceita } = params;

  try {
    console.log("Código da receita:", codReceita);

    const receita = await getReceitaById(codReceita);
    console.log("Receita Data:", receita.data);

    return {
      props: {
        receita,
      },
    };
  } catch (error) {
    console.error('Erro ao obter dados da receita:', error);

    return {
      props: {
        receita: {}, 
      },
    };
  }
}

export default ReceitaDetailPage;

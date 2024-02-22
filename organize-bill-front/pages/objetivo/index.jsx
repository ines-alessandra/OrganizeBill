import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import style from "./Objetivo.module.css"
import Link from "next/link";
import { Montserrat_Alternates } from "next/font/google";
import Objetivo from "../components/Objetivo";
import { DocumentPlusIcon } from "@heroicons/react/24/solid";
import { getAllObjetivo } from "../api/objetivo";
const objetivo = () => {

  const [objetivos, setObjetivos] = useState([]);

  useEffect(() => {
    loadObjetivo();
  }
    , []);
  const loadObjetivo = async () => {
    try {
      const usuario = JSON.parse(localStorage.getItem('usuario'));
      const objetivos = await getAllObjetivo(usuario.cpf);
      setObjetivos(objetivos);
      console.log(objetivos);
    } catch (error) {
      console.error('Erro ao carregar objetivos:', error);
    }
  };

  return (
    <div className="flex flex-col h-screen max-w-7xl justify-center m-auto" >
      <div className="flex justify-between items-center w-full ">
        <h1 className="text-2xl font-black" >Objetivos Financeiros</h1>
        <div className="flex gap-3">
          <Link href="/objetivo/novo">
            <button
              className="flex gap-2 focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-1 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2" >
              <DocumentPlusIcon className="h-4 w-4 text-white" />
              Adicionar Objetivo
            </button>
          </Link>
        </div>
      </div>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4 p-4">
        {objetivos.map((objetivo, index) => (
          <Objetivo objetivo={objetivo} key={index} />
        ))}
      </div>





    </div>







  )
}

export default objetivo;
import { useEffect, useState } from "react";
import { useMutation } from "react-query";

import Link from "next/link";
import { deleteDespesa, getDespesaByData } from "@/pages/api/despesa";
import { DocumentPlusIcon } from "@heroicons/react/24/solid";
import Despesa from "../Despesa";
import { deleteCategoria, getAllCategoria, getCategoriaById } from "@/pages/api/categoria";
import Categoria from "../Categoria";


const Categorias = () => {
  const [categoria, setCategoria] = useState([]);
  const [editar, setEditar] = useState(false); 
  const [fixo, setFixo] = useState(false);

  useEffect(() => {
    mutate();
  }, [fixo]);

  const { state, mutate } = useMutation(
    async () => {
      const usuario = JSON.parse(localStorage.getItem('usuario'));
      return getAllCategoria(usuario.cpf);
    }, {
    onSuccess: (data) => {
      const categoriasOrdenadas = data.sort((a, b) => a.codCategoria - b.codCategoria);
      setCategoria(categoriasOrdenadas);
    },
    onError: (error) => {
      console.log(error)
    }
  }
  );


  return (
    <div className="flex flex-col h-screen max-w-7xl justify-center m-auto" >
      <div className="flex justify-between items-center w-full ">
        <h1 className="text-2xl font-black" >Categorias</h1>
        <div className="flex gap-3">
          <Link href="/categoria/nova">
            <button
              className="flex gap-2 focus:outline-none text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2" >
              <DocumentPlusIcon className="h-4 w-4 text-white" />
              Adicionar Categoria
            </button>
          </Link>

        </div>

      </div>


      <div className="relative overflow-x-auto shadow-md sm:rounded-lg w-full mt-6">
        <table className="w-full text-base text-left rtl:text-right text-gray-800 ">
          <thead className="text-base text-white uppercase bg-blue-800">
            <tr>
              <th className="px-6 py-3 text-base" >Nome</th>
              <th className="px-6 py-3 text-base" >Descrição</th>
              <th className="px-6 py-3 text-base" >Gasto Médio</th>
              <th className="px-6 py-3 text-base" >Ações</th>
            </tr>
          </thead>

          <tbody>
            <>
              {categoria.map((categoria, index) => {
                  return (
                    <Categoria key={index} categoria={categoria} />
                  )
              })}
            </>
          </tbody>
        </table >
      </div >
    </div >
  )
}

export default Categorias;
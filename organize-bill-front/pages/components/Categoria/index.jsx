"use client"

import { deleteCategoria, updateCategoria, updatecategoria } from "@/pages/api/categoria";
import { PaperAirplaneIcon, PencilIcon, TrashIcon, XMarkIcon } from "@heroicons/react/24/solid";
import Link from "next/link";
import { useState } from "react";



const Categoria = ({categoria, index}) => {
  
  const [editar, setEditar] = useState(false);
  const [editData, setEditData] = useState(categoria);

  const editarCategoria = () => {
    if (editar === true) {
      setEditar(false);
    } else {
      setEditar(true);

    }
  }
  const handleEditarCategoria = async (id, editData) => {
    try {
      console.log(id, editData)
      await updateCategoria(id, editData);
      // Atualiza a categoria específica no estado

      // Ordena as categorias após a atualização
      window.location.reload();
    } catch (error) {
      console.error('Erro ao editar categoria:', error);
    }
  };

  const handleExcluirCategoria = async (id) => {
    try {
      console.log(id)
      deleteCategoria(id);
      window.location.reload();
    } catch (error) {
      console.error('Erro ao excluir despesa:', error);
    }
  };
  return (
    <>
      {editar ?
        <tr>
          <td className="px-6 py-3 text-base" >
            <input
              type="text"
              placeholder={categoria.nome}
              value={editData.nome}
              onChange={(e) => setEditData({ ...editData, nome: e.target.value })}

            />
          </td >
          <td className="px-6 py-3 text-base" >
            <input
              type="text"
              placeholder={categoria.descricao}
              value={editData.descricao}
              onChange={(e) => setEditData({ ...editData, descricao: e.target.value })}

            />
          </td>
          <td className="px-6 py-3 text-base" >

            <input
              type="Number"
              placeholder={categoria.gastoMedio}
              value={editData.gastoMedio}
              onChange={(e) => setEditData({ ...editData, gastoMedio: e.target.value })}
            />
          </td>

          <td className="flex gap-4 px-6 py-3" >
            <button title="Enviar" onClick={() => handleEditarCategoria(Number(editData.codCategoria), editData)}>
              <PaperAirplaneIcon className="h-5 w-5 text-gray-800" />
            </button>
            <Link href="/categoria" title="Cancelar" onClick={() => editarCategoria()}>
              <XMarkIcon className="h-5 w-5 text-gray-800" />
            </Link>


          </td>
        </tr >

        :
        <tr key={index} >
          <td className="px-6 py-3 text-base" >{categoria.nome}</td >
          <td className="px-6 py-3 text-base" >{categoria.descricao}</td>
          <td className="px-6 py-3 text-base" >{categoria.gastoMedio}</td>
          <td className="flex gap-4 px-6 py-3" >
            <button title="Editar" onClick={() => editarCategoria()}>
              <PencilIcon className="h-5 w-5 text-gray-800" />
            </button>

            <button title="Excluir" onClick={() => handleExcluirCategoria(Number(categoria.codCategoria))}>
              <TrashIcon className="h-5 w-5 text-gray-800" />
            </button>
          </td>
        </tr>

      }

    </>
  )
}

export default Categoria;
"use client"

import { updateDespesa } from "@/pages/api/despesa";
import { PaperAirplaneIcon, PencilIcon, TrashIcon, XMarkIcon } from "@heroicons/react/24/solid";
import Link from "next/link";
import { useState } from "react";



const Despesa = ({despesa, index}) => {
  
  const [editar, setEditar] = useState(false);
  const [editData, setEditData] = useState(despesa);

  const editarDespesa = () => {
    if (editar === true) {
      setEditar(false);
    } else {
      setEditar(true);

    }
  }
  const handleEditarDespesa = async (id, editData) => {
    try {
      await updateDespesa(id, editData);
      // Atualiza a despesa específica no estado

      // Ordena as despesas após a atualização
      window.location.reload();
    } catch (error) {
      console.error('Erro ao editar despesa:', error);
    }
  };
  return (
    <>
      {editar ?
        <tr>
          <td className="px-6 py-3 text-base" >
            <input
              type="text"
              placeholder={despesa.descricao}
              value={editData.descricao}
              onChange={(e) => setEditData({ ...editData, descricao: e.target.value })}

            />
          </td >
          <td className="px-6 py-3 text-base" >
            <input
              type="text"
              placeholder={despesa.valor}
              value={editData.valor}
              onChange={(e) => setEditData({ ...editData, valor: e.target.value })}

            />
          </td>
          <td className="px-6 py-3 text-base" >

            <input
              type="date"
              placeholder={despesa.data}
              value={editData.data}
              onChange={(e) => setEditData({ ...editData, data: e.target.value })}
            />
          </td>
          <td className="px-6 py-3 text-base" >
            <input type="checkbox"
              checked={editData.fixo}
              onChange={(e) => setEditData({ ...editData, fixo: e.target.checked })}
              />
          </td>
          <td className="flex gap-4 px-6 py-3" >
            <button title="Enviar" onClick={() => handleEditarDespesa(editData.codDespesa, editData)}>
              <PaperAirplaneIcon className="h-5 w-5 text-gray-800" />
            </button>
            <Link href="/despesa" title="Cancelar" onClick={() => editarDespesa()}>
              <XMarkIcon className="h-5 w-5 text-gray-800" />
            </Link>


          </td>
        </tr >

        :
        <tr key={index} >
          <td className="px-6 py-3 text-base" >{despesa.descricao}</td >
          <td className="px-6 py-3 text-base" >{despesa.valor}</td>
          <td className="px-6 py-3 text-base" >{despesa.data}</td>
          <td className="px-6 py-3 text-base" >{despesa.fixo ? 'Sim' : 'Não'}</td>
          <td className="flex gap-4 px-6 py-3" >
            <button title="Editar" onClick={() => editarDespesa()}>
              <PencilIcon className="h-5 w-5 text-gray-800" />
            </button>

            <button title="Excluir" onClick={() => handleExcluirDespesa(despesa.codDespesa)}>
              <TrashIcon className="h-5 w-5 text-gray-800" />
            </button>
          </td>
        </tr>

      }

    </>
  )
}

export default Despesa;
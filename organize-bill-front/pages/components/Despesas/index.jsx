import { getAllCategoria } from "@/pages/api/categoria";
import { useEffect, useState } from "react";
import { useMutation } from "react-query";

import style from './despesas.module.scss';
import Image from "next/image";
import Link from "next/link";
import { deleteDespesa, getDespesaByData, updateDespesa } from "@/pages/api/despesa";
import { DocumentPlusIcon, PencilIcon, TrashIcon } from "@heroicons/react/24/solid";
import Despesa from "../Despesa";


const Despesas = ({despesa}) => {
  const [despesas, setDespesas] = useState([]);
  const [filtroSelecionado, setFiltroSelecionado] = useState('mes');
  const [fixo, setFixo] = useState(false);
  const [editar, setEditar] = useState(false); 

  useEffect(() => {
    // A função `mutate` é chamada sem argumentos aqui
    mutate();
    // Adicione `filtroSelecionado` e `fixo` como dependências
  }, [filtroSelecionado, fixo]);

  const { state, mutate } = useMutation(
    async () => {
      const usuario = JSON.parse(localStorage.getItem('usuario'));
      return getDespesaByData(usuario.cpf, filtroSelecionado, fixo);
    }, {
    onSuccess: (data) => {
      const despesasOrdenadas = data.sort((a, b) => a.codDespesa - b.codDespesa);
      setDespesas(despesasOrdenadas);
    },
    onError: (error) => {
      console.log(error)
    }
  }
  );



  const handleExcluirDespesa = async (id) => {
    try {
      await deleteDespesa(id);
      setDespesas([]);
      mutate();
    } catch (error) {
      console.error('Erro ao excluir receita:', error);
    }
  };
  console.log(editar)
  return (
    <div className="flex flex-col h-screen max-w-7xl justify-center m-auto" >
      <div className="flex justify-between items-center w-full ">
        <h1 className="text-2xl font-black" >Receitas</h1>
        <div className="flex gap-3">
          <Link href="/receita/novo">
            <button
              className="flex gap-2 focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2" >
              <DocumentPlusIcon className="h-4 w-4 text-white" />
              Adicionar Despesa
            </button>
          </Link>

          <select
            className="flex gap-2 text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-1 focus:ring-gray-100 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700"
            value={filtroSelecionado} onChange={(e) => setFiltroSelecionado(e.target.value)}>
            <option value="">Filtro data</option>
            <option value="dia">Dia</option>
            <option value="semana">Semanal</option>
            <option value="mes">Mensal</option>
            <option value="ano">Anual</option>
          </select>
        </div>
        <div>
          <input
            className="gap-2 focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2"
            type="checkbox" value={fixo} onChange={(e) => setFixo(e.target.checked)} />
          <label>Despesas Fixas</label>
        </div>
      </div>


      <div className="relative overflow-x-auto shadow-md sm:rounded-lg w-full mt-6">
        <table className="w-full text-base text-left rtl:text-right text-gray-800 ">
          <thead className="text-base text-white uppercase bg-blue-800">
            <tr>
              <th className="px-6 py-3 text-base" >Descrição</th>
              <th className="px-6 py-3 text-base" >Valor</th>
              <th className="px-6 py-3 text-base" >Data</th>
              <th className="px-6 py-3 text-base" >Fixo</th>
              <th className="px-6 py-3 text-base" >Ações</th>
            </tr>
          </thead>

          <tbody>
            <>
              {despesas.map((despesa, index) => {
                  return (
                    <Despesa key={index} despesa={despesa} />
                  )
              })}
              
            </>
          </tbody>
        </table >
      </div >
    </div >
  )
}

export default Despesas;
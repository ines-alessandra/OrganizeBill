import { useRouter } from "next/router";
import { createReceita, deleteReceita, getAllReceita, getReceitasByData, getTotalReceitasMensais, updateReceita } from "../api/receita";
import { useEffect, useState } from "react";
import style from "./Receita.module.css"
import Link from "next/link";
import { DocumentPlusIcon, PencilIcon, TrashIcon } from "@heroicons/react/24/solid";


const receita = () => {
    const [receitas, setReceitas] = useState([]);

    const [filtroSelecionado, setFiltroSelecionado] = useState('mes');

    useEffect(() => {
        loadReceitas();
    }, [filtroSelecionado]);





    const loadReceitas = async () => {
        try {
            const usuario = JSON.parse(localStorage.getItem('usuario'));
           
            const receitasData = await getReceitasByData(usuario.cpf, filtroSelecionado, false);
            console.log('Receitas Data:', receitasData);
            setReceitas(Array.isArray(receitasData) ? receitasData : []);
        } catch (error) {
            console.error('Erro ao carregar receitas:', error);
        }
    };


    const handleExcluirReceita = async (id) => {
        try {
            await deleteReceita(id);
            setReceitas([]);
            loadReceitas();
        } catch (error) {
            console.error('Erro ao excluir receita:', error);
        }
    };




    return (
        <div className="flex flex-col h-screen max-w-7xl justify-center m-auto" >
            <div className="flex justify-between items-center w-full ">
                <h1 className="text-2xl font-black" >Receitas</h1>
                <div className="flex gap-3">
                    <Link href="/receita/novo">
                        <button 
                            className="flex gap-2 focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2" >
                            <DocumentPlusIcon className="h-4 w-4 text-white" />
                            Adicionar receita
                    </button>
                    </Link>

                    <select 
                    className="flex gap-2 text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-1 focus:ring-gray-100 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700"
                    value={filtroSelecionado} onChange={(e) => setFiltroSelecionado(e.target.value)}>
                        <option value="mes">Filtro data</option>
                        <option value="dia">Dia</option>
                        <option value="semana">Semanal</option>
                        <option value="mes">Mensal</option>
                        <option value="ano">Anual</option>
                    </select>
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
                        {receitas.map((receita, index) => (
                            <tr key={index}>
                                <td className="px-6 py-3 text-base" >{receita.descricao}</td>
                                <td className="px-6 py-3 text-base" >{receita.valor}</td>
                                <td className="px-6 py-3 text-base" >{receita.data}</td>
                                <td className="px-6 py-3 text-base" >{receita.fixo ? 'Sim' : 'Não'}</td>
                                <td className="flex gap-4 px-6 py-3" >
                                    <Link  title="Editar" href={`/receita/${receita.codReceita}`}>
                                        <PencilIcon className="h-5 w-5 text-gray-800" />
                                    </Link>

                                    <button title="Excluir" onClick={() => handleExcluirReceita(receita.codReceita)}>
                                        <TrashIcon className="h-5 w-5 text-gray-800" />
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default receita;
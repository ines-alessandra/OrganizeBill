import { createObjetivo, getAllObjetivo, addValueObjetivo, updateObjetivo } from "../../api/objetivo.js";
import { useEffect, useState } from "react";
import Link from "next/link";
import { ArrowUturnLeftIcon } from "@heroicons/react/24/solid";

const NovoObjetivo = () => {
    const [objetivo, setObjetivo] = useState({
        dataCriacao: '',
        nome: '',
        valor: 0,
        valorTransitorio: 0,
        valorMeta: 0,
        descricao: '',
        usuario: null
    });

    const handleDataEntrada = () => {
        const data = new Date();
        const ano = data.getFullYear();
        const mes = String(data.getMonth() + 1).padStart(2, '0'); // Convertendo para string antes de chamar padStart()
        const dia = String(data.getDate()).padStart(2, '0'); // Convertendo para string antes de chamar padStart()
        const dataFormatada = `${ano}-${mes}-${dia}`;
        console.log('Data formatada:', dataFormatada);
        const usuario = JSON.parse(localStorage.getItem('usuario'));
        // Retorna um novo objeto de usuário com a data de entrada definida
        return { ...objetivo, dataCriacao: dataFormatada, usuario: usuario };
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const novoObjetivo = handleDataEntrada();
        console.log(novoObjetivo);
        const response = await createObjetivo(novoObjetivo);
        console.log(response);
        window.location.href = "/objetivo";
    }





    return (
        <div className="flex flex-col h-screen max-w-7xl justify-center m-auto" >
            <div className="flex justify-between items-center w-full ">
                <Link href="/objetivo">
                    <button
                        className="flex gap-2 focus:outline-none text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2" >
                        <ArrowUturnLeftIcon className="h-4 w-4 text-white" />
                        Voltar
                    </button>
                </Link>
            </div>

            <section className="bg-white white:bg-white-900">
                <div className="py-8 px-4 mx-auto max-w-2xl lg:py-16">
                    <h2 className="mb-4 text-xl font-bold text-blue-900 blue:text-blue">Adicionar novo Objetivo</h2>
                    <form onSubmit={handleSubmit}>
                        <div className="grid gap-4 sm:grid-cols-2 sm:gap-6">

                            <div className="sm:col-span-2">
                                <label htmlFor="nome" className="block mb-2 text-sm font-medium text-blue-900 white:text-black">Nome</label>
                                <input
                                    id="nome"
                                    name="nome"
                                    type="text"
                                    className="bg-blue-50 border border-blue-300 text-blue-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 blue:bg-blue-700 blue:border-blue-600 blue:placeholder-blue-400 blue:text-white blue:focus:ring-primary-500 blue:focus:border-primary-500"
                                    value={objetivo.nome}
                                    onChange={(e) => setObjetivo({ ...objetivo, nome: e.target.value })}
                                    placeholder="Nome do objetivo" />
                            </div>

                            <div className="sm:col-span-2">
                                <label htmlFor="descricao" className="block mb-2 text-sm font-medium text-blue-900 white:text-black">Descrição</label>
                                <textarea id="descricao" name="descricao" rows="8" className="bg-blue-50 border border-blue-300 text-blue-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 blue:bg-blue-700 blue:border-blue-600 blue:placeholder-blue-400 blue:text-blue blue:focus:ring-primary-500 blue:focus:border-primary-500"
                                    value={objetivo.descricao}
                                    onChange={(e) =>
                                        setObjetivo({ ...objetivo, descricao: e.target.value })}
                                    placeholder="Descrição do Objetivo"></textarea>
                            </div>

                            <div className="w-full">
                                <label htmlFor="dataLimite" className="block mb-2 text-sm font-medium text-blue-900 white:text-black">Data Limite</label>
                                <input type="date" name="dataLimite" id="dataLimite" className="bg-blue-50 border border-blue-300 text-blue-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 blue:bg-blue-700 blue:border-blue-600 blue:placeholder-blue-400 blue:text-blue blue:focus:ring-primary-500 blue:focus:border-primary-500"
                                    value={objetivo.dataLimite}
                                    onChange={(e) =>
                                        setObjetivo({ ...objetivo, dataLimite: e.target.value })}
                                    placeholder="Selecione a data" required="" />
                            </div>
                            <div className="w-full">
                                <label htmlFor="valorMeta" className="block mb-2 text-sm font-medium text-blue-900 white:text-black">Valor Meta</label>
                                <input type="number" name="valorMeta" id="valorMeta" className="bg-blue-50 border border-blue-300 text-blue-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 blue:bg-blue-700 blue:border-blue-600 blue:placeholder-blue-400 blue:text-blue blue:focus:ring-primary-500 blue:focus:border-primary-500"
                                    value={objetivo.valorMeta}
                                    onChange={(e) =>
                                        setObjetivo({ ...objetivo, valorMeta: parseFloat(e.target.value) })}
                                    placeholder="$2999" required="" />
                            </div>
                            <div className="w-full">
                                <label htmlFor="valorTransitorio" className="block mb-2 text-sm font-medium text-blue-900 white:text-black">Valor Mensal</label>
                                <input type="number" name="valorTransitorio" id="valorTransitorio" className="bg-blue-50 border border-blue-300 text-blue-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 blue:bg-blue-700 blue:border-blue-600 blue:placeholder-blue-400 blue:text-blue blue:focus:ring-primary-500 blue:focus:border-primary-500"
                                    value={objetivo.valorTransitorio}
                                    onChange={(e) =>
                                        setObjetivo({ ...objetivo, valorTransitorio:parseFloat(e.target.value) })}
                                    placeholder="$2999" required="" />
                            </div>


                        </div>
                        <button
                            type="submit" className="bg-blue-50 border border-blue-300 text-blue-900 inline-flex items-center px-5 py-2.5 mt-4 sm:mt-6 text-sm font-medium text-center text-blue bg-primary-700 rounded-lg focus:ring-4 focus:ring-primary-700 blue:focus:ring-primary-900 hover:bg-primary-800">
                            Adicionar Objetivo
                        </button>
                    </form>
                </div>
            </section>
        </div>
    );
}

export default NovoObjetivo;
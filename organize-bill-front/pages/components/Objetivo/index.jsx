import { createObjetivo, getAllObjetivo, addValueObjetivo, updateObjetivo } from "../../api/objetivo.js";
import { useEffect, useState } from "react";
import Link from "next/link";

const Objetivo = ({ objetivo }) => {
    const [mostrarInput, setMostrarInput] = useState(false);
    const [valorAdicionado, setValorAdicionado] = useState('');
    const [modoEdicao, setModoEdicao] = useState(false);

    const handleConfirmarClick = async () => {
        console.log("Valor adicionado: ", valorAdicionado);
        // Aqui você pode adicionar a lógica para efetivamente adicionar os fundos
        // Por exemplo, atualizar o estado do componente ou fazer uma chamada de API

        const response = await addValueObjetivo(objetivo.codObjetivo, valorAdicionado);
        console.log(response);

        setMostrarInput(false);
        setValorAdicionado('');
        window.location.href = "/objetivo";
    };



    // Estados temporários para edição
    const [tituloEditado, setTituloEditado] = useState(objetivo.nome);
    const [descricaoEditada, setDescricaoEditada] = useState(objetivo.descricao);
    const [valorMensalEditado, setValorMensalEditado] = useState(objetivo.valorTransitorio);
    const [dataLimiteEditada, setDataLimiteEditada] = useState(objetivo.dataLimite);

    const handleEditar = () => {
        setModoEdicao(true);
    };

    const handleUpdate = async () => {
        const response = updateObjetivo(objetivo.id, {
            nome: tituloEditado,
            descricao: descricaoEditada,
            valorTransitorio: valorMensalEditado,
            dataLimite: dataLimiteEditada,
            categoria: objetivo.categoria,
            dataCriacao: objetivo.dataCriacao,
            codObjetivo: objetivo.codObjetivo,
            usuario: objetivo.usuario,
            valor: objetivo.valor,
            valorMeta: objetivo.valorMeta
        });

        console.log(response);
    };

    const verificarDataLimite = () => {
        const hoje = new Date();
        const limite = new Date(objetivo.dataLimite);

        // Comparando as datas (desconsiderando o horário)
        hoje.setHours(0, 0, 0, 0);
        limite.setHours(0, 0, 0, 0);

        return hoje > limite;
    };



    const handlePorcentagem = () => {
        let porcentagem = objetivo.valor === 0? 0:  (objetivo.valor * 100) / objetivo.valorMeta;
        return porcentagem;
    }

    return (
        <div className="max-w-md mx-auto bg-white rounded-xl shadow-md overflow-hidden md:max-w-2xl">
            <div className="p-8">
                {modoEdicao ? (
                    <>
                        <input className="text-lg font-semibold w-full" value={tituloEditado} onChange={e => setTituloEditado(e.target.value)} />
                        <textarea className="mt-2 w-full" value={descricaoEditada} onChange={e => setDescricaoEditada(e.target.value)} />
                        <input className="mt-2 w-full" type="number" value={valorMensalEditado} onChange={e => setValorMensalEditado(parseFloat(e.target.value))} />
                        <input className="mt-2 w-full" type="date" value={dataLimiteEditada} onChange={e => setDataLimiteEditada(e.target.value)} />
                        <button className="mt-4 bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded" onClick={handleUpdate}>
                            Salvar
                        </button>
                        <button className="mt-4 bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded" onClick={() => { setModoEdicao(false) }}>
                            Cancelar
                        </button>
                    </>
                ) : (
                    <>
                        <div className="uppercase tracking-wide text-sm text-indigo-500 font-semibold">{objetivo.nome}</div>
                        <div className="block mt-1 text-lg leading-tight font-medium text-black">R$ {objetivo.valor} / R$ {objetivo.valorMeta}</div>
                        <p className="text-gray-600 mt-2">
                            {objetivo.descricao}
                        </p>
                        <div className="text-gray-600 mt-2">
                            Valor mensal: <span className="font-semibold">R$ {objetivo.valorTransitorio}</span>
                        </div>
                        <div className="text-gray-600 mt-2">
                            Data de criação: <span className="font-semibold">{objetivo.dataCriacao}</span>
                        </div>
                        <div className="text-gray-600 mt-2">
                            Data limite: <span className="font-semibold">{objetivo.dataLimite}</span>
                        </div>
                        <div className="w-full bg-gray-200 rounded-full h-2.5 dark:bg-gray-700 mt-4">
                            <div className="bg-green-600 h-2.5 rounded-full" style={{ width: `${handlePorcentagem()}%` }}></div>
                        </div>
                        {handlePorcentagem() >= 100 && (
                            <div className="mt-4 bg-opacity-70 bg-green-500 text-white text-center p-2 rounded">
                                Parabéns! Você atingiu sua meta!
                            </div>
                        )}

                        {verificarDataLimite() && (
                            <div className="mt-4 bg-opacity-70 bg-red-500 text-white text-center p-2 rounded">
                                A data limite para este objetivo já passou!
                            </div>
                        )}

                        <button className="mt-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" onClick={handleEditar}>
                            Editar
                        </button>


                        {!mostrarInput && (
                            <div className="mt-4">
                                <button onClick={() => { setMostrarInput(!mostrarInput) }} className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                                    Adicionar Fundos
                                </button>
                            </div>
                        )}


                        {mostrarInput && (
                            <div className="mt-4">
                                <input
                                    type="number"
                                    value={valorAdicionado}
                                    onChange={(e) => setValorAdicionado(e.target.value)}
                                    className="shadow appearance-none border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                                    placeholder="Valor a adicionar"
                                />
                                <button onClick={handleConfirmarClick} className="ml-2 bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">
                                    Confirmar
                                </button>
                            </div>
                        )}
                    </>)}
            </div>



        </div>


    )
}

export default Objetivo;
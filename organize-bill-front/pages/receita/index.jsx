import { useRouter } from "next/router";
import { createReceita, deleteReceita, getAllReceita, getReceitasByData, getTotalReceitasMensais, updateReceita } from "../api/receita";
import { useEffect, useState } from "react";
import style from "./Receita.module.css"
import Link from "next/link";

const receita = () => {
    const [receitas, setReceitas] = useState([]);
    const [usuario, setUsuario] = useState([]);
    const [filtroSelecionado, setFiltroSelecionado] = useState('mes');  

    useEffect(() => {
        loadUsuario();
        loadReceitas();

    }, [filtroSelecionado]);

    const loadUsuario = () => {
        const usuarioStorage = JSON.parse(localStorage.getItem('usuario'));
        console.log('Usuario:', usuarioStorage);
        setUsuario(usuarioStorage);
    };



    const loadReceitas = async () => {
        try {
            console.log('Usuario:', usuario)
            console.log('Filtro:', filtroSelecionado)
            const receitasData = await getAllReceita();
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
        <div>
            <h1>Receitas</h1>
            <Link href="/receita/novo">
                Adicionar Receita
            </Link>

            <select value={filtroSelecionado} onChange={(e) => setFiltroSelecionado(e.target.value)}>
                <option value="mes">Filtro data</option>
                <option value="dia">Dia</option>
                <option value="semana">Semanal</option>
                <option value="mes">Mensal</option>
                <option value="ano">Anual</option>
            </select>


            <table className={style.table}>

                <thead>
                    <tr>
                        <th>Descrição</th>
                        <th>Valor</th>
                        <th>Data</th>
                        <th>Fixo</th>
                        <th>Ações</th>
                    </tr>
                </thead>

                <tbody>
                    {receitas.map((receita, index) => (
                        <tr key={index}>
                            <td>{receita.descricao}</td>
                            <td>{receita.valor}</td>
                            <td>{receita.data}</td>
                            <td>{receita.fixo ? 'Sim' : 'Não'}</td>
                            <td>
                                <Link href={`/receita/${receita.codReceita}`}>
                                    Editar
                                </Link>

                                <button onClick={() => handleExcluirReceita(receita.codReceita)}>
                                    Excluir
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default receita;
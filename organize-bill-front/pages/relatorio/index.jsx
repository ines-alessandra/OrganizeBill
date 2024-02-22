import { useRouter } from "next/router";
import { getAllCategoria, createCategoria } from "../api/categoria.js";
import { getDespesaByCategoria, getDespesaByData, createDespesa } from "../api/despesa.js";
import { getRelatorioByMeses, getRelatorioReceitaByMeses, getRelatorioDespesaByMeses } from "../api/relatorio.js";
import { useEffect, useState } from "react";
import style from "./Relatorio.module.css"
import Link from "next/link";
import { Chart } from "react-google-charts";
import { createReceita, deleteReceita, getAllReceita, getReceitasByData, getTotalReceitasMensais, updateReceita } from "../api/receita";
import Despesa from "../Despesa";



const relatorio = () => {
  const [filtroData, setFiltroData] = useState(1);
  const [relatorioData, setRelatorioData] = useState([]);
  const [receitas, setReceitas] = useState([]);
  const [despesas, setDespesas] = useState([]);
  const [tipoData, setTipoData] = useState("mes");
  const [tipoRelatorio, setTipoRelatorio] = useState("geral");
  const [options, setOptions] = useState({});



  useEffect(() => {
    filtroString();
    loadRelatorioData();
    loadReceitas();
    loadDespesas();
  }, [filtroData, tipoData, tipoRelatorio]);

  const filtroString = async () => {
    console.log(filtroData);
    switch (filtroData) {
      case "1":
        setTipoData("mes");
        break;
      case "3":
        setTipoData("trimestral");
        break;
      case "6":
        setTipoData("semestral");
        break;
      case "12":
        setTipoData("ano");
        break;
      default:
        setTipoData('outro'); // Pode ser ajustado conforme necessário
    }
    console.log(tipoData);
  }

  const loadRelatorioData = async () => {
    try {

      const usuario = JSON.parse(localStorage.getItem('usuario'));
      let dataIni
      let relatoriosData;
   

      if (tipoRelatorio === "despesa") {
        dataIni = [
          ["Data", "Total Despesa"]
        ];
        setOptions({
          colors: ['#e60a0a' ],
        } )
        relatoriosData = await getRelatorioDespesaByMeses(usuario.cpf, filtroData);
      } else if (tipoRelatorio === "receita") {
        dataIni = [
          ["Data", "Total Receita"]
        ];
        setOptions( {
          colors: ['#1e970e' ],
        } )
        relatoriosData = await getRelatorioReceitaByMeses(usuario.cpf, filtroData); // Corrigido de getRelatoriReceitaByMeses para getRelatorioReceitaByMeses
      } else {
        dataIni = [
          ["Data", "Total Receita", "Total Despesa"]
        ];
        setOptions({
          colors: ['#1e970e','#e60a0a' ],
        } )
        relatoriosData = await getRelatorioByMeses(usuario.cpf, filtroData);
      }

      console.log('Receitas Data:', relatoriosData);
      Array.prototype.push.apply(dataIni, relatoriosData);
      setRelatorioData(dataIni)
    } catch (error) {
      console.error('Erro ao carregar relatorio:', error);
    }
  };

  const loadReceitas = async () => {
    try {


      const usuario = JSON.parse(localStorage.getItem('usuario'));

      const receitasData = await getReceitasByData(usuario.cpf, tipoData, false);
      console.log('Receitas Data:', receitasData);
      setReceitas(Array.isArray(receitasData) ? receitasData : []);
    } catch (error) {
      console.error('Erro ao carregar receitas:', error);
    }
  };

  const loadDespesas = async () => {
    try {
      const usuario = JSON.parse(localStorage.getItem('usuario'));

      const despesasData = await getDespesaByData(usuario.cpf, tipoData, false);
      console.log('Receitas Data:', despesasData);
      setDespesas(Array.isArray(despesasData) ? despesasData : []);
    } catch (error) {
      console.error('Erro ao carregar despesas:', error);
    }
  };



  return (
    <div className="flex flex-col h-screen max-w-7xl justify-center m-auto" >
      <select
        className="flex gap-2 text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-1 focus:ring-gray-100 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700"
        value={filtroData} onChange={(e) => setFiltroData(e.target.value)}>
        <option value="1">Filtro data</option>
        <option value="1">Mensal</option>
        <option value="3">Trimestral</option>
        <option value="6">Semestral</option>
        <option value="12">Anual</option>
      </select>
      <select
        className="flex gap-2 text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-1 focus:ring-gray-100 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700"
        value={tipoRelatorio} onChange={(e) => setTipoRelatorio(e.target.value)}>
        <option value="receita">Receitas</option>
        <option value="despesa">Despesas</option>
        <option value="geral">Geral</option>
      </select>
      <Chart
        chartType="Bar"
        width="100%"
        height="400px"
        data={relatorioData}
        options={options}
      />

      <div className="relative overflow-x-auto shadow-md sm:rounded-lg w-full mt-6">
        {tipoRelatorio != "despesa" &&
          <>
            <h1 className="text-2xl font-black" >Receitas</h1>
            <table className="w-full text-base text-left rtl:text-right text-gray-800 ">
              <thead className="text-base text-white uppercase bg-blue-800">
                <tr>
                  <th className="px-6 py-3 text-base" >Descrição</th>
                  <th className="px-6 py-3 text-base" >Valor</th>
                  <th className="px-6 py-3 text-base" >Data</th>
                  <th className="px-6 py-3 text-base" >Fixo</th>

                </tr>
              </thead>

              <tbody>
                {receitas.map((receita, index) => (
                  <tr key={index}>
                    <td className="px-6 py-3 text-base" >{receita.descricao}</td>
                    <td className="px-6 py-3 text-base" >{receita.valor}</td>
                    <td className="px-6 py-3 text-base" >{receita.data}</td>
                    <td className="px-6 py-3 text-base" >{receita.fixo ? 'Sim' : 'Não'}</td>

                  </tr>
                ))}
              </tbody>
            </table>

          </>
        }


        {tipoRelatorio != "receita" &&
        <>
        <h1 className="text-2xl font-black" >Despesas</h1>
        <div className="relative overflow-x-auto shadow-md sm:rounded-lg w-full mt-6">
          <table className="w-full text-base text-left rtl:text-right text-gray-800 ">
            <thead className="text-base text-white uppercase bg-blue-800">
              <tr>
                <th className="px-6 py-3 text-base" >Descrição</th>
                <th className="px-6 py-3 text-base" >Valor</th>
                <th className="px-6 py-3 text-base" >Data</th>
                <th className="px-6 py-3 text-base" >Fixo</th>
                <th className="px-6 py-3 text-base" >Categoria</th>

              </tr>
            </thead>

            <tbody>
              {despesas.map((despesa, index) => (
                <tr key={index}>
                  <td className="px-6 py-3 text-base" >{despesa.descricao}</td>
                  <td className="px-6 py-3 text-base" >{despesa.valor}</td>
                  <td className="px-6 py-3 text-base" >{despesa.data}</td>
                  <td className="px-6 py-3 text-base" >{despesa.fixo ? 'Sim' : 'Não'}</td>
                  <td className="px-6 py-3 text-base" >{despesa.categoria.nome}</td>

                </tr>
              ))}
            </tbody>
          </table>
        </div>
        </>
}


      </div>
    </div>

  )
}

export default relatorio;
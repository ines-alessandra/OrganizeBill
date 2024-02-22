"use client";
import { ArrowUturnLeftIcon } from "@heroicons/react/24/solid";
import Link from "next/link";

import { useEffect, useState } from "react";
import { useMutation } from "react-query";
import { createDespesa } from "@/pages/api/despesa";
import { getAllCategoria } from "@/pages/api/categoria";


const NovaDespesa = () => {

  const [despesa, setDespesa] = useState({
    descricao: "",
    data: "",
    valor: "",
    categoriaCod: "",
    despesaFixa: false,
  });

  const [categorias, setCategorias] = useState([]);

  useEffect(() => {
    const fetchCategorias = async () => {
      const usuario = JSON.parse(localStorage.getItem('usuario'));
      const response = await getAllCategoria(usuario.cpf);
      setCategorias(response);
    };

    fetchCategorias();
  }, []);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    if (name === "categoriaCod") {
      const categoriaSelecionada = categorias.find(categoria => categoria.codCategoria === Number(value));
      console.log(categoriaSelecionada);
      if(categoriaSelecionada) {
        setDespesa((prevDespesa) => ({
          ...prevDespesa,
          categoriaCod: categoriaSelecionada.codCategoria,
        }));
      }
    } else {
      setDespesa((prevDespesa) => ({
        ...prevDespesa,
        [name]: type === "checkbox" ? checked : value,
      }));
    }
  };
  const handleSubmit = async (e) => {
    // Supondo que a categoria seja selecionada pelo codCategoria e o usuário esteja armazenado em localStorage
    const usuario = JSON.parse(localStorage.getItem('usuario'));
    const categoriaCompleta = categorias.find(c => c.codCategoria === despesa.categoriaCod);
    const novaDespesa = {
      ...despesa,
      categoria: categoriaCompleta,
      usuario: usuario,
    };
    try {
      await createDespesa(novaDespesa);
      // Tratar sucesso da criação da despesa
      window.location.href = "/despesa";
    } catch (error) {
      console.error('Erro ao criar despesa:', error);
    }
  };

  return (
    <div className="flex flex-col h-screen max-w-7xl justify-center m-auto" >
      <div className="flex justify-between items-center w-full ">
        <Link href="/despesa">
          <button
            className="flex gap-2 focus:outline-none text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2" >
            <ArrowUturnLeftIcon className="h-4 w-4 text-white" />
            Voltar
          </button>
        </Link>
      </div>

      <section className="bg-white white:bg-white-900">
        <div className="py-8 px-4 mx-auto max-w-2xl lg:py-16">
          <h2 className="mb-4 text-xl font-bold text-blue-900 blue:text-blue">Adicionar nova despesa</h2>
          <form onSubmit={handleSubmit}>
            <div className="grid gap-4 sm:grid-cols-2 sm:gap-6">

              <div className="w-full">
                <label htmlFor="data" className="block mb-2 text-sm font-medium text-blue-900 white:text-black">Data</label>
                <input type="date" name="data" id="data" className="bg-blue-50 border border-blue-300 text-blue-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 blue:bg-blue-700 blue:border-blue-600 blue:placeholder-blue-400 blue:text-blue blue:focus:ring-primary-500 blue:focus:border-primary-500"
                  value={despesa.data}
                  onChange={handleChange}
                  placeholder="Selecione a data" required="" />
              </div>
              <div className="w-full">
                <label htmlFor="valor" className="block mb-2 text-sm font-medium text-blue-900 white:text-black">Valor</label>
                <input type="number" name="valor" id="valor" className="bg-blue-50 border border-blue-300 text-blue-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 blue:bg-blue-700 blue:border-blue-600 blue:placeholder-blue-400 blue:text-blue blue:focus:ring-primary-500 blue:focus:border-primary-500"
                  value={despesa.valor}
                  onChange={handleChange}
                  placeholder="$2999" required="" />
              </div>
              <div>
                <label htmlFor="categoriaCod" className="block mb-2 text-sm font-medium text-blue-900 white:text-black">Categoria</label>
                <select
                  id="categoriaCod"
                  name="categoriaCod"
                  className="bg-blue-50 border border-blue-300 text-blue-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 blue:bg-blue-700 blue:border-blue-600 blue:placeholder-blue-400 blue:text-blue blue:focus:ring-primary-500 blue:focus:border-primary-500"
                  value={despesa.categoriaCod}
                  onChange={handleChange}
                >
                  <option>Selecione uma categoria</option>
                  {categorias.map((categoria) => (
                    <option key={categoria.codCategoria} value={categoria.codCategoria}>
                      {categoria.nome}
                    </option>
                  ))}
                </select>
              </div>
              <div>
                <label htmlFor="despesaFixa" className="block mb-2 text-sm font-medium text-blue-900 white:text-black">Despesa Fixa?</label>
                <select id="despesaFixa" name="despesaFixa" className="bg-blue-50 border border-blue-300 text-blue-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 blue:bg-blue-700 blue:border-blue-600 blue:placeholder-blue-400 blue:text-blue blue:focus:ring-primary-500 blue:focus:border-primary-500"
                  value={despesa.despesaFixa}
                  onChange={handleChange}
                >
                  <option >Selececione</option>
                  <option value="true">Sim</option>
                  <option value="false">Não</option>

                </select>
              </div>
              <div className="sm:col-span-2">
                <label htmlFor="descricao" className="block mb-2 text-sm font-medium text-blue-900 white:text-black">Description</label>
                <textarea id="descricao" name="descricao" rows="8" className="bg-blue-50 border border-blue-300 text-blue-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 blue:bg-blue-700 blue:border-blue-600 blue:placeholder-blue-400 blue:text-blue blue:focus:ring-primary-500 blue:focus:border-primary-500"
                  value={despesa.descricao}
                  onChange={handleChange}
                  placeholder="Descrição da despesa"></textarea>
              </div>
            </div>
            <button
              type="submit" className="bg-blue-50 border border-blue-300 text-blue-900 inline-flex items-center px-5 py-2.5 mt-4 sm:mt-6 text-sm font-medium text-center text-blue bg-primary-700 rounded-lg focus:ring-4 focus:ring-primary-700 blue:focus:ring-primary-900 hover:bg-primary-800">
              Adicionar despesa
            </button>
          </form>
        </div>
      </section>
    </div>
  )
}

export default NovaDespesa;
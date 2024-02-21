"use client";
import { ArrowUturnLeftIcon } from "@heroicons/react/24/solid";
import Link from "next/link";

import { useEffect, useState } from "react";
import { useMutation } from "react-query";
import { createcategoria } from "@/pages/api/categoria";
import { createCategoria, getAllCategoria } from "@/pages/api/categoria";


const NovaCategoria = () => {

  const [categoria, setCategoria] = useState({
    nome: "",
    descricao: "",
    gastoMedio: "",
    categoriaCod: "",
  });

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;

    setCategoria((prevCategoria) => ({
      ...prevCategoria,
      [name]: type === "checkbox" ? checked : value,
    }));
  };
  const handleSubmit = async (e) => {
    // Supondo que a categoria seja selecionada pelo codCategoria e o usuário esteja armazenado em localStorage
    const usuario = JSON.parse(localStorage.getItem('usuario'));
    const novaCategoria = {
      ...categoria,
      usuario: usuario,
    };
    try {
      await createCategoria(novaCategoria);
      // Tratar sucesso da criação da categoria
      window.location.href = "/categoria";
    } catch (error) {
      console.error('Erro ao criar categoria:', error);
    }
  };

  return (
    <div className="flex flex-col h-screen max-w-7xl justify-center m-auto" >
      <div className="flex justify-between items-center w-full ">
        <Link href="/categoria">
          <button
            className="flex gap-2 focus:outline-none text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2" >
            <ArrowUturnLeftIcon className="h-4 w-4 text-white" />
            Voltar
          </button>
        </Link>
      </div>

      <section className="bg-white white:bg-white-900">
        <div className="py-8 px-4 mx-auto max-w-2xl lg:py-16">
          <h2 className="mb-4 text-xl font-bold text-blue-900 blue:text-blue">Adicionar nova categoria</h2>
          <form onSubmit={handleSubmit}>
            <div className="grid gap-4 sm:grid-cols-2 sm:gap-6">

              <div className="w-full">
                <label htmlFor="nome" className="block mb-2 text-sm font-medium text-blue-900 white:text-black">Nome da Categoria</label>
                <input type="text" name="nome" id="nome" className="bg-blue-50 border border-blue-300 text-blue-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 blue:bg-blue-700 blue:border-blue-600 blue:placeholder-blue-400 blue:text-blue blue:focus:ring-primary-500 blue:focus:border-primary-500"
                  value={categoria.nome}
                  onChange={handleChange}
                  placeholder="Selecione a nome" required="" />
              </div>
              <div className="w-full">
                <label htmlFor="gastoMedio" className="block mb-2 text-sm font-medium text-blue-900 white:text-black">Gasto Medio</label>
                <input type="number" name="gastoMedio" id="gastoMedio" className="bg-blue-50 border border-blue-300 text-blue-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 blue:bg-blue-700 blue:border-blue-600 blue:placeholder-blue-400 blue:text-blue blue:focus:ring-primary-500 blue:focus:border-primary-500"
                  value={categoria.gastoMedio}
                  onChange={handleChange}
                  placeholder="$2999" required="" />
              </div>

              <div className="sm:col-span-2">
                <label htmlFor="descricao" className="block mb-2 text-sm font-medium text-blue-900 white:text-black">Descrição</label>
                <textarea id="descricao" name="descricao" rows="8" className="bg-blue-50 border border-blue-300 text-blue-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 blue:bg-blue-700 blue:border-blue-600 blue:placeholder-blue-400 blue:text-blue blue:focus:ring-primary-500 blue:focus:border-primary-500"
                  value={categoria.descricao}
                  onChange={handleChange}
                  placeholder="Descrição da categoria"></textarea>
              </div>
            </div>
            <button
              type="submit" className="bg-blue-50 border border-blue-300 text-blue-900 inline-flex items-center px-5 py-2.5 mt-4 sm:mt-6 text-sm font-medium text-center text-blue bg-primary-700 rounded-lg focus:ring-4 focus:ring-primary-700 blue:focus:ring-primary-900 hover:bg-primary-800">
              Adicionar categoria
            </button>
          </form>
        </div>
      </section>
    </div>
  )
}

export default NovaCategoria;
// 'editarPerfil.js'
import React, { useState, useEffect } from 'react';
import Link from 'next/link';
import { ArrowUturnLeftIcon } from "@heroicons/react/24/solid";
import { updateUsuario, getUsuarioByCpf } from "@/pages/api/usuario";
import { useRouter } from 'next/router';

const EditarPerfil = () => {
  const router = useRouter();
  const [user, setUser] = useState(null);
  const [editUsuario, setEditUsuario] = useState({
    nome: '',
    email: '',
    senha: '',
  });

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem('usuario'));
    console.log("Usuário teste:", storedUser);
    setUser(storedUser);

    if (storedUser) {
      setEditUsuario({
        nome: storedUser.nome,
        email: storedUser.email,
        senha: '',
      });
    }
  }, []);

  const handleInputChange = (e) => {
    setEditUsuario({
      ...editUsuario,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    try {
      await updateUsuario(user.cpf, {
        nome: editUsuario.nome,
        email: editUsuario.email,
        senha: editUsuario.senha,
        dataRegistro: editUsuario.dataRegistro,
      });

      const updatedUser = { ...user, ...editUsuario };
      localStorage.setItem('usuario', JSON.stringify(updatedUser));

      router.push(`/perfil`);
    } catch (error) {
      console.error('Erro ao atualizar usuário:', error);
    }
  };

  return (
    <div className="flex flex-col h-screen max-w-7xl justify-center m-auto">
      <div className="flex justify-between items-center w-full">
        <Link href="/perfil">
          <button
            className="flex gap-2 mr-auto focus:outline-none text-white bg-blue-700 hover:bg-blue-800 focus:ring-1 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2"
          >
            <ArrowUturnLeftIcon className="h-4 w-4 text-white" />
            Voltar
          </button>
        </Link>
      </div>

      <div className='w-2/4 border mt-6 px-10 py-10 rounded-2xl mx-auto'>
        {user ? (
          <form onSubmit={handleSubmit}>
            <div className='mb-4'>
              <h1 className='mx-auto text-2xl text-center font-black'>Editar Perfil</h1>

              <label 
                className="block mb-2 text-sm font-medium text-blue-900 white:text-black"
                htmlFor='nome'>Nome</label>
              <input
                type='text'
                name='nome'
                id='nome'
                value={editUsuario.nome}
                onChange={handleInputChange}
                className="bg-blue-50 border border-blue-300 text-blue-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 blue:bg-blue-700 blue:border-blue-600 blue:placeholder-blue-400 blue:text-blue blue:focus:ring-primary-500 blue:focus:border-primary-500"
              />
            </div>

            <div className='mb-4'>
              <label 
                className='block text-sm font-medium text-gray-700'
                htmlFor='email'>Email</label>
              <input
                type='text'
                name='email'
                value={editUsuario.email}
                onChange={handleInputChange}
                className='mt-1 p-2 border rounded-md w-full'
              />
            </div>

            <button
              type='submit'
              className="w-full bg-blue-700 hover:bg-blue-800 text-white  py-2 px-4 rounded-lg"
            >
              Salvar
            </button>
          </form>
        ) : (
          ''
        )}



      </div>
    </div>
  );
};

export default EditarPerfil;

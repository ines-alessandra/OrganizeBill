'use client'
import React from 'react'
import { useState, useEffect } from 'react';
import Link from 'next/link';
import { UserCircleIcon, ArrowUturnLeftIcon } from "@heroicons/react/24/solid";


const perfilUsuario = () => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem('usuario'));
    console.log("Usuário teste:", storedUser);
    setUser(storedUser);

  }, []);

  const formatarCPF = (cpf) => {
    return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
  };

  return (
    <div className="flex flex-col h-screen max-w-7xl justify-center m-auto">
      <div className="flex justify-between items-center w-full ">
        <div className='w-full flex justify-between'>
          <Link href="/">
            <button
              className="flex gap-2 mr-auto focus:outline-none text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2" >
              <ArrowUturnLeftIcon className="h-4 w-4 text-white" />
              Voltar
            </button>
          </Link>
          <h1 className='mx-auto text-2xl font-black' >Meu perfil</h1>
        </div>

        </div>


      <div className='w-full border mt-6 px-10 py-10 rounded-2xl'>
        {user ? (
          <div className="flex flex-col gap-2">
            <div className='flex justify-between'>
              <div className='flex content-center	items-center gap-4'>
                <UserCircleIcon className="h-16 w-16 text-gray-300" />
                <p className='text-lg font-bold'>{user.nome}</p>
              </div>
              <Link href=''>
              <button 
                className="mt-4 text-gray-900 hover:text-white border border-gray-800 hover:bg-gray-900 focus:ring-1 focus:outline-none focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2 text-center me-2 mb-2 dark:border-gray-600 dark:text-gray-400 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-800">
                Editar perfil
              </button>
              </Link>
          </div>
            
          <div className='flex w-full mt-6'>
              <div className='w-2/4'>
                <p className='font-bold'>Nome</p>
                <p>{user.nome}</p>
              </div>
            
              <div className='w-2/4'>
                <p className='font-bold'>CPF</p>
                <p>{formatarCPF(user.cpf)}</p>
              </div>
          </div>
          
          <div className='flex w-full mt-6'>
              <div className='w-2/4'>
                <p className='font-bold'>Email</p>
                <p>{user.email}</p>
              </div>
            
              <div className='w-2/4'>
                <p className='font-bold'>Data de registro no sistema</p>
                <p>{formatarCPF(user.dataRegistro)}</p>
              </div>
          </div>
        </div>
        ) : (
          ''
        )}
        
      </div>
    </div>
  )
}

export default perfilUsuario
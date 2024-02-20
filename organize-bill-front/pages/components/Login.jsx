"use client"

import React from 'react'
import { loginUsuario } from '../api/usuario'; 
import { useState } from 'react';
import Link from 'next/link';

const Login = () => {
  const [novoUsuario, setNovoUsuario] = useState({
    email: '',
    senha: '',
  });

  const handleLoginSubmit = async (e) => {
    e.preventDefault();
    console.log('Tentando fazer login:', novoUsuario)
    try {
      const respostaLogin = await loginUsuario(novoUsuario.email, novoUsuario.senha);
      console.log('Resposta do login:', respostaLogin);
      localStorage.setItem('usuario', JSON.stringify(respostaLogin));
      window.location.href = '/usuario';

    } catch (error) {
      console.error('Erro ao fazer login:', error);
    }
  };

  return (
    <div className='w-2/4 border px-10 py-10 rounded-2xl'>
        <form onSubmit={handleLoginSubmit}>
          <h1 className='text-2xl font-black text-center mb-10'>Login</h1>

          <div>
            <label 
              className='block mb-1 text-gray-900'
              htmlFor="email">Email:
            </label>
            <input
              id='email'
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500 block w-full p-1.5  "
              type="email"
              value={novoUsuario.email}
              onChange={(e) => setNovoUsuario(prevState => ({ ...prevState, email: e.target.value }))}
            />
          </div>

          <div>
            <label 
              className='block mb-1 text-gray-900 mt-2'
              htmlFor="senha">Senha:
            </label>
            <input
              id='senha'
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500 block w-full p-1.5 "
              type="password"
              value={novoUsuario.senha}
              onChange={(e) => setNovoUsuario(prevState => ({ ...prevState, senha: e.target.value }))}
            />
          </div>

          <div className='flex flex-col gap-4 justify-items-center'>
            <button
              className='text-white bg-blue-700	border border-gray-300 focus:outline-none hover:bg-blue-800 focus:ring-4 focus:ring-gray-100 font-medium rounded-md text-sm px-5 py-2 me-2 mb-2 mt-10 w-full ' 
              type="submit" 
              onClick={handleLoginSubmit}>
                Login
            </button>
            <Link 
              className='text-center	text-blue-700 font-bold	hover:text-blue-800'
              href="/usuario/novo">
              <button type="submit">Cadastrar-se</button>
            </Link>
          </div>
        </form>
    </div>
  );
};

export default Login;
"use client"

import React from 'react'
import { loginUsuario } from '../api/usuario'; 
import { useState } from 'react';
import Link from 'next/link';

const Login = () => {
  const [novoUsuario, setNovoUsuario] = useState({
    nome: '',
    senha: '',
  });

  const handleLoginSubmit = async (e) => {
    e.preventDefault();

    try {
      const respostaLogin = await loginUsuario(novoUsuario.email, novoUsuario.senha);
      console.log('Resposta do login:', respostaLogin);

    } catch (error) {
      console.error('Erro ao fazer login:', error);
    }
  };

  return (
    <div>
        <form className='flex flex-col gap-3' onSubmit={handleLoginSubmit}>
          <label>
            Email:
            <input
              type="email"
              value={novoUsuario.email}
              onChange={(e) => setNovoUsuario({ ...novoUsuario, email: e.target.value })}
            />
          </label>
          <label>
            Senha:
            <input
              type="password"
              value={novoUsuario.senha}
              onChange={(e) => setNovoUsuario({ ...novoUsuario, senha: e.target.value })}
            />
          </label>

          <div className='flex gap-4'>
            <button type="submit">Login</button>
            <Link href="/usuario/novo">
              <button type="submit">Cadastrar-se</button>
            </Link>
          </div>
        </form>
    </div>
  );
};

export default Login;
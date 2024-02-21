'use client'
import React from "react";
import Link from "next/link";
import { BuildingLibraryIcon, UserCircleIcon } from '@heroicons/react/24/solid'
import { useState, useEffect } from "react";

const Navbar = () => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem('usuario'));
    console.log("Usuário teste:", storedUser);
    setUser(storedUser);
  }, []);

  return (
    <>
      <div className="w-full box-border	h-20 bg-blue-800 sticky top-0 ">
        <div className="container max-w-7xl m-auto h-full box-content">
          <div className="flex justify-between items-center h-full">
            {/* <Logo /> */}
            <Link href="/">
              <BuildingLibraryIcon className="h-6 w-6 text-white" />
            </Link>
            <ul className="hidden md:flex gap-x-6 text-white">
              <li>
                <Link href="/usuario">
                  <p>Usuários</p>
                </Link>
              </li>
              <li>
                <Link href="/despesa">
                  <p>Despesas</p>
                </Link>
              </li>
              <li>
                <Link href="/categoria">
                  <p>Categoria</p>
                </Link>
              </li>
              <li>
                <Link href="/objetivo">
                  <p>Objetivos</p>
                </Link>
              </li>
              <li>
                <Link href="/receita">
                  <p>Receitas</p>
                </Link>
              </li>
            </ul>
            <div>
              {user ? (
                <div className="flex gap-2">
                  <UserCircleIcon className="h-6 w-6 text-white" />
                  <p className="text-white" >Olá, {user.nome}!</p>
                  {/* <button>Sair</button> */}
                </div>
              ) : (
                <p>Faça login para acessar.</p>
              )}
            </div>
            {/* <Button /> */}
          </div>
        </div>
      </div>
    </>
  );
};

export default Navbar;
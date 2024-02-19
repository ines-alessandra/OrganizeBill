import React from "react";
import Link from "next/link";
import { BuildingLibraryIcon } from '@heroicons/react/24/solid'

const Navbar = () => {
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
            {/* <Button /> */}
          </div>
        </div>
      </div>
    </>
  );
};

export default Navbar;
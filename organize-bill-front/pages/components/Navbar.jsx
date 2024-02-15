import React from "react";
import Link from "next/link";
import { BuildingLibraryIcon } from '@heroicons/react/24/solid'

const Navbar = () => {
  return (
    <>
      <div className="w-full h-20 bg-neutral-700 sticky top-0">
        <div className="container mx-auto px-4 h-full">
          <div className="flex justify-between items-center h-full">
            {/* <Logo /> */}
            <BuildingLibraryIcon className="h-6 w-6 text-white" />
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
                  <p>Objetivo</p>
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
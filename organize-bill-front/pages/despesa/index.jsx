import { useRouter } from "next/router";
import { getAllCategoria, createCategoria } from "../api/categoria.js";
import {  getDespesaByCategoria, getDespesaByData, createDespesa } from "../api/despesa";
import { useEffect, useState } from "react";
import Link from "next/link";
import Categorias from "../components/Categorias/index.jsx";

const despesa = () => {
    return (
      <div>
        <Categorias />
      </div>
    )
  }
  
  export default despesa;
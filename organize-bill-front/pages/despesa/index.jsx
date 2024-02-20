import { useRouter } from "next/router";
import { getAllCategoria, createCategoria } from "../api/categoria.js";
import {  getDespesaByCategoria, getDespesaByData, createDespesa } from "../api/despesa";
import { useEffect, useState } from "react";
import Link from "next/link";
import Despesas from "../components/Despesas/index.jsx";
const despesa = () => {
    return (
      <div>
        <Despesas />
      </div>
    )
  }
  
  export default despesa;
import { useRouter } from "next/router";
import { getAllCategoria, createCategoria } from "../api/categoria.js";
import {  getDespesaByCategoria, getDespesaByData, createDespesa } from "../api/despesa";
import { useEffect, useState } from "react";
import style from "./Despesa.module.css"
import Link from "next/link";

const despesa = () => {
    return (
      <div>
        <h1>Despesa</h1>
      </div>
    )
  }
  
  export default despesa;
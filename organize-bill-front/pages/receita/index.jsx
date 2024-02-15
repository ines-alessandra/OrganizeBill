import { useRouter } from "next/router";
import { createReceita, getAllReceitabyData, getTotalReceitabyData, addValueObjetivo } from "../api/receita";
import { useEffect, useState } from "react";
import style from "./Receita.module.css"
import Link from "next/link";

const receita = () => {
  return (
    <div>
      <h1>Receita</h1>
    </div>
  )
}

export default receita;
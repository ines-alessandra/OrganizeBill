import { useRouter } from "next/router";
import { createObjetivo, getAllObjetivo, addValueObjetivo } from "../api/objetivo";
import { useEffect, useState } from "react";
import style from "./Objetivo.module.css"
import Link from "next/link";

const objetivo = () => {
    return (
      <div>
        <h1>Objetivo</h1>
      </div>
    )
  }
  
  export default objetivo;
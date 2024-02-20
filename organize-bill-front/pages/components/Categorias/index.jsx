import { getAllCategoria } from "@/pages/api/categoria";
import { useEffect, useState } from "react";
import { useMutation } from "react-query";

import style from './categorias.module.scss';
import Image from "next/image";
import Link from "next/link";
const Categorias = () => {
  const [categorias, setCategorias] = useState([]);
  const [click, setCLick] = useState(false);
 
 
  useEffect(() => {
    mutate();
  }, [])

  const { state, mutate } = useMutation(
    async () => {
      return getAllCategoria("11571988733");
    }, {
    onSuccess: (data) => {
      console.log(data);
      setCategorias(data);
    },
    onError: (error) => {
      console.log(error)
    }
  }
  );

  return (
    <div className={style.container}>
      <div className={style.container__header}>
        <div>
          <h1 className={style.container__header_title}>
            Despesas
          </h1>
        </div>
        <div>
          <button className={style.container__header_button}>
            <h1>
              Adicionar Categoria
            </h1>
          </button>
        </div>
      </div>
      <div className={style.container__content}>
        {categorias.map((categoria, index) => {
          return (
            <div key={index}>
              <div className={style.container__content}>
                <div className={style.container__content_body}>
                  <div className={style.container__content_body_icon}>
                    <span>$</span>
                  </div>
                  <div className={style.container__content_body_text}>
                    <h1>{categoria.nome}</h1>
                    <p>{categoria.descricao}</p>
                    <h1>{categoria.gastoMedio}</h1>
                  </div>
                  <button>
                    <Image src="/assets/arrow_front.svg" alt="Visualizar" width={27} height={26} />
                  </button>
                </div>
              </div>
            </div>
          )
        }
        )
        }

      </div>
    </div>
  )
}

export default Categorias;
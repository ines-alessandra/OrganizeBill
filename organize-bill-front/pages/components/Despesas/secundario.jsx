import { getAllCategoria } from "@/pages/api/categoria";
import { useEffect, useState } from "react";
import { useMutation } from "react-query";

import style from './despesas.module.scss';
import Image from "next/image";
import Link from "next/link";
import { deleteDespesa, getDespesaByData } from "@/pages/api/despesa";


const Despesas = () => {
  const [despesas, setDespesas] = useState([]);
  const [filtroSelecionado, setFiltroSelecionado] = useState('mes');
  const [fixo, setFixo] = useState(false);

  useEffect(() => {
    // A função `mutate` é chamada sem argumentos aqui
    mutate();
    // Adicione `filtroSelecionado` e `fixo` como dependências
  }, [filtroSelecionado, fixo]);

  const { state, mutate } = useMutation(
    async () => {
      const usuario = JSON.parse(localStorage.getItem('usuario'));
      return getDespesaByData(usuario.cpf, filtroSelecionado, fixo);
    }, {
    onSuccess: (data) => {
      console.log(data);
      setDespesas(data);
    },
    onError: (error) => {
      console.log(error)
    }
  }
  );
  console.log(filtroSelecionado, fixo)
  return (
    <>
      <div className={style.containerHeader}>
        <div className={style.containerHeader__header}>
          <div>
            <h1 className={style.containerHeader__header_title}>
              Despesas
            </h1>
          </div>
          <div>
            <button className={style.containerHeader__header_button}>
              <h1>
                Adicionar Despesa
              </h1>
            </button>
          </div>
        </div>
        <div className={style.containerHeader__header_filter}>
          <div>
            <h1>Filtrar por:</h1>
          </div>
          <div>
            <div>
              <select value={filtroSelecionado} onChange={(e) => setFiltroSelecionado(e.target.value)}>
                <option value="">Filtro data</option>
                <option value="dia">Dia</option>
                <option value="semana">Semanal</option>
                <option value="mes">Mensal</option>
                <option value="ano">Anual</option>
              </select>
            </div>
            <div>
              <input type="checkbox" value={fixo} onChange={(e) => setFixo(e.target.checked)} />
              <label>Despesas Fixas</label>
            </div>
          </div>
        </div>
      </div>
      <div className={style.container}>

        {despesas.map((despesa, index) => {
          return (
            <div key={index}>
              <div className={style.container__content}>
                <div className={style.container__content_body}>
                  <div className={style.container__content_body_icon}>
                    <span>$</span>
                  </div>
                  <div className={style.container__content_body_text}>
                    <h2>nomeCategoria</h2>
                    <div>
                      <label>Descrição:  </label>
                      <span>{despesa.descricao}</span>
                    </div>
                    <div>
                      <label>Fixo: </label>
                      <span>{despesa.fixo ? 'Sim' : 'Não'}</span>
                    </div>
                    <div>
                      <label>Data: </label>
                      <span>{despesa.data}</span>
                    </div>
                    <div>
                      <label>Valor: </label>
                      <span>R$ {despesa.valor}</span>
                    </div>
                  </div>
                  <div className={style.container__content_body_button}>
                    <button>
                      <Image src="/assets/pencil.svg" alt="editar" width={27} height={26} />
                    </button>
                    <button>
                      <Image src="/assets/delete.svg" alt="deletar" width={27} height={26} />
                    </button>
                  </div>
                </div>
              </div>
            </div>
          )
        })}

      </div>
    </>
  )
}

export default Despesas;
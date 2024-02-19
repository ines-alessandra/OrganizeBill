import { useRouter } from "next/router";
import { getAllUsuario, createUsuario } from "../api/usuario";
import { useEffect, useState } from "react";
import style from "./Usuario.module.css"
import Link from "next/link";

const usuario = () => {
  const [usuarios, setUsuarios] = useState([]);
  const [novoUsuario, setNovoUsuario] = useState({
    nome: '',
    email: '',
    senha: '',
    cpf: '',
    dataEntrada: '',
  });

  useEffect(() => {
    loadUsuarios();
  }, []);

  const loadUsuarios = async () => {
    try {
      const usuariosData = await getAllUsuario();
      console.log('Usuarios Data:', usuariosData);
      setUsuarios(Array.isArray(usuariosData) ? usuariosData : []);
    } catch (error) {
      console.error('Erro ao carregar usuários:', error);
    }
  };

  const handleCreateUsuario = async () => {
    try {
      await createUsuario(novoUsuario);
      setUsuarios([]);
      setNovoUsuario({
        nome: '',
        email: '',
        senha: '',
        cpf: '',
        dataEntrada: '',
      });
      loadUsuarios();
    } catch (error) {
      console.error('Erro ao criar usuário:', error);
    }
  };

  return (
    <div className="flex flex-col h-screen justify-center items-center">
      <div className="flex  gap-11">
        <h1 className="text-2xl font-black" >Lista de Usuários</h1>
        <Link href="/usuario/novo">
          <button className="focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800" >Criar Novo Usuário</button>
        </Link>
      </div>

      <ul className="flex flex-col gap-4">
        {usuarios !== undefined ? (
          usuarios.map((usuario) => (
            <li key={usuario.cpf}>
              <p>CPF: {usuario.cpf}</p>
              <p>Nome: {usuario.nome}</p>
              <p>Email: {usuario.email}</p>
            </li>
          ))
        ) : (
          <p>Não existem usuários cadastrados.</p>
        )}
      </ul>
    </div>
  );
};

export default usuario;

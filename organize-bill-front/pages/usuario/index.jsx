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
    <div>
      <h1 className="text-red-500" >Lista de Usuários</h1>

      <Link href="/usuario/novo">
        <button>Criar Novo Usuário</button>
      </Link>

      <ul>
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

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

  const formatarCPF = (cpf) => {
    return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
  };

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
    <div className="flex flex-col h-screen max-w-7xl justify-center m-auto ">
      <div className="flex justify-between items-center w-full">
        <h1 className="text-2xl font-black" >Lista de usuários</h1>
        <Link href="/usuario/novo">
          <button 
            className="focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2" >
              Criar novo usuário
          </button>
        </Link>
      </div>

      <div className="relative overflow-x-auto shadow-md sm:rounded-lg w-full mt-6">
        <table className="w-full text-base text-left rtl:text-right text-gray-800 ">
          <thead className="text-base text-white uppercase bg-blue-800">
            <tr>
              <th className="px-6 py-3 text-base">CPF</th>
              <th className="px-6 py-3 text-base">Nome</th>
              <th className="px-6 py-3 text-base">Email</th>
            </tr>
          </thead>
          <tbody>
            {usuarios !== undefined ? (
              usuarios.map((usuario) => (
                <tr key={usuario.cpf}>
                  <td className="px-6 py-4 text-base">{formatarCPF(usuario.cpf)}</td>
                  <td className="px-6 py-4 text-base">{usuario.nome}</td>
                  <td className="px-6 py-4 text-base">{usuario.email}</td>
                </tr>
              ))
            ) : (
              <p>Não existem usuários cadastrados.</p>
            )}
          </tbody>
        </table>
      </div>

    </div>
  );
};

export default usuario;

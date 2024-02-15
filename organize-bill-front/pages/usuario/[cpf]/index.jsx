import { getUsuarioByCpf } from "../../api/usuario";


const UsuarioDetailPage = ({ usuario }) => {
  return (
    <div>
      <h1>Detalhes do Usuário</h1>
      <p>CPF: {usuario.cpf}</p>
      <p>Nome: {usuario.nome}</p>
      <p>Email: {usuario.email}</p>
      <p>Data entrada: {usuario.dataEntrada}</p>
      <p>Senha: {usuario.senha}</p>
    </div>
  );
};

export async function getServerSideProps({ params }) {
  const { cpf } = params;

  try {
    if (process.env.NODE_ENV === 'production') {
      const usuario = await getUsuarioByCpf(cpf);

      return {
        props: {
          usuario,
        },
      };
    }

    return {
      props: {
        usuario: {}, 
      },
    };
  } catch (error) {
    console.error('Erro ao obter dados do usuário:', error);

    return {
      props: {
        usuario: {}, 
      },
    };
  }
}

export default UsuarioDetailPage;

import Link from "next/link";
import Login from "@/pages/components/Login";

export default function Home() {
  return (
    <div className="flex h-screen place-items-center max-w-7xl m-auto gap-14" >
      <div className="w-2/4">
        <h1 className="text-2xl font-black mb-2" >OrganizeBill</h1>
        <p>O projeto de gerenciamento financeiro proposto visa oferecer um sistema que seja possível cadastrar, gerenciar, estabelecer metas financeiras. Este projeto foi desenvolvido como parte da avaliação parcial da disciplina de Banco de Dados</p>
      </div>
      <Login/>
    </div>
  );
}

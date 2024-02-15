import Link from "next/link";
import Login from "@/pages/components/Login";

export default function Home() {
  return (
    <div className="flex flex- h-screen justify-center items-center gap-10" >
      <h1 className="text-2xl font-black" >OrganizeBill</h1>
      <Login />
    </div>
  );
}

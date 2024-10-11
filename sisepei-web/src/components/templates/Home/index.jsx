import { NoticeList } from "../../organisms";

export function Home() {
  return (
    <div id="page1">
      <h1 className="welcome">Bem vindo!</h1>
      <hr className="myhr" />
      <NoticeList />
    </div>
  );
}

export default Home;

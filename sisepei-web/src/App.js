import "./App.css";
import Login from "./pages/Login/Login";
import Header from "./Components/Header/Header.jsx";
import { Route, Routes } from "react-router-dom";

function App() {
  return (
    <div className="app">
      <Header />
      
      <Routes>
        <Route path="/" element={<Login />} />
      </Routes>
    </div>
  );
}

export default App;

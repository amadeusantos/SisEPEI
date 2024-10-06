import "./App.css";
import Header from "./components/molecules/Header/Index";
import React from "react";
import Router from "./router";

function App() {
  return (
    <>
      <div className="app">
        <Header />
        <Router />
      </div>
    </>
  );
}

export default App;

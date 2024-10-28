import "./App.css";
import Header from "./components/molecules/Header/Index";
import React from "react";
import Router from "./router";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

const queryClient = new QueryClient();

function App() {
  return (
    <QueryClientProvider client={queryClient} >
      <div className="app">
        <Header />
        <Router />
      </div>
    </QueryClientProvider>
  );
}

export default App;

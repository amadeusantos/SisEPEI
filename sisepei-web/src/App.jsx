import React from "react";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

import "./App.css";
import Header from "./components/molecules/Header/Index";
import Router from "./router";

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

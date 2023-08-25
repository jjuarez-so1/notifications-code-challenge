import React from "react";
import Home from "./pages/home/Home"; 
import "./app.scss";

import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import NewBroadcast from "./pages/new-broadcast/NewBroadcast";
import Sidebar from "./components/sidebar/Sidebar";
import Navbar from "./components/navbar/Navbar";
import Logs from "./pages/logs/Logs";

function App() {
 return (
  <div className="App">
    <BrowserRouter>
      <Sidebar />
      <div className="AppContainer">
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/notifications/new" element={<NewBroadcast />} />
          <Route path="/messages" element={<Logs />} />
        </Routes>
      </div>
    </BrowserRouter>
  </div>
 );
}

export default App;

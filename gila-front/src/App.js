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
        </Routes>
      </div>
    </BrowserRouter>
  </div>
 );
}

export default App;

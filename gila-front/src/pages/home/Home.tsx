import React from "react";
import "./home.scss";

import KpiCards from "../../components/kpi-cards/KpiCards"

const Home = () => {
  return (
    <div className="home">
        <div className="homeContainer">
          <KpiCards />
        </div>
    </div>
  )
}

export default Home
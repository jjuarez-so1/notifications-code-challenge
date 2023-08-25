import React, { useState } from "react";
import "./home.scss";

import KpiCards from "../../components/kpi-cards/KpiCards"
import Tooltip from '@mui/material/Tooltip';
import HelpOutlineIcon from '@mui/icons-material/HelpOutline';
import WarningAmberOutlinedIcon from '@mui/icons-material/WarningAmberOutlined';

const Home = () => {
  const [inProgress, setInProgress] = useState(false);

  const handleProgressChange = (progressStatus: boolean) => {
    setInProgress(progressStatus);
  }

  return (
    <div className="home">
      <div className="title-container">
        <h1 className="pageTitle">Dashboard</h1>
        <Tooltip title="This data correspond to the last broadcast sent" placement="bottom">
          <HelpOutlineIcon className="tooltip-icon" />
        </Tooltip>
        {inProgress && (
          <Tooltip title="Broadcast in progress" placement="right">
            <WarningAmberOutlinedIcon className="tooltip-icon" />
          </Tooltip>
        )}
      </div>
      <div className="homeContainer">
        <KpiCards onProgressChange={handleProgressChange}/>
      </div>
    </div>
  );
};
export default Home;
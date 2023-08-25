import React from "react";
import "./sidebar.scss";

import SpaceDashboardOutlinedIcon from '@mui/icons-material/SpaceDashboardOutlined';
import LayersOutlinedIcon from '@mui/icons-material/LayersOutlined';
import LogoDevOutlinedIcon from '@mui/icons-material/LogoDevOutlined';
import CellTowerOutlinedIcon from '@mui/icons-material/CellTowerOutlined';
import StarHalfOutlinedIcon from '@mui/icons-material/StarHalfOutlined';
import DoNotTouchOutlinedIcon from '@mui/icons-material/DoNotTouchOutlined';
import LocalTaxiOutlinedIcon from '@mui/icons-material/LocalTaxiOutlined';
import ShareOutlinedIcon from '@mui/icons-material/ShareOutlined';
import FingerprintOutlinedIcon from '@mui/icons-material/FingerprintOutlined';
import EmojiEmotionsOutlinedIcon from '@mui/icons-material/EmojiEmotionsOutlined';
import CalendarViewMonthOutlinedIcon from '@mui/icons-material/CalendarViewMonthOutlined';

import { Link, useLocation } from "react-router-dom";

const Sidebar = () => {
    const location = useLocation();

    return (
        <div className="sidebar">
            <div className="top">
                <span className="logo">jjuarezitc</span>
            </div>
            <hr />
            <div className="center">
                <ul>
                    <p className="title">MAIN</p>
                    <Link to="/">
                        <li className={location.pathname === '/' ? 'active' : ''}>
                            <SpaceDashboardOutlinedIcon className="icon" />
                            <span>Dashboard</span>
                        </li>
                    </Link>

                    <p className="title">BROADCAST</p>
                    <Link to="/notifications/new">
                        <li className={location.pathname === '/notifications/new' ? 'active' : ''}>
                        <CellTowerOutlinedIcon className="icon" />
                            <span>New Broadcast</span>
                        </li>
                    </Link>

                    <p className="title">LOGS</p>

                    <Link to="/messages">
                        <li className={location.pathname === '/messages' ? 'active' : ''}>
                            <CalendarViewMonthOutlinedIcon className="icon" />
                            <span>Messages sent</span>
                        </li>
                    </Link>

                    <p className="title">USEFUL LINKS</p>
                    <li>
                        <LayersOutlinedIcon className="icon" />
                        <span>Cras a tellus</span>
                    </li>

                    <li>
                        <CalendarViewMonthOutlinedIcon className="icon" />
                        <span>Vehicula aphemeral</span>
                    </li>

                    <li>
                        <FingerprintOutlinedIcon className="icon" />
                        <span>Eget aiquam</span>
                    </li>

                    <li>
                        <EmojiEmotionsOutlinedIcon className="icon" />
                        <span>Suspendisse Aplomb</span>
                    </li>

                    <li>
                        <ShareOutlinedIcon className="icon" />
                        <span>Varius morbi</span>
                    </li>

                    <li>
                        <LocalTaxiOutlinedIcon className="icon" />
                        <span>Sed quis mi</span>
                    </li>

                    <li>
                        <LogoDevOutlinedIcon className="icon" />
                        <span>Pulvinar nimi</span>
                    </li>

                    <li>
                        <StarHalfOutlinedIcon className="icon" />
                        <span>Nulla faciloisi</span>
                    </li>

                    <li>
                        <DoNotTouchOutlinedIcon className="icon" />
                        <span>Rhoncus effici</span>
                    </li>
                </ul>
            </div>
        </div>
    )
}

export default Sidebar
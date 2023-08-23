import "./kpi-cards.scss"

import { useEffect, useState } from "react";
import axios from "axios";
import Widget from "../widget/Widget"

const KpiCards = () => {
    const [kpis, setKpis] = useState(null);

    useEffect(() => {
        async function fetchKpis() {
            try {
                const response = await axios.get("http://localhost:8080/api/kpis");
                setKpis(response.data);
            } catch (error) {
                console.error("Error fetching KPIs:", error);
            }
        }

        fetchKpis();
    }, []);

    return (
        <div className="kpiCardsContainer">
            <div className="widgets">
                {kpis !== null && (
                    <>
                        {kpis.users !== null && <Widget type={kpis.users.title} quantity={kpis.users.quantity} />}
                        {kpis.smsNotifications !== null && <Widget type={kpis.smsNotifications.title} quantity={kpis.smsNotifications.quantity} />}
                        {kpis.emailNotifications !== null && <Widget type={kpis.emailNotifications.title} quantity={kpis.emailNotifications.quantity} />}
                        {kpis.pushNotifications !== null && <Widget type={kpis.pushNotifications.title} quantity={kpis.pushNotifications.quantity} />}
                    </>
                )}
                {kpis === null && <p>Loading KPIs...</p>}
                {kpis !== null && Object.values(kpis).every(val => val === null) && <p>No notifications sent yet.</p>}
            </div>
        </div>
    );
};

export default KpiCards;

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
                {kpis ? (
                    <>
                        <Widget type={kpis.users.title} quantity={kpis.users.quantity} />
                        <Widget type={kpis.smsNotifications.title} quantity={kpis.smsNotifications.quantity} />
                        <Widget type={kpis.emailNotifications.title} quantity={kpis.emailNotifications.quantity} />
                        <Widget type={kpis.pushNotifications.title} quantity={kpis.pushNotifications.quantity} />
                    </>
                ) : (
                    <p>Loading KPIs...</p>
                )}
            </div>
        </div>
    );
};

export default KpiCards;

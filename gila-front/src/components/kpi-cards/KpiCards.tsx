import React from "react";
import "./kpi-cards.scss"

import { useEffect, useState } from "react";
import Widget from "../widget/Widget"
import { myContainer } from "../../inversify/inversify.config";
import { TYPES } from "../../inversify/types";
import KpiData from "./KpiData";
import { KpiRepository } from "../../repository/KpiRepository";

const KpiCards = () => {
    const [kpis, setKpis] = useState<KpiData|null>(null);

    useEffect(() => {
        fetchKpis();
    }, []);

    async function fetchKpis() {
        try {
            const myRepo = myContainer.get<KpiRepository>(TYPES.KpiRepository);
            const kpiData = await myRepo.fetchKpis();
            setKpis(kpiData);
        } catch (error) {
            console.error("Error fetching KPIs:", error);
        }
    }

    const isLoading = kpis === null;
    const allNotificationsEmpty = kpis !== null && Object.values(kpis).every(val => val === null);

    return (
        <div className="kpiCardsContainer">
            <div className="widgets">
                {isLoading ? (
                    <p>Loading KPIs...</p>
                ) : allNotificationsEmpty ? (
                    <p>No notifications sent yet.</p>
                ) : (
                    <>
                        {kpis.users !== null && <Widget type={kpis.users.title} quantity={kpis.users.quantity} />}
                        {kpis.smsNotifications !== null && <Widget type={kpis.smsNotifications.title} quantity={kpis.smsNotifications.quantity} />}
                        {kpis.emailNotifications !== null && <Widget type={kpis.emailNotifications.title} quantity={kpis.emailNotifications.quantity} />}
                        {kpis.pushNotifications !== null && <Widget type={kpis.pushNotifications.title} quantity={kpis.pushNotifications.quantity} />}
                    </>
                )}
            </div>
        </div>
    );
};

export default KpiCards;

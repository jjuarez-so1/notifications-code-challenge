import { injectable, inject } from "inversify";
import "reflect-metadata";
import { NotificationsRepository } from "./interfaces";
import { TYPES } from "./types";
import { KpiRepository } from "../repository/KpiRepository";
import KpiData from "../components/kpi-cards/KpiData";
import axios from "axios";

@injectable()
class KpiRepositoryImpl implements KpiRepository {
    @inject(TYPES.ApiBaseUrl) private _apiUrl: string | any;

    async fetchKpis(): Promise<KpiData> {
        try {
            const response = await axios.get(`${this._apiUrl}/kpis`);
            return response.data;
        } catch (error) {
            throw new Error("Error fetching KPIs");
        }
    }
}

@injectable()
class NotificationsRepositoryImpl implements NotificationsRepository {
    @inject(TYPES.ApiBaseUrl) private _apiUrl: string | any;

    async sendNotification(topic: string, message: string): Promise<void> {
        console.log('using my super repo');
        try {
            const notificationData = {
                category: topic,
                message: message,
            };

            await axios.post(
                `${this._apiUrl}/notifications/send`,
                notificationData
            );
        } catch (error) {
            throw new Error("Error sending notification");
        }
    }
}

export { KpiRepositoryImpl, NotificationsRepositoryImpl };

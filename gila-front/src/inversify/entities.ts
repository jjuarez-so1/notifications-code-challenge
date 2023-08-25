import { injectable, inject } from "inversify";
import "reflect-metadata";
import { MessagesRepository, MessagesResponse, Notification, NotificationsRepository } from "./interfaces";
import { TYPES } from "./types";

import KpiData from "../dtos/KpiDataDTO";
import axios from "axios";
import { KpiRepository } from "../repositories/KpiRepository";

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

    async getLastNotifications(): Promise<Notification[]> {
      try {
        const response = await axios.get<Notification[]>(
          `${this._apiUrl}/notifications/last`
        );
        return response.data;
      } catch (error) {
        console.error("Error fetching last notifications", error);
        throw new Error("Error fetching last notifications");
      }
    }
}

@injectable()
class MessagesRepositoryImpl implements MessagesRepository {
  @inject(TYPES.ApiBaseUrl) private _apiUrl: string | any;
  async getMessagesByNotification(notificationId: number, page: number, pageSize: number): Promise<MessagesResponse> {
    try {
      const response = await fetch(
        `${this._apiUrl}/messages/by-notification?notificationId=${notificationId}&page=${page}&pageSize=${pageSize}`
      );

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const responseData: MessagesResponse = await response.json();
      return responseData;
    } catch (error) {
      console.error("Error fetching data", error);
      return {
        messages: [],
        totalCount: 0,
      };
    }
  }
}

export { KpiRepositoryImpl, NotificationsRepositoryImpl, MessagesRepositoryImpl };

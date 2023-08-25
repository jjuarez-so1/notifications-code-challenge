import KpiData from "../dtos/KpiDataDTO";

export interface NotificationsRepository {
    sendNotification(topic: string, message: string): Promise<void>;
    getLastNotifications(): Promise<Notification[]>;
}

export interface Notification {
  id: number;
  topic: string;
  message: string;
  startTime: string;
  endTime: string;
  status: string;
}


  export interface Message {
    channel: string;
    userEmail: string;
    userId: number;
    sentTime: string;
  }
  
  export interface MessagesResponse {
    messages: Message[];
    totalCount: number;
  }

export interface Category {
    value: string;
    label: string;
}

export interface KpiRepository {
    fetchKpis(): Promise<KpiData>;
}

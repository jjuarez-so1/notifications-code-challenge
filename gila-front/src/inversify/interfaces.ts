export interface NotificationsRepository {
    sendNotification(topic: string, message: string): Promise<void>;
}

export interface Category {
    value: string;
    label: string;
}

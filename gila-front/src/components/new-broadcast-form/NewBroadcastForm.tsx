import React from "react";
import "./new-broadcast-form.scss";

import { useState } from "react";
import { myContainer } from "../../inversify/inversify.config";
import { NotificationsRepository } from "../../inversify/interfaces";
import { TYPES } from "../../inversify/types";
import Category from "../../interfaces/Category";



const categories: Category[] = myContainer.get<Category[]>(TYPES.Categories);

interface NewBroadcastFormProps {
    onBroadcast: () => void;
    setNotifications: (notifications: any) => void;
    lastRefreshed: Date | null;
}

const NewBroadcastForm: React.FC<NewBroadcastFormProps> = ({
    onBroadcast,
    setNotifications,
    lastRefreshed,
}) => {
    const [selectedTopic, setSelectedTopic] = useState("SPORTS");
    const [message, setMessage] = useState("");
    const [isLoading, setIsLoading] = useState(false);



    const handleTopicChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setSelectedTopic(event.target.value);
    }

    const handleMessageChange: React.ChangeEventHandler<HTMLTextAreaElement> = (event) => {
        const newMessage = event.target.value;
        if (newMessage.length <= 60) {
            setMessage(newMessage);
        }
    }


    const handleSubmit: React.FormEventHandler<HTMLFormElement> = async (event) => {
        event.preventDefault();

        setIsLoading(true);

        try {
            const notificationsRepo = myContainer.get<NotificationsRepository>(TYPES.NotificationsRepository);
            await notificationsRepo.sendNotification(selectedTopic, message);

            setSelectedTopic(categories[0].value);
            setMessage("");
            setIsLoading(false);

            onBroadcast();
        } catch (error) {
            console.error("Error sending broadcast:", error);
            setIsLoading(false);
        }
    };

    return (
        <div className="newBroacastFormContainer">
            <h1>Send a new broadcast</h1>
            <form className="broadcast-form" onSubmit={handleSubmit}>
                <div className="row align-items-end">
                    <div className="col-md-2">
                        <label htmlFor="topic" className="labelForm">Topic:</label>
                        <select
                            id="topic"
                            className="form-select"
                            value={selectedTopic}
                            onChange={handleTopicChange}
                        >
                            {categories.map((category) => (
                                <option key={category.value} value={category.value}>
                                    {category.label}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className="col-md-8">
                        <label htmlFor="message" className="labelForm">Message:</label>
                        <textarea
                            id="message"
                            className="form-control"
                            value={message}
                            onChange={handleMessageChange}
                            rows={1}
                            maxLength={60}
                        />
                    </div>
                    <div className="col-md-2">
                        <button type="submit" className="btn btn-dark" style={{ width: "100%" }} disabled={isLoading || message.trim().length <= 3}>
                            {isLoading ? "Sending..." : "SEND"}
                        </button>
                    </div>
                </div>
            </form>
        </div>
    )
}

export default NewBroadcastForm

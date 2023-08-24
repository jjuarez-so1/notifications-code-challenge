import React from "react";
import "./new-broadcast.scss"

import { useEffect, useState } from "react";
import List from "../../components/list/List"
import axios from "axios";
import NewBroadcastForm from "../../components/new-broadcast-form/NewBroadcastForm";

interface Notification {
  id: number;
  topic: string;
  message: string;
  status: string;
  startTime: string;
  endTime: string;
}

const NewBroadcast = () => {
  const [notifications, setNotifications] = useState<Notification[]>([]);
  const [lastRefreshed, setLastRefreshed] = useState(new Date());

  useEffect(() => {
    fetchNotifications();
  }, []);

  const handleBroadcast = () => {
    fetchNotifications();
    setLastRefreshed(new Date());
  };

  const fetchNotifications = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/notifications/last');
      setNotifications(response.data);
      setLastRefreshed(new Date());
    } catch (error) {
      console.error('Error fetching notifications:', error);
    }
  };
  return (
    <div className="newBroadcast">
      <div className="newBroadcastContainer">
        <div className="formContainer">
          <NewBroadcastForm
            onBroadcast={handleBroadcast}
            setNotifications={setNotifications}
            lastRefreshed={lastRefreshed} />
        </div>
        <div className="tableContainer">
          <List
            notifications={notifications}
            lastRefreshed={lastRefreshed}
            setNotifications={setNotifications}
            onBroadcast={handleBroadcast}/>
        </div>
      </div>
    </div>
  );
}

export default NewBroadcast;

import "./new-broadcast.scss"

import { useEffect, useState } from "react";
import List from "../../components/list/List"
import axios from "axios";
import NewBroadcastForm from "../../components/new-broadcast-form/NewBroadcastForm";

const NewBroadcast = () => {
  const [notifications, setNotifications] = useState([]);
  const [lastRefreshed, setLastRefreshed] = useState(null);

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

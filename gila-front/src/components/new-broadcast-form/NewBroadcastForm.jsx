import "./new-broadcast-form.scss";

import { useState } from "react";
import axios from "axios";

const NewBroadcastForm = ({ onBroadcast, setNotifications, lastRefreshed }) => {
    const [selectedTopic, setSelectedTopic] = useState("SPORTS");
  const [message, setMessage] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  
  

  const handleTopicChange = (event) => {
    setSelectedTopic(event.target.value);
  }

  const handleMessageChange = (event) => {
    const newMessage = event.target.value;
    if (newMessage.length <= 110) {
      setMessage(newMessage);
    }
  }


  const myHandleSubmit = async (event) => {
    event.preventDefault();

    setIsLoading(true);

    const broadcastData = {
      category: selectedTopic,
      message: message,
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/api/notifications/send",
        broadcastData
      );
      setSelectedTopic("SPORTS");
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
            <form className="broadcast-form" onSubmit={myHandleSubmit}>
                <div className="row align-items-end">
                    <div className="col-md-2">
                        <label htmlFor="topic" className="labelForm">Topic:</label>
                        <select id="topic" className="form-select" value={selectedTopic} onChange={handleTopicChange}>
                            <option value="SPORTS">SPORTS</option>
                            <option value="FINANCE">FINANCE</option>
                            <option value="FILMS">FILMS</option>
                        </select>
                    </div>
                    <div className="col-md-8">
                        <label htmlFor="message" className="labelForm">Message:</label>
                        <textarea
                            id="message"
                            className="form-control"
                            value={message}
                            onChange={handleMessageChange}
                            rows="1"
                            maxLength="110"
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

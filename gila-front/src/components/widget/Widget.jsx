import "./widget.scss"
import PeopleOutlinedIcon from '@mui/icons-material/PeopleOutlined';
import VibrationOutlinedIcon from '@mui/icons-material/VibrationOutlined';
import ForwardToInboxOutlinedIcon from '@mui/icons-material/ForwardToInboxOutlined';
import NotificationsActiveOutlinedIcon from '@mui/icons-material/NotificationsActiveOutlined';

const Widget = ({ type, quantity }) => {
    let data;
    switch (type) {
        case "USERS":
            data = {
                title: "USERS",
                icon: <PeopleOutlinedIcon className="icon" style={{ color: "#404040" }}/>,
            };
            break;
            case "SMS_NOTIFICATIONS":
            data = {
                title: "SMS",
                icon: <VibrationOutlinedIcon className="icon" style={{ color: "#2b6a6c" }}/>,
            };
            break;
            case "EMAIL_NOTIFICATIONS":
            data = {
                title: "EMAIL",
                icon: <ForwardToInboxOutlinedIcon className="icon" style={{ color: "#f29724" }}/>,
            };
            break;
            case "PUSH_NOTIFICATIONS":
            data = {
                title: "PUSH NOTIFICATIONS",
                icon: <NotificationsActiveOutlinedIcon className="icon" style={{ color: "rgb(255, 102, 155)" }}/>,
            };
            break;
        default:
            break;
    }

    return (
        <div className="widgett">
            <div className="leftt">
                <span className="titlee">{ data.title }</span>
                <span className="counterr">{quantity}</span>
                <span className="linkk">See details</span>
            </div>
            <div className="rightt">
                { data.icon }
            </div>
        </div>
    )
}

export default Widget

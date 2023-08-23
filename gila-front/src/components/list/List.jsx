import "./list.scss"
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import AutorenewOutlinedIcon from "@mui/icons-material/AutorenewOutlined";

const List = ({ notifications, setNotifications, onBroadcast, lastRefreshed }) => {
  const handleRefresh = () => {
    onBroadcast();
  };

  return (
    <div className="listContainer">
      <h1>Latest broadcasts</h1>
      <div className="row last-refresh-container">
        <div className="col-md-12 text-end">
          {lastRefreshed && (
            <span className="last-refresh">
              Last Refresh: {lastRefreshed.toLocaleString()}
            </span>
          )}
          <AutorenewOutlinedIcon
            className="refresh-icon"
            onClick={handleRefresh}
          />
        </div>
      </div>
      <TableContainer component={Paper} className="my-table">
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell style={{ width: "10px" }}>ID</TableCell>
              <TableCell style={{ minWidth: 100 }}>CATEGORY</TableCell>
              <TableCell style={{ minWidth: 100 }}>MESSAGE</TableCell>
              <TableCell style={{ minWidth: 100 }}>STATUS</TableCell>
              <TableCell style={{ minWidth: 100 }}>START TIME</TableCell>
              <TableCell style={{ minWidth: 100 }}>END TIME</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {notifications.map((row) => (
              <TableRow
                key={row.id}
                sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
              >
                <TableCell component="th" scope="row">
                  {row.id}
                </TableCell>
                <TableCell>{row.topic}</TableCell>
                <TableCell>{row.message}</TableCell>
                <TableCell>{row.status}</TableCell>
                <TableCell>{row.startTime}</TableCell>
                <TableCell>{row.endTime}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}

export default List;
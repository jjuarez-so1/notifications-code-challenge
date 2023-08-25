import "./paginated-table.scss";

import React, { useState, useEffect } from "react";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import TablePagination from '@mui/material/TablePagination';
import { myContainer } from "../../inversify/inversify.config";
import { MessagesRepository, MessagesResponse, NotificationsRepository, Notification } from "../../inversify/interfaces";
import { TYPES } from "../../inversify/types";

const PaginatedTable: React.FC = () => {
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  const [data, setData] = useState<MessagesResponse|null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [totalCount, setTotalCount] = useState(0);
  const [selectedNotificationId, setSelectedNotificationId] = useState<number | null>(null);
  const [notifications, setNotifications] = useState<Notification[]>([]);

  const fetchNotifications = async (): Promise<void> => {
    try {
      const notificationsRepo = myContainer.get<NotificationsRepository>(TYPES.NotificationsRepository);
      const latestNotifications = await notificationsRepo.getLastNotifications();
      setNotifications(latestNotifications);
      setSelectedNotificationId(latestNotifications[0]?.id || null);
    } catch (error) {
      setError("Error fetching notifications");
    }
  };

  const fetchData = async (page: number, pageSize: number): Promise<void> => {
    if(selectedNotificationId === null) {
      return;
    }
    setLoading(true);
    try {
      const messagesRepo = myContainer.get<MessagesRepository>(TYPES.MessagesRepository);
      const response = await messagesRepo.getMessagesByNotification(selectedNotificationId, page + 1, rowsPerPage);

      setData(response);
      setTotalCount(response.totalCount);
      setError(null);
    } catch (error) {
      setError("Error fetching data");
    } finally {
      setLoading(false);
    }
  };

  const handlePageChange = (event: React.MouseEvent<HTMLButtonElement> | null, newPage: number) => {
    setPage(newPage);
  };

  const handleRowsPerPageChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleRefresh = async () => {
    await fetchData(page , rowsPerPage);
  };

  useEffect(() => {
    fetchNotifications();
  }, []);

  useEffect(() => {
    if (selectedNotificationId !== null) {
      handleRefresh();
    }
  }, [selectedNotificationId, page, rowsPerPage]);

  const handleSelectChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    console.log('notification select change');
    setSelectedNotificationId(parseInt(event.target.value));
    setPage(0);
  };

  return (
    <div className="listContainer">
      <div className="row last-refresh-container">
        <div className="col-md-5 text-end myFoo">
          <label className="labelForSelect">Notification: </label>
          <select id="select"
              className="form-select"
              onChange={handleSelectChange}
              value={selectedNotificationId || ""}
            >
            {notifications.map((notification) => (
              <option key={notification.id} value={notification.id}>
                ID: {notification.id}, MESSAGE: {notification.message.substr(0, 30)}...
              </option>
            ))}
          </select>
        </div>
      </div>
      <TableContainer component={Paper} className="my-table">
        <Table  aria-label="simple table">
          <TableHead>
            <TableRow>
            <TableCell>Sent time</TableCell>
              <TableCell>Channel</TableCell>
              <TableCell>User Id</TableCell>
              <TableCell>User Email</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {loading ? (
              <TableRow>
                <TableCell colSpan={4} align="center">
                  Loading...
                </TableCell>
              </TableRow>
            ) : (
              data?.messages.map((row, index) => (
                <TableRow key={index}>
                  <TableCell>{row.sentTime}</TableCell>
                <TableCell>{row.channel}</TableCell>
                <TableCell>{row.userId}</TableCell>
                <TableCell>{row.userEmail}</TableCell>
                </TableRow>
              ))
            )}
          </TableBody>
        </Table>
        <TablePagination
          rowsPerPageOptions={[10]}
          component="div"
          count={totalCount}
          rowsPerPage={rowsPerPage}
          page={page}
          onPageChange={handlePageChange}
          onRowsPerPageChange={handleRowsPerPageChange}
        />
      </TableContainer>
    </div>
  );
}

export default PaginatedTable;

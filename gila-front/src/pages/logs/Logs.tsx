import React from 'react'
import PaginatedTable from '../../components/paginated-table/PaginatedTable'

const Logs = () => {
  return (
    <div className='logs'>
      <h1 className="pageTitle">Messages sent</h1>
        <div className="logsContainer">
            <PaginatedTable />
        </div>
    </div>
  )
}

export default Logs
interface KpiData {
  inProgress: boolean;
  users: {
    title: string;
    quantity: number;
  };
  smsNotifications: {
    title: string;
    quantity: number;
  };
  emailNotifications: {
    title: string;
    quantity: number;
  };
  pushNotifications: {
    title: string;
    quantity: number;
  };
}

  export default KpiData;

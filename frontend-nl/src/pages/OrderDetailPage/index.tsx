import React, { useEffect } from "react";
import { useAuth } from "../../contexts/auth";
import { OrderManage } from "../../components/OrderManage/OrderManage";
import LayoutDefault from "../../components/Layout/LayoutDefault";

const OrderDetailPage = () => {
  const { isAdmin } = useAuth();

  return (
    <LayoutDefault>
      <OrderManage />
    </LayoutDefault>
  );
};

export default OrderDetailPage;

import { Button, Table, Tag } from "antd";
import React, { useEffect, useState } from "react";
import { Order, OrderStatus } from "../../types/order";
import { ColumnsType } from "antd/es/table";
import Action from "../Action";
import { useCategory } from "../../contexts/category";
import { CartService } from "../../service/api";
import OrderAction from "./Action";

const columns: ColumnsType<Order> = [
  {
    title: "Tên",
    dataIndex: "receiver",
    key: "name",
    render: (text) => <p>{text}</p>,
  },
  {
    title: "Mã đơn hàng",
    dataIndex: "no",
    key: "no",
  },
  {
    title: "Hình thức thanh toán",
    dataIndex: "manufacturer",
    key: "paymentMethod",
    render: (value, record, index) => {
      let color = "gray";
      if (record.paymentMethod.id == 2) {
        color = "green";
      }
      return <Tag color={color}>{record.paymentMethod.name}</Tag>;
    },
  },
  {
    title: "Tổng giá trị",
    dataIndex: "totalPrice",
    key: "totalPrice",
    render: (value, record, index) => {
      return record.totalPrice;
    },
  },
  {
    title: "Trạng thái thanh toán",
    dataIndex: "paid",
    key: "paid",
    render: (value, record, index) => {
      let color = record.paid ? "green" : "gray";

      return (
        <Tag color={color}>
          {record.paid ? "Đã thanh toán" : "Chưa thanh toán"}
        </Tag>
      );
    },
  },
  {
    title: "Trạng thái",
    key: "tags",
    dataIndex: "tags",
    render: (_, { status }) => {
      let color = "green";
      let message = "";
      console.log(status);
      switch (status) {
        case OrderStatus.CANCEL:
          color = "red";
          message = "Đã hủy";
          break;
        case OrderStatus.PENDING:
          color = "gray";
          message = "Đang chờ duyệt";
          break;
        case OrderStatus.SHIPPING:
          color = "yellow";
          message = "Đang giao";
          break;
        case OrderStatus.RECEIVED:
          color = "green";
          message = "Đã nhận";
          break;
        default:
          break;
      }
      return (
        <>
          <Tag color={color}>{message}</Tag>
        </>
      );
    },
  },
  {
    title: "Hành động",
    key: "action",
    render: (_, record) => {
      return <OrderAction data={record} />;
    },
  },
];
export const OrderManage = () => {
  const [data, setData] = useState<Order[]>([]);
  const { update } = useCategory();
  const id = localStorage.getItem("id");
  const user = localStorage.getItem("user");
  useEffect(() => {
    if (id === "1") {
      CartService.get({ url: "order" }).then((res) => {
        setData(res.data);
      });
    } else {
      CartService.get({ url: `/order/private/${user}` }).then((res) => {
        setData(res.data);
      });
    }
  }, [update]);
  return (
    <Table columns={columns} dataSource={data} pagination={{ pageSize: 5 }} />
  );
};

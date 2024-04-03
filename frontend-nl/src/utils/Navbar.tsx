import {
  BarChartOutlined,
  BookOutlined,
  FileFilled,
  NotificationOutlined,
  PhoneOutlined,
  ShoppingCartOutlined,
  UserOutlined,
} from "@ant-design/icons";
import { MenuProps } from "antd";
import React from "react";
import { Link, useHistory } from "react-router-dom";

export const HomeElements: (
  history: any,
  setCategory: React.Dispatch<React.SetStateAction<number>>
) => MenuProps["items"] = (
  history: any,
  setCategory: React.Dispatch<React.SetStateAction<number>>
) => {
  return [
    {
      key: "home",
      icon: React.createElement(UserOutlined),
      label: "Trang chủ",
      onClick: () => {
        setCategory(0);
        history.push("/");
      },
    },
    {
      key: 0,
      icon: React.createElement(NotificationOutlined),
      label: `Danh mục`,
      onClick: () => {
        history.push("/");
      },
      children: [
        {
          key: 1,
          icon: React.createElement(PhoneOutlined),
          label: "Xiaomi",
          onClick: () => {
            setCategory(1);
          },
        },
        {
          key: 2,
          icon: React.createElement(PhoneOutlined),
          label: "Iphone",
          onClick: () => {
            setCategory(2);
          },
        },
        {
          key: 3,
          icon: React.createElement(PhoneOutlined),
          label: "SamSung",
          onClick: () => {
            setCategory(3);
          },
        },
        {
          key: 4,
          icon: React.createElement(PhoneOutlined),
          label: "Oppo",
          onClick: () => {
            setCategory(4);
          },
        },
      ],
    },
  ];
};
export const AdminElements: (history: any) => MenuProps["items"] = (
  history: any
) => {
  return [
    {
      key: "phone",
      icon: <BookOutlined />,
      label: "Quản lý điện thoại",
      onClick: () => {
        history.push("/admin");
      },
    },
    {
      key: "order",
      icon: <FileFilled />,
      label: "Quản lý giao dịch",
      onClick: () => {
        history.push("/admin/order");
      },
    },
    {
      key: "chart",
      icon: <BarChartOutlined />,
      label: "Biểu đồ thống kê",
      onClick: () => {
        history.push("/admin/chart");
      },
    },
  ];
};

export const AdminItems = (name: string) => {
  const history = useHistory();
  const item: MenuProps["items"] = [
    {
      key: "hi",
      label: `Chào ${name}`,
      style: { width: 100 },
      children:
        name === "admin"
          ? [
              {
                key: "infor",
                icon: <UserOutlined />,
                label: `Đăng xuất`,
                onClick: () => {
                  localStorage.clear();
                  window.location.href = "/login";
                },
              },
            ]
          : [
              {
                key: "infor",
                icon: <UserOutlined />,
                label: `Thông tin cá nhân`,
                onClick: () => {
                  history.push("/user");
                },
              },
              {
                key: "cart",
                icon: <UserOutlined />,
                label: `Giỏ hàng`,
                onClick: () => {
                  history.push("/cart");
                },
              },
              {
                key: "orderDetail",
                icon: <ShoppingCartOutlined />,
                label: `Đơn hàng của tôi`,
                onClick: () => {
                  history.push("/order/detail");
                },
              },
              {
                key: "logout",
                icon: <UserOutlined />,
                label: `Đăng xuất`,
                onClick: () => {
                  localStorage.clear();
                  window.location.href = "/login";
                },
              },
            ],
    },
  ];
  return item;
};

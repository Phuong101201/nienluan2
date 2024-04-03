import React, { useEffect, useState } from "react";
import { useAuth } from "../../contexts/auth";
import { useHistory } from "react-router";
import LayoutDefault from "../../components/Layout/LayoutDefault";
import { Button, Space, Table, Tag } from "antd";
import type { ColumnsType } from "antd/es/table";
import { Product } from "../../types/Product";
import { useCategory } from "../../contexts/category";
import { localhost } from "../../service/api";
import { UnauthoritePage } from "../../components/Error";
import CustomModal from "../../components/CustomModal";
import Action from "../../components/Action";

const columns: ColumnsType<Product> = [
  {
    title: "Tên",
    dataIndex: "name",
    key: "name",
    render: (text) => <a>{text}</a>,
  },
  {
    title: "Chip",
    dataIndex: "chip",
    key: "chip",
  },
  {
    title: "Ram",
    dataIndex: "ram",
    key: "ram",
  },
  {
    title: "Rom",
    dataIndex: "rom",
    key: "rom",
  },
  {
    title: "Size",
    dataIndex: "size",
    key: "size",
  },
  {
    title: "Phân loại",
    dataIndex: "category",
    key: "category",
    render: (value, record, index) => {
      return record.category.name;
    },
  },
  {
    title: "Giá",
    dataIndex: "price",
    key: "price",
  },
  {
    title: "Pin",
    dataIndex: "pin",
    key: "pin",
  },
  {
    title: "Hành động",
    key: "action",
    render: (_, record) => <Action data={record} />,
  },
];

const AdminRoute: React.FC = () => {
  const { isAdmin } = useAuth();
  const [data, setData] = useState<Product[]>([]);

  const [isModalOpen, setIsModalOpen] = useState(false);
  const { update } = useCategory();
  useEffect(() => {
    localhost
      .get({
        url: "/phone/all",
      })
      .then((res) => {
        setData(res.data);
      })
      .catch(() => {
        <UnauthoritePage />;
      });
    isAdmin();
  }, [update]);
  return (
    <LayoutDefault>
      <div style={{ display: "flex", flexDirection: "column" }}>
        <Button
          type="primary"
          htmlType="submit"
          onClick={() => setIsModalOpen(true)}
          style={{ width: 100 }}
        >
          Thêm mới
        </Button>
        <Table columns={columns} dataSource={data}>
          hi
        </Table>
      </div>
      <CustomModal isModalOpen={isModalOpen} setIsModalOpen={setIsModalOpen} />
    </LayoutDefault>
  );
};

export default AdminRoute;

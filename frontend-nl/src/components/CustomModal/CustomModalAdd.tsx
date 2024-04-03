import { Button, Form, Input, Modal } from "antd";
import React, { useEffect } from "react";
import { Product } from "../../types/Product";
import { localhost } from "../../service/api";
import { useCategory } from "../../contexts/category";

const convertEnglish = (world: string) => {
  switch (world) {
    case "category":
      return "Loại";

    case "manufacturer":
      return "Nhà sản xuất";

    default:
      return "";
  }
};
const CustomModalAdd = (props: {
  modalShow: boolean;
  setModalShow: React.Dispatch<React.SetStateAction<boolean>>;
  typeModal?: string;
  values?: Product;
}) => {
  const { update, setUpdate } = useCategory();
  const handleSubmit = (v: { name: string }) => {
    localhost
      .post({ url: `/category/add${props.typeModal}`, data: v })
      .finally(() => {
        props.setModalShow(false);
        setUpdate(new Date());
      });
  };
  return (
    <>
      {props.typeModal && (
        <Modal
          footer={null}
          title={`Thêm ${convertEnglish(props.typeModal)}`}
          open={props.modalShow}
          onCancel={() => props.setModalShow(false)}
        >
          <Form onFinish={handleSubmit}>
            <Form.Item
              name="name"
              label={`Tên ${convertEnglish(props.typeModal)}`}
            >
              <Input />
            </Form.Item>
            <Form.Item>
              <Button htmlType="submit" type="primary">
                {" "}
                Tạo
              </Button>
            </Form.Item>
          </Form>
        </Modal>
      )}
    </>
  );
};

export default CustomModalAdd;

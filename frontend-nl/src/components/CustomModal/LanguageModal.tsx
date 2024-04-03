import { Button, Form, Input, Modal } from "antd";
import React from "react";
import { localhost } from "../../service/api";

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

const AuthorModal = (props: {
  modalShow: boolean;
  setModalShow: React.Dispatch<React.SetStateAction<boolean>>;
  typeModal?: string;
}) => {
  const handleSubmit = (v: { name: string }) => {
    localhost.post({ url: `/book/${props.typeModal}`, data: v });
  };
  return (
    <>
      {props.typeModal && (
        <Modal
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

export default AuthorModal;

import { Button, Col, Row, message } from "antd";
import React, { useState } from "react";
import { Product } from "../../types/Product";
import { localhost } from "../../service/api";
import { useCategory } from "../../contexts/category";
import ModelEditPhone from "./modelEditPhone";

const Action = (props: { data: Product }) => {
  const { setUpdate } = useCategory();
  const [show, setShow] = useState(false);

  const handleRemove = () => {
    localhost
      .delete({ url: `/phone/${props.data.id}` })
      .then(() => {
        message.info(`Xóa ${props.data.name} thành công`);
        setUpdate(new Date());
      })
      .catch((e) => {
        message.error(e);
      });
  };
  return (
    <div>
      <Row gutter={4}>
        <Col>
          <Button onClick={() => setShow(true)}>Chỉnh sửa</Button>
        </Col>
        <Col>
          <Button onClick={handleRemove} danger>
            Xóa
          </Button>
        </Col>
      </Row>
      <ModelEditPhone
        data={props.data}
        isModalOpen={show}
        setIsModalOpen={setShow}
      />
    </div>
  );
};

export default Action;

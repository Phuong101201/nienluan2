import React, { useEffect, useState } from "react";
import {
  Button,
  Col,
  Form,
  Input,
  InputNumber,
  Modal,
  Row,
  Select,
  SelectProps,
  Space,
  message,
} from "antd";
import { Manufacturer, PhoneRequest } from "../../types/Product";
import { MinusCircleOutlined, PlusOutlined } from "@ant-design/icons";
import { getManufacturer } from "../../service/ManufacturerService";
import { getCategory } from "../../service/CategoryService";
import CustomModalAdd from "./CustomModalAdd";
import { useCategory } from "../../contexts/category";
import { localhost } from "../../service/api";
const layout = {
  labelCol: { span: 12 },
  wrapperCol: { span: 12 },
};

const tailLayout = {
  wrapperCol: { offset: 8, span: 16 },
};
const CustomModal = (props: {
  isModalOpen: boolean;
  setIsModalOpen: React.Dispatch<React.SetStateAction<boolean>>;
}) => {
  const [manufacturer, setManufacturer] = useState<SelectProps["options"]>([]);
  const [category, setCategory] = useState<SelectProps["options"]>([]);
  const [modalshow, setModalShow] = useState<boolean>(false);
  const [modalType, setModalType] = useState<string>();
  const { setUpdate, update } = useCategory();
  useEffect(() => {
    getManufacturer().then((res) => setManufacturer(res));
    getCategory().then((res) => setCategory(res));
  }, [update]);
  const onFinish = (values: PhoneRequest) => {
    localhost
      .post({ url: "/phone", data: values })
      .then(() => {
        setUpdate(new Date());
        message.info("Thêm thành công");
      })
      .catch((e) => {
        message.error("Sản phẩm đã tồn tại. Nhập lại sản phẩm khác");
      });
    props.setIsModalOpen(false);
  };

  const setModalCustom = (v: string) => {
    setModalType(v);
    setModalShow(true);
  };
  return (
    <>
      <Modal
        title="Thêm sản phẩm"
        open={props.isModalOpen}
        width={1000}
        footer={null}
        onCancel={() => props.setIsModalOpen(false)}
      >
        <Form
          {...layout}
          name="control-hooks"
          onFinish={onFinish}
          style={{ maxWidth: 900 }}
        >
          <Row>
            <Col span={12}>
              <Form.Item
                name="name"
                label="Tên điện thoại"
                rules={[{ required: true }]}
              >
                <Input />
              </Form.Item>
              <Form.Item
                name="description"
                label="Mô tả"
                rules={[{ required: true }]}
              >
                <Input />
              </Form.Item>
              <Form.Item
                name="chip"
                label="Vi xử lý"
                rules={[{ required: true }]}
              >
                <Input />
              </Form.Item>
              <Row>
                <Col span={10} offset={2}>
                  <Form.Item
                    name="pin"
                    label="Pin"
                    rules={[{ required: true }]}
                  >
                    <InputNumber min={1000} />
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item
                    name="size"
                    label="Kích thước"
                    rules={[{ required: true }]}
                  >
                    <InputNumber min={1} />
                  </Form.Item>
                </Col>
              </Row>
              <Row>
                <Col span={10} offset={2}>
                  <Form.Item
                    name="ram"
                    label="Ram"
                    rules={[{ required: true }]}
                  >
                    <InputNumber min={1000} />
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item
                    name="rom"
                    label="Rom"
                    rules={[{ required: true }]}
                  >
                    <InputNumber min={1} />
                  </Form.Item>
                </Col>
              </Row>
              <Row>
                <Col span={10} offset={2}>
                  <Form.Item
                    name="price"
                    label="Giá"
                    rules={[{ required: true }]}
                  >
                    <InputNumber min={1000} />
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item
                    name="quantity"
                    label="Số lượng"
                    rules={[{ required: true }]}
                  >
                    <InputNumber min={1} />
                  </Form.Item>
                </Col>
              </Row>
              <Form.Item
                name="manufacturer"
                label="Nhà sản xuất"
                rules={[{ required: true }]}
              >
                <Select
                  options={manufacturer}
                  onSelect={(v) => console.log(v)}
                />
              </Form.Item>
              <PlusOutlined onClick={() => setModalCustom("manufacturer")} />
              <Form.Item
                name="category"
                label="Phân loại"
                rules={[{ required: true }]}
              >
                <Select options={category} onSelect={(v) => console.log(v)} />
              </Form.Item>
              <PlusOutlined onClick={() => setModalCustom("category")} />
            </Col>
            <Col span={10} offset={2}>
              <Form.List name="picture">
                {(fields, { add, remove }) => (
                  <>
                    {fields.map(({ key, name, ...restField }) => (
                      <Space
                        key={key}
                        style={{ display: "flex", marginBottom: 8 }}
                        align="baseline"
                      >
                        <div>Hình ảnh {key + 1}: </div>
                        <Form.Item
                          {...restField}
                          name={[name, "caption"]}
                          rules={[
                            {
                              required: true,
                              message: `Thiếu miêu tả hình ảnh`,
                            },
                          ]}
                        >
                          <Input placeholder={`Miêu tả hình ảnh`} />
                        </Form.Item>
                        <Form.Item
                          {...restField}
                          name={[name, "imagePath"]}
                          rules={[
                            {
                              required: true,
                              message: `Thiếu đường dẫn hình ảnh`,
                            },
                          ]}
                        >
                          <Input placeholder={`Đường dẫn hình ảnh`} />
                        </Form.Item>
                        <MinusCircleOutlined onClick={() => remove(name)} />
                      </Space>
                    ))}
                    <Form.Item>
                      <Button
                        type="dashed"
                        onClick={() => add()}
                        block
                        icon={<PlusOutlined />}
                      >
                        Thêm Hình ảnh
                      </Button>
                    </Form.Item>
                  </>
                )}
              </Form.List>
            </Col>
          </Row>
          <Form.Item {...tailLayout}>
            <Button type="primary" htmlType="submit">
              Thêm
            </Button>
          </Form.Item>
        </Form>
      </Modal>
      <CustomModalAdd
        modalShow={modalshow}
        setModalShow={setModalShow}
        typeModal={modalType}
      />
    </>
  );
};

export default CustomModal;

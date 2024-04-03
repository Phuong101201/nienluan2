import { MinusCircleOutlined, PlusOutlined } from "@ant-design/icons";
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
import React, { useEffect, useState } from "react";
import { PhoneRequest, Product } from "../../types/Product";
import { localhost } from "../../service/api";
import { useCategory } from "../../contexts/category";
import { getManufacturer } from "../../service/ManufacturerService";
import { getCategory } from "../../service/CategoryService";
const layout = {
  labelCol: { span: 12 },
  wrapperCol: { span: 12 },
};

const tailLayout = {
  wrapperCol: { offset: 8, span: 16 },
};
const ModelEditPhone = (props: {
  data: Product;
  isModalOpen: boolean;
  setIsModalOpen: React.Dispatch<React.SetStateAction<boolean>>;
}) => {
  const { setUpdate } = useCategory();
  const [manufacturer, setManufacturer] = useState<SelectProps["options"]>([]);
  const [category, setCategory] = useState<SelectProps["options"]>([]);
  useEffect(() => {
    getManufacturer().then((res) => setManufacturer(res));
    getCategory().then((res) => setCategory(res));
  }, []);
  const onFinish = (values: PhoneRequest) => {
    localhost
      .put({ url: `/phone/${props.data.id}`, data: values })
      .then(() => {
        setUpdate(new Date());
        message.info("Cập nhật thành công!");
      })
      .catch((e) => {
        message.error("Cập nhật thất bại!");
      });
    props.setIsModalOpen(false);
  };
  return (
    <Modal
      title="Thêm sách"
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
              label="Tên sách"
              rules={[{ required: true }]}
              initialValue={props.data.name}
            >
              <Input />
            </Form.Item>
            <Form.Item
              name="description"
              label="Mô tả"
              rules={[{ required: true }]}
              initialValue={props.data.description}
            >
              <Input />
            </Form.Item>
            <Form.Item
              name="chip"
              label="Vi xử lý"
              rules={[{ required: true }]}
              initialValue={props.data.chip}
            >
              <Input />
            </Form.Item>
            <Row>
              <Col span={10} offset={2}>
                <Form.Item
                  name="pin"
                  initialValue={props.data.pin}
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
                  initialValue={props.data.size}
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
                  initialValue={props.data.ram}
                  label="Ram"
                  rules={[{ required: true }]}
                >
                  <InputNumber min={1000} />
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item
                  name="rom"
                  initialValue={props.data.rom}
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
                  initialValue={props.data.price}
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
                  initialValue={props.data.quantity}
                  rules={[{ required: true }]}
                >
                  <InputNumber min={1} />
                </Form.Item>
              </Col>
            </Row>
            <Form.Item
              name="idManufacturer"
              label="Nhà xuất bản"
              rules={[{ required: true }]}
              initialValue={props.data.manufacturer.id}
            >
              <Select options={manufacturer} onSelect={(v) => console.log(v)} />
            </Form.Item>
            <Form.Item
              name="idCategory"
              label="Phân loại"
              rules={[{ required: true }]}
              initialValue={props.data.category.id}
            >
              <Select options={category} onSelect={(v) => console.log(v)} />
            </Form.Item>
          </Col>
          <Col span={10} offset={2}>
            <Form.List name="picture" initialValue={props.data.pictures}>
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
            Chỉnh sửa
          </Button>
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default ModelEditPhone;

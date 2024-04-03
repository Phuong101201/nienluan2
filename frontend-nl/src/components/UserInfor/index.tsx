import { Button, Form, Input, Select, SelectProps, message } from "antd";
import React, { useEffect, useState } from "react";
import { localhost } from "../../service/api";
import { RegisterRequest, UpdateUser } from "../../types/User";
import { useHistory } from "react-router-dom";
const { Option } = Select;
const formItemLayout = {
  labelCol: {
    xs: { span: 24 },
    sm: { span: 8 },
  },
  wrapperCol: {
    xs: { span: 24 },
    sm: { span: 16 },
  },
};
const tailFormItemLayout = {
  wrapperCol: {
    xs: {
      span: 24,
      offset: 0,
    },
    sm: {
      span: 16,
      offset: 8,
    },
  },
};
const options: SelectProps["options"] = [
  { label: "Name", value: "true" },
  { label: "Nữ", value: "false" },
];
const UserEdit = () => {
  const [form] = Form.useForm();
  const history = useHistory();
  const id = localStorage.getItem("id") || "";
  const [userInfor, setuserInfor] = useState<UpdateUser>();
  const [update, setUpdate] = useState(new Date());
  useEffect(() => {
    localhost.get({ url: `/all/${id}` }).then((res) => setuserInfor(res.data));
  }, [update]);
  // const [infor, setInfor] = useState<RegisterRequest>();
  const onFinish = (values: UpdateUser) => {
    const infor = {
      name: values.name,
      age: 20,
      phoneNumber: values.phoneNumber,
      sex: userInfor?.sex,
      address: values.address,
    };
    console.log(infor);
    localhost.put({ url: `/user/${id}`, data: infor }).then((res) => {
      message.info("Cập nhật thành công");
      setUpdate(new Date());
    });
  };
  console.log(userInfor);

  return userInfor ? (
    <Form
      {...formItemLayout}
      form={form}
      name="register"
      onFinish={onFinish}
      style={{
        padding: "10px",
      }}
    >
      <Form.Item
        name="name"
        label="Tên"
        tooltip="What do you want others to call you?"
        initialValue={userInfor?.name}
        rules={[
          {
            required: true,
            message: "Please input your nickname!",
            whitespace: true,
          },
        ]}
      >
        <Input />
      </Form.Item>
      <Form.Item
        name="address"
        label="Địa chỉ"
        initialValue={userInfor?.address}
        rules={[
          {
            required: true,
            message: "Please input your adress!",
            whitespace: true,
          },
        ]}
      >
        <Input />
      </Form.Item>
      <Form.Item
        name="phoneNumber"
        label="Số điện thoại"
        initialValue={userInfor?.phoneNumber}
        rules={[{ required: true, message: "Please input your phone number!" }]}
      >
        <Input style={{ width: "100%" }} />
      </Form.Item>
      <Form.Item name="gender" label="Giới tính">
        {userInfor.sex ? "Nam" : "Nữ"}
      </Form.Item>
      <Form.Item {...tailFormItemLayout}>
        <Button
          style={{ backgroundColor: "antiquewhite", color: "black" }}
          type="primary"
          htmlType="submit"
        >
          Cập nhật
        </Button>
      </Form.Item>
    </Form>
  ) : null;
};

export default UserEdit;

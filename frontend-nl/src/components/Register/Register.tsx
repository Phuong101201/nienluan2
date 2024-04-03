import { Button, Form, Input, Select, message } from "antd";
import React, { useState } from "react";
import { RegisterRequest } from "../../types/User";
import { localhost } from "../../service/api";
import { useHistory } from "react-router";
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

const RegisterForm = (props: {
  sellect: React.Dispatch<React.SetStateAction<string>>;
}) => {
  const [form] = Form.useForm();
  const history = useHistory();
  // const [infor, setInfor] = useState<RegisterRequest>();
  const onFinish = (values: any) => {
    const infor: RegisterRequest = {
      username: values.email,
      password: values.password,
      name: values.nickname,
      age: 20,
      phoneNumber: values.phone,
      sex: values.gender === "Male" ? true : false,
      adress: values.address,
    };
    localhost
      .post({ url: "/register", data: infor })
      .then((res) => {
        message.info("Đăng ký thành công");
        window.localStorage.setItem("token", res.data.token);
        props.sellect("login");
      })
      .catch(() => {
        message.error("Người dùng đã tồn tại");
      });
  };

  return (
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
        name="email"
        label="E-mail"
        rules={[
          {
            required: true,
            message: "Hãy nhập E-mail!",
          },
        ]}
      >
        <Input />
      </Form.Item>

      <Form.Item
        name="password"
        label="Mật khẩu"
        rules={[
          {
            required: true,
            message: "Hãy nhập mật khẩu!",
          },
        ]}
        hasFeedback
      >
        <Input.Password />
      </Form.Item>

      <Form.Item
        name="confirm"
        label="Xác nhận mật khẩu"
        dependencies={["password"]}
        hasFeedback
        rules={[
          {
            required: true,
            message: "Nhập lại mật khẩu!",
          },
          ({ getFieldValue }) => ({
            validator(_, value) {
              if (!value || getFieldValue("password") === value) {
                return Promise.resolve();
              }
              return Promise.reject(new Error("Mật khẩu không giống!"));
            },
          }),
        ]}
      >
        <Input.Password />
      </Form.Item>

      <Form.Item
        name="nickname"
        label="Tên người dùng"
        rules={[
          {
            required: true,
            message: "Nhập tên người dùng!",
            whitespace: true,
          },
        ]}
      >
        <Input />
      </Form.Item>
      <Form.Item
        name="adress"
        label="Địa chỉ"
        tooltip="Bạn sống ở đâu"
        rules={[
          {
            required: true,
            message: "Hãy nhập địa chỉ!",
            whitespace: true,
          },
        ]}
      >
        <Input />
      </Form.Item>
      <Form.Item
        name="phone"
        label="Số điện thoại"
        rules={[{ required: true, message: "Nhập số điện thoại!" }]}
      >
        <Input style={{ width: "100%" }} />
      </Form.Item>
      <Form.Item
        name="gender"
        label="Giới tinh"
        rules={[{ required: true, message: "Chọn giới tính!" }]}
      >
        <Select placeholder="Chọn giới tính">
          <Option value="male">Nam</Option>
          <Option value="female">Nữ</Option>
        </Select>
      </Form.Item>
      <Form.Item {...tailFormItemLayout}>
        <Button type="primary" htmlType="submit">
          Đăng ký
        </Button>
      </Form.Item>
    </Form>
  );
};

export default RegisterForm;

import {
  LaptopOutlined,
  NotificationOutlined,
  UserOutlined,
} from "@ant-design/icons";
import type { MenuProps } from "antd";
import { Breadcrumb, Layout, Menu } from "antd";
import React, { useEffect, useState } from "react";
import Main from "../../components/Main/Main";
import styles from "./style.module.css";
import { Link, useHistory } from "react-router-dom";
import { AdminElements, AdminItems, HomeElements } from "../../utils/Navbar";
import { Product } from "../../types/Product";
import { useCategory } from "../../contexts/category";
import { localhost } from "../../service/api";
import { useAuth } from "../../contexts/auth";
const { Header, Content, Sider, Footer } = Layout;

const LayoutDefault = (props: { children: React.ReactNode }) => {
  const history = useHistory();
  const { isAdmin } = useAuth();
  const { setCategory, max } = useCategory();
  const [name, setName] = useState(window.localStorage.getItem("user") || "");
  const id = localStorage.getItem("id");
  useEffect(() => {
    if (id) {
      localhost.get({ url: `/${id}` }).then((res) => {
        const name = res.data.name;
        setName(name);
        window.localStorage.setItem("user", name);
        if (id === "1" && window.location.pathname === "/") {
          history.push("/admin");
        }
      });
    }
  }, []);
  return (
    <Layout>
      <Header
        className={styles.header}
        style={{ backgroundColor: "antiquewhite" }}
      >
        <div>
          <div
            className={styles.logo}
            onClick={() => {
              history.push("/");
            }}
          >
            IMobile
          </div>
        </div>
        <div
          style={{
            width: 200,
            display: "flex",
            justifyContent: "space-between",
          }}
        >
          {name ? (
            <>
              <Menu mode="horizontal" items={AdminItems(name)} />
            </>
          ) : (
            <>
              <Link to={"/login"}>Log in</Link>
              <div style={{ width: 40 }}></div>
            </>
          )}
        </div>
      </Header>
      <Layout>
        <Sider width={200} className="site-layout-background">
          <Menu
            mode="inline"
            defaultSelectedKeys={["1"]}
            defaultOpenKeys={["sub1"]}
            style={{ height: "100%", borderRight: 0 }}
            items={
              localStorage.getItem("id") == "1"
                ? AdminElements(history)
                : HomeElements(history, setCategory)
            }
          />
        </Sider>
        <Layout>
          {/* <Breadcrumb>
            <Breadcrumb.Item>Home</Breadcrumb.Item>
            <Breadcrumb.Item>List</Breadcrumb.Item>
            <Breadcrumb.Item>HomePage</Breadcrumb.Item>
          </Breadcrumb> */}
          <Content
            className="site-layout-background"
            style={{
              padding: 16,
              margin: 0,
              // minHeight: 630,
            }}
          >
            {props.children}
          </Content>
        </Layout>
      </Layout>
      {/* <Layout>
        <Footer
          style={{ textAlign: "center", backgroundColor: "antiquewhite" }}
        >
          @2023
        </Footer>
      </Layout> */}
    </Layout>
  );
};

export default LayoutDefault;

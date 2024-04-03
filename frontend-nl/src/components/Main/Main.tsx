import React, { useEffect, useState } from "react";
import styles from "./style.module.css";
import { Product } from "../../types/Product";
import { Button, Col, Image, Row } from "antd";
// import { product } from "./Mock/mock";
import { useHistory } from "react-router-dom";
import { useCategory } from "../../contexts/category";
import Search from "antd/es/input/Search";
import { CartService, localhost } from "../../service/api";
const mockA = (amount: number, product1: Product[]) => {
  const goods: Product[] = [];
  for (let index = 0; index < amount; index++) {
    goods.push(...product1);
  }
  return goods;
};
const Main = () => {
  const history = useHistory();

  const { setProducts, setUpdate, products, max } = useCategory();
  // const { user } = useAuth();
  const [name, setName] = useState(window.localStorage.getItem("user") || "");
  useEffect(() => {
    console.log(products);
  }, [products]);
  const onChangeSearch = (e: any) => {
    const id = setTimeout(() => {
      localhost
        .get({
          url: "/phone/search",
          configs: { params: { name: e.target.value } },
        })
        .then((res) => {
          setProducts(res.data);
          setUpdate(new Date());
        })
        .finally(() => {
          clearTimeout(id);
        });
    }, 2000);
  };
  const onSearch = (value: any, _e: any) => {
    localhost
      .get({
        url: "/book/search",
        configs: { params: { name: value } },
      })
      .then((res) => {
        setProducts(res.data);
        setUpdate(new Date());
      });
  };
  return (
    <div>
      <Search
        placeholder="Tìm điện thoại"
        enterButton={
          <Button style={{ backgroundColor: "antiquewhite", color: "black" }}>
            Tìm
          </Button>
        }
        size="middle"
        onChange={onChangeSearch}
        onSearch={onSearch}
      />
      <Row gutter={[12, 12]} className={styles.container}>
        {products.map((mock) => {
          return (
            <>
              <Col
                className={styles.goods}
                key={mock.id}
                onClick={() => {
                  history.push(`/infor/${mock.id}`);
                }}
              >
                {mock.pictures.length != 0 ? (
                  <Image
                    src={mock.pictures[0].imagePath}
                    className={styles.picture}
                    preview={false}
                    height="80%"
                  />
                ) : null}
                <div className={styles.infor}>
                  <div>{mock.name}</div>
                  <div style={{ color: "red", fontSize: 24 }}>{mock.price}</div>
                </div>
              </Col>
            </>
          );
        })}
      </Row>
    </div>
  );
};

export default Main;

import { Button, Col, Row, message } from "antd";
import { CartService } from "../../service/api";
import { Product } from "../../types/Product";
import { useHistory } from "react-router-dom";
import { useAuth } from "../../contexts/auth";
import { useEffect } from "react";
import { AddCart } from "../../types/CartItem";
import { useItem } from "../../contexts/Items";
import { useCategory } from "../../contexts/category";

const Payment = (props: { data: Product; sale?: number }) => {
  const { user } = useAuth();
  const { setUpdate } = useCategory();
  const history = useHistory();
  useEffect(() => {
    console.log(user);
  }, [user]);
  const AddToCart = (data: Product) => {
    if (user) {
      const body: AddCart = {
        id: data.id,
        image: data.pictures[0].imagePath,
        name: data.name,
        price: data.price,
        quantity: 1,
        user: user || "test",
      };
      CartService.post({ url: "/basket/add", data: body })
        .then(() => {
          message.info(`Sản phẩm ${body.name} đã thêm vào giỏ hàng`);
        })
        .finally(() => {
          setUpdate(new Date());
        });
    } else {
      history.replace("/login");
    }
  };
  return (
    <div>
      <Col>
        <Row>
          <Col
            style={{
              color: "red",
              fontSize: 36,
              height: 40,
              fontWeight: "bold",
            }}
          >
            {props.data.price}
          </Col>
          {props.sale ? <Col>{props.sale}</Col> : null}
        </Row>
        <Row>
          <Col>
            <Button
              style={{ backgroundColor: "antiquewhite", color: "black" }}
              onClick={() => AddToCart(props.data)}
            >
              Thêm vào giỏ hàng
            </Button>
          </Col>
        </Row>
      </Col>
    </div>
  );
};

export default Payment;

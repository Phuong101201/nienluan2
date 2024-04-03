import React, { useEffect, useState } from "react";
import { CartService } from "../../service/api";
import { useAuth } from "../../contexts/auth";
import { CartType } from "../../types/CartItem";
import Items from "../Items";
import { useItem } from "../../contexts/Items";
import { useHistory } from "react-router-dom";
import { Button } from "antd";
const Cart = () => {
  const { items, setItems } = useItem();
  const [update, setUpdate] = useState(new Date());
  const history = useHistory();

  useEffect(() => {}, [items]);
  const handlePay = () => {
    history.push("/checkout");
  };
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <Items cartItem={items} update={setUpdate} />
      {items?.items ? (
        <Button
          onClick={handlePay}
          htmlType="submit"
          style={{
            backgroundColor: "antiquewhite",
            width: 100,
            color: "black",
          }}
          type="primary"
        >
          Thanh toán
        </Button>
      ) : null}
    </div>
  );
};

export default Cart;

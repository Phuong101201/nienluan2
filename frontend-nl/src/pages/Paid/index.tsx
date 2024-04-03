import React, { useEffect, useState } from "react";
import { useItem } from "../../contexts/Items";
import { Spin, message } from "antd";
import { useHistory } from "react-router-dom";
import { useCategory } from "../../contexts/category";
import "./style.css";
import { CartService } from "../../service/api";
const Paid = () => {
  const { setUpdate } = useCategory();
  const [spin, setSpin] = useState(true);
  const history = useHistory();
  // useEffect(() => {
  const data = localStorage.getItem("order") || "";
  const codereturn = window.location.search;
  if (data !== "" && codereturn && codereturn.includes("vnp_ResponseCode=00")) {
    CartService.post({
      url: "/order",
      data: { ...JSON.parse(data), paid: true },
    })
      .then(() => {
        message.info("Bạn đã thanh toán thành công");
        setUpdate(new Date());
        history.push("/");
      })
      .finally(() => {
        setSpin(false);
      });

    localStorage.removeItem("order");
  } else if (codereturn && !codereturn.includes("vnp_ResponseCode=00")) {
    const id = setTimeout(() => {
      message.error("Xảy ra lỗi khi thanh toán");
      setSpin(false);
      setUpdate(new Date());
      clearTimeout(id);
      history.push("/");
    }, 3000);
  }
  // }, []);
  return (
    <div className="example">
      <Spin spinning={spin} />
    </div>
  );
};

export default Paid;

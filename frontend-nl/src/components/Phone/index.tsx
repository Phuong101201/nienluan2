import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Product } from "../../types/Product";
import { localhost } from "../../service/api";
import ImageBox from "../Image";
import { Detail } from "../Detail";
import Payment from "../Payment";

const Phone = () => {
  //@ts-ignore
  const { id } = useParams();
  const [phone, setPhone] = useState<Product>();
  useEffect(() => {
    localhost.get({ url: `/phone/${id}` }).then((res) => {
      setPhone(res.data);
    });
  }, []);
  return (
    <div>
      {phone ? (
        <div
          style={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-between",
          }}
        >
          <ImageBox images={phone.pictures} />
          <Detail data={phone} />
          <Payment data={phone} />
        </div>
      ) : null}
    </div>
  );
};

export default Phone;

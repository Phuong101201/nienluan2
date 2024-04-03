import { Col, Row } from "antd";
import React from "react";
import { Product } from "../../types/Product";

export const Detail = (props: { data: Product }) => {
  return (
    <div>
      <h1>Cấu hình chi tiết {props.data.name}</h1>
      <div style={{ width: 350 }}>
        <Row
          style={{ backgroundColor: "rgb(240 232 232)" }}
          gutter={{ xs: 8, sm: 16, md: 24, lg: 32 }}
        >
          <Col className="gutter-row" span={8}>
            <div>Tên điện thoại:</div>
          </Col>
          <Col className="gutter-row" span={12}>
            <div>{props.data.name}</div>
          </Col>
        </Row>
        <Row gutter={{ xs: 8, sm: 16, md: 24, lg: 32 }}>
          <Col className="gutter-row" span={8}>
            <div>Chip:</div>
          </Col>
          <Col className="gutter-row" span={12}>
            <div>{props.data.chip}</div>
          </Col>
        </Row>
        <Row
          style={{ backgroundColor: "rgb(240 232 232)" }}
          gutter={{ xs: 8, sm: 16, md: 24, lg: 32 }}
        >
          <Col className="gutter-row" span={8}>
            <div>Ram:</div>
          </Col>
          <Col className="gutter-row" span={12}>
            <div>{props.data.ram}</div>
          </Col>
        </Row>
        <Row gutter={{ xs: 8, sm: 16, md: 24, lg: 32 }}>
          <Col className="gutter-row" span={8}>
            <div>Bộ nhớ trong:</div>
          </Col>
          <Col className="gutter-row" span={12}>
            <div>{props.data.rom}</div>
          </Col>
        </Row>
        <Row
          style={{ backgroundColor: "rgb(240 232 232)" }}
          gutter={{ xs: 8, sm: 16, md: 24, lg: 32 }}
        >
          <Col className="gutter-row" span={8}>
            <div>Kích thước:</div>
          </Col>
          <Col className="gutter-row" span={12}>
            <div>{props.data.size}</div>
          </Col>
        </Row>
        <Row gutter={{ xs: 8, sm: 16, md: 24, lg: 32 }}>
          <Col className="gutter-row" span={8}>
            <div>Dung lượng pin: </div>
          </Col>
          <Col className="gutter-row" span={12}>
            <div>{props.data.pin} mAh</div>
          </Col>
        </Row>
      </div>
    </div>
  );
};

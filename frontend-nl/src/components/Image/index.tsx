import { Image } from "antd";
import React, { useState } from "react";
import { Picture } from "../../types/Product";

const ImageBox = (props: { images: Picture[] }) => {
  const [src, setSRC] = useState(props.images[0].imagePath);

  return (
    <div style={{ display: "flex", flexDirection: "column" }}>
      <Image src={src} height={400} width={300} />
      <div style={{ display: "flex", height: 100, marginTop: 20 }}>
        {props.images ? (
          props.images.map((image) => {
            return (
              <Image
                height={70}
                preview={false}
                src={image.imagePath}
                style={{ marginRight: 20 }}
                onClick={() => setSRC(image.imagePath)}
              />
            );
          })
        ) : (
          <div></div>
        )}
      </div>
    </div>
  );
};

export default ImageBox;

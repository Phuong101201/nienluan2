import React, { useEffect, useState } from "react";
import Main from "../../components/Main/Main";
import LayoutDefault from "../../components/Layout/LayoutDefault";
import { useCategory } from "../../contexts/category";
import { Pagination } from "antd";

const HomePage: React.FC = () => {
  const { products, setPage, max } = useCategory();
  useEffect(() => {}, [products]);
  return (
    <LayoutDefault>
      <Main />
      <Pagination
        onChange={(page, pageSize) =>
          setPage({ page: page - 1, size: pageSize })
        }
        defaultCurrent={1}
        total={max}
      />
    </LayoutDefault>
  );
};

export default HomePage;

import { message } from "antd";
import React, { createContext, useContext, useEffect, useState } from "react";
import { localhost } from "../service/api";
import { Product } from "../types/Product";
import { InternalServerPage, UnauthoritePage } from "../components/Error";
import { Paging } from "../types/Paging";
interface ContextProps {
  products: Product[];
  setCategory: React.Dispatch<React.SetStateAction<number>>;
  setPage: React.Dispatch<React.SetStateAction<Paging>>;
  setUpdate: React.Dispatch<React.SetStateAction<Date>>;
  update: Date;
  setProducts: React.Dispatch<React.SetStateAction<Product[]>>;
  max: number;
}
const CategoryContext = createContext({} as ContextProps);
const CategoryProvider = (props: { children: React.ReactNode }) => {
  const [category, setCategory] = useState<number>(0);
  const [products, setProducts] = useState<Product[]>([]);
  const [update, setUpdate] = useState(new Date());
  const [page, setPage] = useState<Paging>({ page: 0, size: 10 });
  const [max, setMax] = useState(0);
  useEffect(() => {
    if (category === 0) {
      localhost
        .get({
          url: "/phone",
          configs: { params: { page: page.page, size: page.size } },
        })
        .then((res) => {
          setProducts(res.data.content);
          setMax(res.data.totalElements);
        })
        .catch(() => {
          <UnauthoritePage />;
        });
    } else
      localhost
        .get({
          url: "/phone/manufacturer",
          configs: { params: { id: category } },
        })
        .then((res) => {
          setProducts(res.data.content);
          setMax(res.data.totalElements);
        })
        .catch((e) => {
          <InternalServerPage />;
        });
  }, [category, page]);
  return (
    <CategoryContext.Provider
      value={{
        products,
        setCategory,
        setPage,
        setUpdate,
        update,
        setProducts,
        max,
      }}
    >
      <>{props.children}</>
    </CategoryContext.Provider>
  );
};
const useCategory = () => {
  return useContext(CategoryContext);
};
export { CategoryProvider, useCategory, CategoryContext };

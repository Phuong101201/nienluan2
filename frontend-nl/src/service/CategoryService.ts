import { SelectProps } from "antd";
import { Category } from "../types/Product";
import { localhost } from "./api";

export const getCategory = (): Promise<SelectProps["options"]> => {
  return localhost.get({ url: "/category/all" }).then((res) => {
    const temp: SelectProps["options"] = [];
    const CategoryList: Category[] = res.data;
    CategoryList.forEach((Category) => {
      temp.push({ value: Category.id, label: Category.name });
    });
    return Promise.resolve(temp);
  });
};

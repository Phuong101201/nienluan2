import { SelectProps } from "antd";
import { Manufacturer } from "../types/Product";
import { localhost } from "./api";

export const getManufacturer = (): Promise<SelectProps["options"]> => {
  return localhost.get({ url: "/category/manufacturer" }).then((res) => {
    const temp: SelectProps["options"] = [];
    const manufacturerList: Manufacturer[] = res.data;
    manufacturerList.forEach((manufacturer) => {
      temp.push({ value: manufacturer.id, label: manufacturer.name });
    });
    return Promise.resolve(temp);
  });
};

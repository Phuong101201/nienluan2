export interface Product {
  id: number;
  name: string;
  chip: string;
  ram: number;
  rom: number;
  size: number;
  manufacturer: Manufacturer;
  category: Category;
  price: number;
  pictures: Picture[];
  pin: number;
  description: string;
  quantity: number;
}

export interface Manufacturer {
  id: number;
  name: string;
}

export interface Category {
  id: number;
  name: string;
}
export interface Picture {
  imagePath: string;
  caption: string;
}
export interface PictureRequest {
  path: string;
  caption: string;
}
export interface PhoneRequest {
  name: string;
  chip: string;
  ram: number;
  rom: number;
  size: number;
  picture: PictureRequest[];
  quantity: number;
  manufacturer: number;
  category: number;
  price: number;
  description: string;
  pin: number;
}

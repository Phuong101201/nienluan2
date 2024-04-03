import React from "react";
import { Switch, Route } from "react-router-dom";
import { CustomRoutes } from "../types/CustomRoute";
import Login from "../pages/Login/Login";
import HomePage from "../pages/Home/Home";
import Cart from "../components/Cart";
import CartPage from "../pages/Cart";
import AdminRoute from "./admin/AdminRoute";
import InforPhonePage from "../pages/InforPhone";
import {
  NotFoundPage,
  InternalServerPage,
  UnauthoritePage,
} from "../components/Error";
import PayPage from "../pages/Pay/PayPage";
import CheckoutPage from "../pages/CheckoutPage/CheckoutPage";
import Paid from "../pages/Paid";
import OrderManagePage from "../pages/OrderManagePage";
import OrderDetailPage from "../pages/OrderDetailPage";
import Chart from "../components/Chart";
import UserInfor from "../pages/UserInforPage";
import ChartPage from "../pages/ChartPage";
const routes: CustomRoutes[] = [
  { path: "/login", exact: true, component: Login },
  { path: "/", exact: true, component: HomePage },
  { path: "/cart", exact: true, component: CartPage },
  { path: "/checkout", exact: true, component: CheckoutPage },
  { path: "/order", exact: true, component: PayPage },
  { path: "/order/detail", exact: true, component: OrderDetailPage },
  { path: "/paid", exact: true, component: Paid },
  // { path: "/test", exact: true, component: Chart },
  { path: "/user", exact: true, component: UserInfor },
];

const ErrorPage: CustomRoutes[] = [
  { path: "error/404", exact: true, component: NotFoundPage },
  { path: "error/401", exact: true, component: UnauthoritePage },
  { path: "error/500", exact: true, component: InternalServerPage },
];
const RouteManage = () => {
  return (
    <Switch>
      <Route path="/admin" exact component={AdminRoute} />
      <Route path="/infor/:id" exact component={InforPhonePage} />
      <Route path="/admin/order" exact component={OrderManagePage} />
      <Route path="/admin/chart" exact component={ChartPage} />
      {ErrorPage.map((route, key) => {
        return (
          <Route
            key={key}
            path={route.path}
            exact={route.exact}
            component={route.component}
          />
        );
      })}
      {routes.map((route, key) => {
        return (
          <Route
            key={key}
            path={route.path}
            exact={route.exact}
            component={route.component}
          />
        );
      })}
    </Switch>
  );
};

export default RouteManage;

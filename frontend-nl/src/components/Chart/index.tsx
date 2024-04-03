import React, { useEffect, useState } from "react";
import { data, options } from "../../utils/Chart";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  BarElement,
  Title,
  Tooltip,
  Legend,
  LineElement,
} from "chart.js";

import { Bar } from "react-chartjs-2";
import { ChartProps } from "../../types/Chart";
import { CartService } from "../../service/api";
import moment from "moment";
ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  BarElement,
  Title,
  Tooltip,
  Legend
);
const ChartCustom = () => {
  const [data, setDate] = useState({
    labels: [""],
    datasets: [
      {
        label: "Dataset 1",
        data: [1],
        borderColor: "rgb(255, 99, 132)",
        backgroundColor: "rgba(255, 99, 132, 0.5)",
      },
    ],
  });
  useEffect(() => {
    const label: string[] = [];
    const total: number[] = [];
    CartService.get({ url: "/order/createDate" }).then(
      (res: { data: { [key: string]: number }[] }) => {
        const test = Object.entries(res.data);
        test.map((order: any) => {
          label.push(order[0]);
          total.push(order[1]);
        });
        setDate({
          labels: label,
          datasets: [
            {
              label: "Doanh thu",
              data: label.map((v, i) => total[i]),
              borderColor: "rgb(255, 99, 132)",
              backgroundColor: "rgba(255, 99, 132, 0.5)",
            },
          ],
        });
      }
    );
  }, [JSON.stringify(data)]);
  return (
    <div className="App">
      <Bar options={options} data={data} width={1000} height={500} />
    </div>
  );
};

export default ChartCustom;

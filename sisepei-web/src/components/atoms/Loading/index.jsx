import { LoadingOutlined } from "@ant-design/icons";
import { Spin } from "antd";

export function Loading() {
  return (
    <Spin
      indicator={<LoadingOutlined style={{ fontSize: 48 }} spin />}
      fullscreen
    />
  );
}

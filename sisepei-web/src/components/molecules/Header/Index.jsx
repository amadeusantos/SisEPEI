import { Link, useLocation } from "react-router-dom";
import { Layout, Typography } from "antd";

import "./Header.css";
import { Menu } from "../Menu";

const Header = () => {
  const location = useLocation();
  const isRegisterOrLoginCurrentRoute = ["/login", "/register"].includes(
    location.pathname
  );

  return (
    <Layout.Header
      style={{
        display: "flex",
        alignItems: "center",
        justifyContent: "space-between",
        backgroundColor: "var(--primary)",
      }}
    >
      <Link className="link" to={"/"}>
        <Typography.Title style={{ color: "var(--secundary)", margin: 0 }}>
          SisPEI
        </Typography.Title>
      </Link>
      {!isRegisterOrLoginCurrentRoute && (
        <Menu />
      )}
    </Layout.Header>
  );
};

export default Header;

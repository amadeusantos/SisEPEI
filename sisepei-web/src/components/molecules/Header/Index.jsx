import { useLocation, Link } from "react-router-dom";
import { Layout, Typography } from "antd";
import Cookies from "js-cookie";

import "./Header.css";
import { Menu } from "../Menu";

const Header = () => {
  const location = useLocation();
  const isLogged = Boolean(Cookies.get("token"));

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

      {isLogged && (
        <Menu />
      )}
    </Layout.Header>
  );
};

export default Header;

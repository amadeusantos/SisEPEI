import { useLocation } from "react-router-dom";

import "./Header.css";
import { logout } from "../../../services/AuthenticationService";
import { Button, Dropdown, Layout, Typography } from "antd";

const items = [
  {
    key: "1",
    label: (
      <Button type="button" onClick={logout}>
        <span>logout</span>
      </Button>
    ),
  },
];

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
      <Typography.Title style={{ color: "var(--secundary)", margin: 0 }}>
        SisPEI
      </Typography.Title>
      {!isRegisterOrLoginCurrentRoute && (
        <Dropdown menu={{ items }} placement="bottomRight">
          <Button>Ações</Button>
        </Dropdown>
      )}
    </Layout.Header>
  );
};

export default Header;

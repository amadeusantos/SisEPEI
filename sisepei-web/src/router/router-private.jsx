import { Navigate } from "react-router-dom";
import { getUser } from "../utils/user-store";
import Cookies from "js-cookie";
import { Loading } from "../components/atoms/Loading";

export const PrivateRoute = ({ children, profiles = [] }) => {
  const { data, isLoading } = getUser();
  console.log({ data });
  const isAuthenticated = !!Cookies.get("token") || !!data;
  if (!isAuthenticated) return <Navigate to="/login" />;
  if (isLoading) return <Loading />;
  const isAuthorized =
    profiles.length == 0 ||
    data.profiles.filter((profile) => profiles.includes(profile.name)).length >
      0;
  return isAuthorized ? children : <Navigate to="/" />;
};

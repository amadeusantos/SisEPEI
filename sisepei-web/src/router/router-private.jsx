import { Navigate } from "react-router-dom";
import Cookies from "js-cookie";

import { Loading } from "../components/atoms/Loading";
import { useWhoami } from "../store/users.store";

export const PrivateRoute = ({ children, profiles = [] }) => {
  const { data: user, isLoading: isLoadingWhoami } = useWhoami();
  const hasToken = Boolean(Cookies.get("token"));
  const isLoading = hasToken ? false : isLoadingWhoami;
  const isAuthenticated = hasToken || !!user;
  const isAuthorized =
    profiles?.length == 0 ||
    user?.profiles?.some((profile) => profiles.includes(profile.name));

  if (isLoading) {
    return <Loading />;
  }

  if (!isAuthenticated) {
    return <Navigate to="/login" />;
  }

  return isAuthorized ? children : <Navigate to="/" />;
};

import axios from "axios";
import Cookies from "js-cookie";

import { APiException } from "./exception"

export const api = axios.create({
  baseURL: "http://localhost:8080/",
});

export async function request(method, url, { token, body } = {}) {
  return await api
    .request({
      method,
      headers: { Authorization: token && "Bearer " + token },
      data: body,
      url,
    })
    .then((response) =>
      response.data
    )
    .catch((err) => {
      const isForbbidenError = err.response.status === 403;

      if (isForbbidenError) {
        Cookies.remove('token')
        window.location.href = '/login'
      } else {
        throw new APiException(err.response.status, err.response.data);
      }
    });
}

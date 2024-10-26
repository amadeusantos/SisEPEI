import axios from "axios";
import { APiException } from "./exception";

export const api = axios.create({
  baseURL: "http://localhost:8080/api/",
});

export async function request(method, url, { token, body }) {
  let result = new APiException(500, "No connection");
  return await api
    .request({
      method,
      headers: { Authorization: token && "Bearer " + token },
      data: body,
      url,
    })
    .then((response) =>
      result = response.data
    )
    .catch((err) => {
      throw new APiException(err.response.status, err.response.data);
    });
}

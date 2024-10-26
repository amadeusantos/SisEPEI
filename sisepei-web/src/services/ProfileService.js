import { request } from "./api";

export async function listProfiles() {
  return request("GET", "");
}

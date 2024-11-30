import Cookies from "js-cookie";

import { request } from "./api";

const PROFILES = 'profiles/'

export async function getProfiles() {
  const token = Cookies.get("token");

  return await request("GET", PROFILES, { token });
}

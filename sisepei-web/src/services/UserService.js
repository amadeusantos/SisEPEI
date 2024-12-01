import { request } from "./api";
import Cookies from "js-cookie";

/**
 * Fetches a paginated list of users, optionally filtered by profile ID.
 *
 * @param {Object} params - The pagination and filter parameters.
 * @param {number} [params.page=1] - The current page number (defaults to 1).
 * @param {number} [params.size=10] - The number of users per page (defaults to 10).
 * @param {string} [params.profileId=undefined] - Optional filter to limit users to a specific profile ID.
 *
 * @returns {Promise<Object>} A promise that resolves to the paginated users, typically including an array of users and metadata (e.g., total pages).
 *
 * @throws {APIException} Throws an error if the request fails or the server responds with an error.
 */
export async function getUsers() {
  const token = Cookies.get("token");

  return await request("GET", 'users', { token });
}

export async function getWhoami() {
  const token = Cookies.get("token");
  return await request("GET", "users/me", { token });
}

export async function patchUserProfiles({ id, profileIds }) {
  const token = Cookies.get("token");
  const body = {
    profileIds
  }

  return await request("PATCH", `users/${id}/profiles`, { token, body });
}

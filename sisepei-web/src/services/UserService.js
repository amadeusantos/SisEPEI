import { request } from "./api";

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
export async function listUsers({
  page = 1,
  size = 10,
  profileId = undefined,
}) {
  let uri = `?page=${page}&size=${size}`;

  if (profileId) {
    uri += `&profileId=${profileId}`;
  }
  return await request("GET", uri);
}

/**
 * Updates the profiles associated with a specific user.
 *
 * @param {string} id - The unique identifier of the user whose profiles are being updated.
 * @param {number[]} profiles - An array of profile IDs to be assigned to the user.
 *
 * @returns {Promise<void>} A promise that resolves when the user's profiles have been successfully updated.
 *
 * @throws {ZodError} Throws an error if input validation fails (e.g., invalid user ID or profiles array).
 * @throws {APIException} Throws an error if the request fails or the server responds with an error.
 */
export async function editUserProfiles(id, profiles) {
  return await request("PATCH", `/${id}`, { body: { profiles } });
}

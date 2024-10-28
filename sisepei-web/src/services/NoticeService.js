import { z } from "zod";
import { request } from "../services/api";

/**
 * Fetches a paginated list of notices based on the provided page, size, and optional filter by axle.
 *
 * @param {Object} params - The pagination and filter parameters.
 * @param {number} [params.page=1] - The current page number (defaults to 1).
 * @param {number} [params.size=10] - The number of notices per page (defaults to 10).
 * @param {string} [params.axle=undefined] - Optional filter to limit notices to a specific axle/category.
 *
 * @returns {Promise<Object>} A promise that resolves to the paginated notices, typically including an array of notices and metadata (e.g., total pages).
 *
 * @throws {APIException} Throws an error if the request fails or the server responds with an error.
 */
export async function paginationNotices({ page = 1, size = 10, axle = undefined }) {
  let uri = `?page=${page}&size=${size}`;

  if (axle) {
    uri += `&axle=${axle}`;
  }
  return await request("GET", uri);
}

const noticeSchema = z.object({
  title: z.string(),
  description: z.string(),
  requirements: z.string(),
  date: z.coerce.date(),
  axle: z.enum(["EXTENSAO", "PESQUISA", "INOVACAO"]),
});

/**
 * Creates a new notice with the provided details.
 *
 * @param {Object} notice - The data for the new notice.
 * @param {string} notice.title - The title of the notice.
 * @param {string} notice.description - A detailed description of the notice.
 * @param {string[]} notice.requirements - A list of requirements associated with the notice.
 * @param {Date} notice.date - The date associated with the notice (e.g., publication or event date).
 * @param {string} notice.axle - The axle or category to which the notice belongs.
 *
 * @returns {Promise<Object>} A promise that resolves to the newly created notice object.
 *
 * @throws {ZodError} Throws an error if input validation fails (e.g., invalid title, description, or date).
 * @throws {APIException} Throws an error if the server fails to create the notice or responds with an error.
 */
export async function createNotice({
  title,
  description,
  requirements,
  date,
  axle,
}) {
  const token = Cookies.get('token');
  const body = noticeSchema.parse({
    title,
    description,
    requirements,
    date,
    axle,
  });

  return await request("POST", "notices", { body, token });
}

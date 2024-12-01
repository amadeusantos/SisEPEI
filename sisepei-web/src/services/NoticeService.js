import { z } from "zod";
import { request } from "./api";
import Cookies from "js-cookie";
import { message } from "antd";

/**
 * Fetches a paginated list of notices based on the provided page, size, and optional filter by axle.
 *
 * @returns {Promise<Object>} A promise that resolves to the paginated notices, typically including an array of notices and metadata (e.g., total pages).
 *
 * @throws {APIException} Throws an error if the request fails or the server responds with an error.
 */
export async function listNotices() {
  let uri = `notices`;
  const token = Cookies.get("token");
  return await request("GET", uri, { token });
}

const noticeSchema = z.object({
  title: z.string(),
  description: z.string(),
  requirements: z.string(),
  time: z.coerce.date(),
  axle: z.enum(["EXTENSAO", "PESQUISA", "INOVACAO"]),
  file: z.string(),
  filename: z.string(),
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
 * @param {string} [noticeData.file] - The base64 file of the notice.
 * @param {string} [noticeData.filename] - The name of the file.
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
  time,
  axle,
  file,
  filename
}) {
  const token = Cookies.get("token");
  const body = noticeSchema.parse({
    title,
    description,
    requirements,
    time,
    axle,
    file,
    filename
  });

  return await request("POST", "notices", { body, token });
}

/**
 * Retrieves the file associated with a specific notice by its ID.
 *
 * @param {string} id - The unique identifier of the notice whose file is being retrieved.
 *
 * @returns {Promise<byte[]>} A promise that resolves to the byte[] file associated with the notice.
 *
 * @throws {APIException} Throws an error if the request fails or the server responds with an error.
 */
export async function findNoticeFile(id) {
  const token = Cookies.get("token");

  return await request("GET", `notices/${id}/file`, { token });
}

/**
 * Edit notice with the provided details.
 *
 * @param {Object} notice - The data for the new notice.
 * @param {number} notice.id - The id of the update notice.
 * @param {string} notice.title - The title of the notice.
 * @param {string} notice.description - A detailed description of the notice.
 * @param {string[]} notice.requirements - A list of requirements associated with the notice.
 * @param {Date} notice.date - The date associated with the notice (e.g., publication or event date).
 * @param {string} notice.axle - The axle or category to which the notice belongs.
 * @param {string} [noticeData.file] - The base64 file of the notice.
 * @param {string} [noticeData.filename] - The name of the file.
 * 
 * @returns {Promise<Object>} A promise that resolves to the newly created notice object.
 *
 * @throws {ZodError} Throws an error if input validation fails (e.g., invalid title, description, or date).
 * @throws {APIException} Throws an error if the server fails to create the notice or responds with an error.
 */
export async function editNotice({
  id,
  title,
  description,
  requirements,
  time,
  axle,
  file,
  filename
}) {
  const token = Cookies.get("token");
  const body = noticeSchema.parse({
    title,
    description,
    requirements,
    time,
    axle,
    file,
    filename
  });

  return await request("PUT", `notices/${id}`, { body, token });
}

/**
 * Retrieves the details of a specific notice by its ID.
 *
 * @param {string} id - The unique identifier of the notice to be retrieved.
 *
 * @returns {Promise<Object>} A promise that resolves to the notice object containing its details.
 *
 * @throws {APIException} Throws an error if the request fails or the server responds with an error.
 */
export async function findNotice(id) {
  const token = Cookies.get("token");

  return await request("GET", `notices/${id}`, { token });
}

/**
 * Deletes a specific notice by its ID.
 *
 * @param {string} id - The unique identifier of the notice to be deleted.
 *
 * @returns {Promise<Object>} A promise that resolves when the notice is successfully deleted.
 *
 * @throws {APIException} Throws an error if the request fails or the server responds with an error.
 */
export async function deleteNotice(id) {
  const token = Cookies.get("token");
  try {
    return await request("DELETE", `notices/${id}`, { token });
  } catch (error) {
    if (error.status === 401) {
      message.error("Você não tem autorização para deletar esse edital");
    } else {
      message.error("Ocorreu um erro ao deletar o edital");
    }
  }
}
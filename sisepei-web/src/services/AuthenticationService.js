import { z } from "zod";
import { request } from "./api";
import Cookies from "js-cookie";

const credentialsSchema = z.object({
  email: z.string().email(),
  password: z.string(),
});

/**
 * Handles the user login process by authenticating with the provided email and password.
 *
 * @param {Object} credentials - The login credentials.
 * @param {string} credentials.email - The user's email address.
 * @param {string} credentials.password - The user's password.
 *
 * @returns {Promise<void>} A promise that resolves when the login is successful.
 *
 * @throws {ZodError} Throws an error if the input validation fails (e.g., invalid email or password format).
 * @throws {ApiException} Throws an error if authentication fails or the server responds with an error.
 */
export async function login({ email, password }) {
  const body = credentialsSchema.parse({ email, password });
  const { token } = await request("POST", "/api/auth/authenticate", { body });
  Cookies.set("token", token);
}

const userDetailsSchema = z.object({
  name: z.string(),
  email: z.string().email().endsWith("@upe.br"),
  password: z
    .string()
    .regex(/.*[0-9].*/gm)
    .regex(/.*[a-z].*/gm)
    .regex(/.*[A-Z].*/gm)
    .regex(/.*[^0-9, a-z, A-Z].*/gm),
});

/**
 * Handles the user registration process by creating a new account with the provided name, email, and password.
 *
 * @param {Object} userDetails - The registration details.
 * @param {string} userDetails.name - The user's full name.
 * @param {string} userDetails.email - The user's email address.
 * @param {string} userDetails.password - The user's password.
 *
 * @returns {Promise<void>} A promise that resolves when the registration is successful.
 *
 * @throws {ZodError} Throws an error if the input validation fails (e.g., invalid name, email, or password format).
 * @throws {APIException} Throws an error if registration fails or the server responds with an error.
 */
export async function register({ name, email, password }) {
  const body = userDetailsSchema.parse({ name, email, password });
  return await request("POST", "api/auth/register", { body });
}

import { useMutation } from "@tanstack/react-query";
import { login } from "../../../services/AuthenticationService";

export function useLogin(onSuccess) {
  const { error, isError, isPending, mutate } = useMutation({
    mutationFn: login,
    onSuccess,
  });

  return { error, isPending, isError, mutate };
}
import { useMutation } from "@tanstack/react-query";
import { register } from "../../../services/AuthenticationService";

export function useRegister(onSuccess) {
  const { error, isError, isPending, mutate } = useMutation({
    mutationFn: register,
    onSuccess,
  });

  return { error, isPending, isError, mutate };
}

import { useMutation } from "@tanstack/react-query";
import { register } from "../../../services/AuthenticationService";
import { message } from "antd";

export function useRegister(onSuccess) {
  const { error, isError, isPending, mutate } = useMutation({
    mutationFn: register,
    onSuccess,
    onError: (error) => {
      if (error.status === 409) {
        message.error("O email já está cadastrado!");
      } else {
        message.error("Ocorreu um erro ao realizar o cadastro. Tente novamente.");
      }
    },
  });

  return { error, isPending, isError, mutate };
}

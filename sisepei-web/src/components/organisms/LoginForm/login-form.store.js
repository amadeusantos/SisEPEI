import { message } from "antd";
import { useMutation } from "@tanstack/react-query";
import { login } from "../../../services/AuthenticationService";

export function useLogin(onSuccess) {
  const { error, isError, isPending, mutate } = useMutation({
    mutationFn: login,
    onSuccess: (data) => {
      message.success("Login realizado com sucesso!");
      onSuccess(data);
    },
    onError: (error) => {
      message.error(
        error.response?.data?.message || 
        "Ocorreu um erro ao tentar realizar o login. Verifique suas credenciais e tente novamente."
      );
    },
  });

  return { error, isPending, isError, mutate };
}

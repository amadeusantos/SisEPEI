import { useMutation } from "@tanstack/react-query";
import { createNotice } from "../../../services/NoticeService";
import { message } from "antd";

export function useCreateNotice({ onSuccess, onError } = {}) {
  return useMutation({
    mutationFn: createNotice,
    onSuccess: (data, variables) => {
      message.success(`Edital ${variables.title} criado com sucesso!`);
      onSuccess(data);
    },
    onError: (error) => {
      message.error("Ocorreu um erro ao tentar criar edital!");
      onError(error);
    },
  });
}

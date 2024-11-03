import { useMutation } from "@tanstack/react-query";
import { createNotice } from "../../../services/NoticeService";

export function useCreateNotice({ onSuccess, onError } = {}) {
  return useMutation({
    mutationFn: createNotice,
    onSuccess,
    onError,
  });
}

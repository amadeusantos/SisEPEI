import { useMutation } from "@tanstack/react-query";
import { createNotice } from "../../../services/NoticeService";

export function useCreateNotice({ onSuccess, onError } = {}) {
    const { error, isError, isPending, mutate } = useMutation({
      mutationFn: createNotice,
      onSuccess,
      onError,
    });
  
    return { error, isError, isPending, mutate };
  }
  
import { useMutation, useQuery } from "@tanstack/react-query";
import { editNotice, findNotice } from "../../../services/NoticeService";

export function useFindNotice(id) {
  return useQuery({
    queryKey: ["notice", id],
    queryFn: () => findNotice(id),
    enabled: !!id,
  });
}

export function useEditNotice({ onSuccess, onError } = {}) {
  const { error, isError, isPending, mutate } = useMutation({
    mutationFn: editNotice,
    onSuccess,
    onError,
  });

  return { error, isError, isPending, mutate };
}

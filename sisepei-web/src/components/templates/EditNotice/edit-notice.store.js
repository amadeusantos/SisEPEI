import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useNavigate, useParams } from "react-router-dom";

import { editNotice, findNotice } from "../../../services/NoticeService";

export function useNotice(id) {
  return useQuery({
    queryKey: ["notice", id],
    queryFn: () => findNotice(id),
    enabled: !!id,
  });
}

export function useCurrentNotice() {
  const { id } = useParams();

  return useNotice(id)
}

export function useEditNotice() {
  const queryClient = useQueryClient();
  const navigate = useNavigate();

  return useMutation({
    mutationFn: editNotice,
    onSuccess: (variables) => {
      queryClient.invalidateQueries(["listNotices"]);
      queryClient.invalidateQueries(["notice", `${variables.id}`]);
      navigate("/");
    },
    onError: () => {
      alert("Ocorreu algum erro!");
    }
  });
}

import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useNavigate, useParams } from "react-router-dom";
import { message } from "antd";

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
  return useNotice(id);
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
    onError: (error) => {
      
      if (error.status === 401) {
        message.error("Você não tem autorização para modificar esse edital");
        navigate("/login"); 
      } else {
        message.error("Um erro inesperado ocorreu");
      }
    },
  });
}

import { useQuery } from "@tanstack/react-query";
import { listNotices } from "../../../services/NoticeService";

export function useNoticeList() {
  const { data, error, isLoading, isError } = useQuery({
    queryKey: ["listNotices"],
    queryFn: listNotices,
    gcTime: 15 * 1000,
  });

  return { data, error, isLoading, isError };
}

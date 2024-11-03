import { useQuery } from "@tanstack/react-query";
import { listNotices } from "../../../services/NoticeService";

export function useNoticeList() {
  return useQuery({
    queryKey: ["listNotices"],
    queryFn: listNotices,
    retry: false,
  });
}

export function useFilteredNoticeList(filter, order) {
  const queryNoticeList = useNoticeList();

  const filteredCards = queryNoticeList.data
    ?.filter(
      (card) =>
        card.title.toLowerCase().includes(filter.toLowerCase()) ||
        card.axle.toLowerCase().includes(filter.toLowerCase())
    )
    .sort((a, b) => {
      switch (order) {
        case "asc":
          return new Date(a.time).getTime() < new Date(b.time).getTime()
            ? -1
            : 1;
        case "desc":
          return new Date(a.time).getTime() > new Date(b.time).getTime()
            ? -1
            : 1;
        default:
          return 0;
      }
    });

  return { ...queryNoticeList, data: filteredCards }
}

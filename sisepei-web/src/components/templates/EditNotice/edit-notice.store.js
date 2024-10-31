import { useQuery } from "@tanstack/react-query";
import { findNotice } from "../../../services/NoticeService"

export function useFindNotice(id) {
    return useQuery({
        queryKey: ['notice', id],
        queryFn: () => findNotice(id),
        enabled: !!id
    })
}
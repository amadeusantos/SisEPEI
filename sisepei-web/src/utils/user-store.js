import { useQuery } from "@tanstack/react-query";
import { userMe } from "../services/UserService";

export function getUser() {
    return useQuery({
        queryKey: ['userMe'],
        queryFn: userMe,
        refetchInterval: 15 * 60 * 1000,
        staleTime: 15 * 60 * 1000,
      });
}
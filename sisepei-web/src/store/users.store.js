import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";

import { getUsers, getWhoami } from "../services/UserService";

export function useUsers() {
    return useQuery({
        queryKey: ['users'],
        queryFn: getUsers
    })
}

export function useLogout() {
    const navigate = useNavigate()
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: () => {
            Cookies.remove('token');
            navigate('/login');
        },
        onSuccess() {
            queryClient.invalidateQueries();
        }
    })
}

export function useWhoami() {
    return useQuery({
        queryKey: ['whoami'],
        queryFn: getWhoami,
        refetchInterval: 15 * 60 * 1000,
        staleTime: 15 * 60 * 1000,
    });
}

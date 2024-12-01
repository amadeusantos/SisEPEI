import { useMemo } from "react";
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

function createData(email, isExtensionCoordinator, isResearchCoordinator, isInovationCoordinator, isAdmin) {
    return { email, isExtensionCoordinator, isResearchCoordinator, isInovationCoordinator, isAdmin };
}

function mountUserRoles(user) {
    const userProfiles = user.profiles
    const roles = {
        isExtensionCoordinator: false,
        isResearchCoordinator: false,
        isInovationCoordinator: false,
        isAdmin: false
    }

    for (const profile of userProfiles) {
        if (profile.name == 'COORDENADOR_EXTENSAO') {
            roles.isExtensionCoordinator = true
        }
        if (profile.name == 'ADMINISTRADOR') {
            roles.isAdmin = true
        }
        if (profile.name == 'COORDENADOR_PESQUISA') {
            roles.isResearchCoordinator = true
        }
        if (profile.name == 'COORDENADOR_INOVACAO') {
            roles.isInovationCoordinator = true
        }
    }

    return roles
}

export function useUsersPermissionsRows() {
    const queryUsers = useUsers();
    const rows = useMemo(() => queryUsers.data?.map(user => {
        const roles = mountUserRoles(user)

        return createData(
            user.email,
            roles.isExtensionCoordinator,
            roles.isResearchCoordinator,
            roles.isInovationCoordinator,
            roles.isAdmin,
        )
    }), [queryUsers.data]);

    return { ...queryUsers, data: rows }
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

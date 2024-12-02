import { useWhoami } from "../../../store/users.store";
import { AdminMenu } from "./AdminMenu";
import { BaseMenu } from "./BaseMenu";

export function Menu() {
    const { data: user, isLoading } = useWhoami();
    const isCurrentUserAdmin = user?.profiles?.some(profile => profile.name === 'ADMINISTRADOR');

    if (isLoading) {
        return null;
    }

    if (isCurrentUserAdmin) {
        return <AdminMenu />
    }

    return <BaseMenu />
}

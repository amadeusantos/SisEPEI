import { Button } from "antd";
import { useNavigate } from "react-router-dom";

import { BaseMenu } from "./BaseMenu";

export function AdminMenu() {
    const navigate = useNavigate();

    function handleNavigateToPermissionsManage() {
        navigate('/admin/permissions')
    }

    const items = [
        {
            key: 'admin-permissions',
            label: (
                <Button type="button" onClick={handleNavigateToPermissionsManage}>
                    <span>admin</span>
                </Button>
            )
        }
    ]

    return <BaseMenu items={items} />
}

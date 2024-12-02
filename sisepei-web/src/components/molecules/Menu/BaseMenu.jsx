import { Button, Dropdown } from "antd";
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

import { useLogout } from "../../../store/users.store";

export function BaseMenu({ items = [] }) {
    const { mutate } = useLogout();

    return (
        <Dropdown
            menu={{
                items: [...items, {
                    key: 'logout',
                    label: (
                        <Button type="button" onClick={mutate}>
                            <span>logout</span>
                        </Button>
                    )
                }]
            }}
            placement="bottomRight"
        >
            <Button size="large"><AccountCircleIcon fontSize="large" /></Button>
        </Dropdown>
    )
}
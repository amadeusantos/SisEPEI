import * as React from 'react';
import { useNavigate } from 'react-router-dom';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Switch } from '@mui/material';
import { IconButton } from '@mui/material';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';

import "./index.css"
import { useProfiles } from '../../../store/profiles.store';
import { useUsersPermissionsRows, useEditUserProfiles } from '../../../store/users.store';
import { Loading } from '../../atoms/Loading';
import { ROLES_MAP } from '../../../utils/enums';

export function PermissionsPage() {
    const { data: profiles, isLoading: isLoadingProfiles } = useProfiles();
    const { data: rows, isLoading: isLoadingUsers } = useUsersPermissionsRows();
    const isLoading = isLoadingProfiles || isLoadingUsers;
    const navigate = useNavigate();
    const { mutateAsync } = useEditUserProfiles();

    function handleGoBack() {
        navigate('/')
    }

    function handleUpdatePermission(user, role) {
        const roleId = profiles?.find(profile => profile.name === role)?.id;
        const userRoleIds = user.profiles.map(profile => profile.id);
        const hasUserRoleId = userRoleIds.includes(roleId);

        if (hasUserRoleId) {
            mutateAsync({ id: user.id, profileIds: userRoleIds.filter((id) => id !== roleId) })
        } else {
            mutateAsync({ id: user.id, profileIds: [...userRoleIds, roleId] })
        }
    }

    if (isLoading) {
        return <Loading />
    }

    return (
        <div className='wrapper'>
            <div className='back-container'>
                <IconButton size='large' onClick={handleGoBack} aria-label="Fechar">
                    <ArrowBackIcon fontSize='large' />
                </IconButton>

                <h1>Gestão de permissões</h1>
            </div>

            <br />

            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 650 }} aria-label="caption table">
                    <caption>Por aqui você pode alterar as permissões dos usuários.</caption>
                    <TableHead>
                        <TableRow>
                            <TableCell>Email do usuário</TableCell>

                            {profiles?.map(profile => (
                                <TableCell key={profile.id} align="right">{ROLES_MAP[profile.name]}</TableCell>
                            ))}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {rows?.map((row) => (
                            <TableRow key={row.email}>
                                <TableCell component="th" scope="row">
                                    {row.email}
                                </TableCell>
                                <TableCell align="right">
                                    <Switch
                                        defaultChecked={row.isAdmin}
                                        onChange={() => {
                                            handleUpdatePermission(row.user, 'ADMINISTRADOR')
                                        }}
                                    />
                                </TableCell>
                                <TableCell align="right">
                                    <Switch
                                        defaultChecked={row.isExtensionCoordinator}
                                        onChange={() => {
                                            handleUpdatePermission(row.user, 'COORDENADOR_EXTENSAO')
                                        }}
                                    />
                                </TableCell>
                                <TableCell align="right">
                                    <Switch
                                        defaultChecked={row.isInovationCoordinator}
                                        onChange={() => {
                                            handleUpdatePermission(row.user, 'COORDENADOR_INOVACAO')
                                        }}
                                    />
                                </TableCell>
                                <TableCell align="right">
                                    <Switch
                                        defaultChecked={row.isResearchCoordinator}
                                        onChange={() => {
                                            handleUpdatePermission(row.user, 'COORDENADOR_PESQUISA')
                                        }}
                                    />
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}

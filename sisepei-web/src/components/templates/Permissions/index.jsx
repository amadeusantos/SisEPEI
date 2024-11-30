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
import { useUsers } from '../../../store/users.store';
import { Loading } from '../../atoms/Loading';

function createData(email, isExtensionCoordinator, isResearchCoordinator, isInovationCoordinator, isAdmin) {
    return { email, isExtensionCoordinator, isResearchCoordinator, isInovationCoordinator, isAdmin };
}

const rows = [
    createData('jurandir@upe.br', true, true, true, true),
    createData('rafael.joses@upe.br', true, true, false, true),
    createData('pedro.souza@upe.br', false, true, true, true),
    createData('amadeu.santos@upe.br', false, false, false, false),
    createData('guilherme.alencar@upe.br', true, false, false, true),
];

const ROLES_MAP = {
    ADMINISTRADOR: 'Administrador Geral',
    COORDENADOR_EXTENSAO: 'Coordenador de Extensão',
    COORDENADOR_INOVACAO: 'Coordenador de Inovação',
    COORDENADOR_PESQUISA: 'Coordenador de Pesquisa'
}

export function PermissionsPage() {
    const { data: profiles, isLoading: isLoadingProfiles } = useProfiles();
    const { data: users, isLoading: isLoadingUsers } = useUsers();
    const isLoading = isLoadingProfiles || isLoadingUsers;
    const navigate = useNavigate();

    console.log(users)

    function handleGoBack() {
        navigate('/')
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
                        {rows.map((row) => (
                            <TableRow key={row.email}>
                                <TableCell component="th" scope="row">
                                    {row.email}
                                </TableCell>
                                <TableCell align="right">
                                    <Switch defaultChecked={row.isExtensionCoordinator} />
                                </TableCell>
                                <TableCell align="right">
                                    <Switch defaultChecked={row.isResearchCoordinator} />
                                </TableCell>
                                <TableCell align="right">
                                    <Switch defaultChecked={row.isInovationCoordinator} />
                                </TableCell>
                                <TableCell align="right">
                                    <Switch defaultChecked={row.isAdmin} />
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}

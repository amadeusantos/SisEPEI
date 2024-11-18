import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Switch } from '@mui/material';
import "./index.css"

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

export function PermissionsPage() {
    return (
        <div className='wrapper'>
            <h1>Gestão de permissões</h1>
            <hr />
            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 650 }} aria-label="caption table">
                    <caption>Por aqui você pode alterar as permissões dos usuários.</caption>
                    <TableHead>
                        <TableRow>
                            <TableCell>Email do usuário</TableCell>
                            <TableCell align="right">Coordenador de Extensão</TableCell>
                            <TableCell align="right">Coordenador de Pesquisa</TableCell>
                            <TableCell align="right">Coordenador de Inovação</TableCell>
                            <TableCell align="right">Administrador Geral</TableCell>
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

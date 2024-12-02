import { screen, fireEvent, cleanup } from '@testing-library/react';
import { describe, it, expect, afterEach } from 'vitest';
import { render } from '../../../test-utils';
import { NoticeModal } from './index';

describe('<NoticeModal />', () => {
    afterEach(() => {
        cleanup();
    });

    const mockProps = {
        id: 1,
        closeModal: vi.fn(),
        name: "Exemplo de Edital",
        type: "Tipo de Edital",
        description: "Descrição detalhada do edital",
        term: "31/12/2024",
        requirements: "Requisitos necessários",
        coordinator: "Coordenador Nome",
        filename: "edital.pdf"
    };

    it('should be able to render title and coordinator', () => {
        render(<NoticeModal {...mockProps} />);

        expect(screen.getByText("Título:")).toBeInTheDocument();
        expect(screen.getByText("Exemplo de Edital")).toBeInTheDocument();
        expect(screen.getByText("Coordenador:")).toBeInTheDocument();
        expect(screen.getByText("Coordenador Nome")).toBeInTheDocument();
    });

    it('should be able to render basic info', () => {
        render(<NoticeModal {...mockProps} />);

        expect(screen.getByText("Tipo:")).toBeInTheDocument();
        expect(screen.getByText("Tipo de Edital")).toBeInTheDocument();
        expect(screen.getByText("Prazo:")).toBeInTheDocument();
        expect(screen.getByText("31/12/2024")).toBeInTheDocument();
        expect(screen.getByText("Requisitos:")).toBeInTheDocument();
        expect(screen.getByText("Requisitos necessários")).toBeInTheDocument();
    });

    it('should be able to render description', () => {
        render(<NoticeModal {...mockProps} />);

        expect(screen.getByText(mockProps.description.substring(0, 255))).toBeInTheDocument();
    });

    it('should be able to calls closeModal function', () => {
        render(<NoticeModal {...mockProps} />);

        const closeButton = screen.getByText("Fechar");
        fireEvent.click(closeButton);

        expect(mockProps.closeModal).toHaveBeenCalledWith(false);
    });
});

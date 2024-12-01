import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { afterEach, describe, it, expect } from 'vitest';
import { BrowserRouter } from 'react-router-dom';
import LoginForm from './index';
import { useLogin } from './login-form.store';

vi.mock('./login-form.store', () => ({
    useLogin: vi.fn(() => ({
        mutate: vi.fn(),
    })),
}));

const renderWithRouter = (component) => {
    return render(<BrowserRouter>{component}</BrowserRouter>);
};

describe('<LoginForm />', () => {
    afterEach(() => {
        vi.clearAllMocks();
    });

    it('should be able to render the login form', () => {
        renderWithRouter(<LoginForm />);

        expect(screen.getByText('Login')).toBeInTheDocument();
        expect(screen.getByPlaceholderText('Digite aqui seu email')).toBeInTheDocument();
        expect(screen.getByPlaceholderText('Digite aqui sua senha')).toBeInTheDocument();
        expect(screen.getByRole('button', { name: 'Entrar' })).toBeInTheDocument();
        expect(screen.getByRole('button', { name: 'Cadastre-se' })).toBeInTheDocument();
    });

    it('should be able to fill in the email and password fields', async () => {
        renderWithRouter(<LoginForm />);

        const emailInput = screen.getByPlaceholderText('Digite aqui seu email');
        const passwordInput = screen.getByPlaceholderText('Digite aqui sua senha');

        fireEvent.change(emailInput, { target: { value: 'test@example.com' } });
        fireEvent.change(passwordInput, { target: { value: 'password123' } });

        expect(emailInput.value).toBe('test@example.com');
        expect(passwordInput.value).toBe('password123');
    });

    it('should be able to submit the form', async () => {
        const mutate = vi.fn();
        useLogin.mockReturnValue({ mutate });

        renderWithRouter(<LoginForm />);

        fireEvent.change(screen.getByPlaceholderText('Digite aqui seu email'), { target: { value: 'test@example.com' } });
        fireEvent.change(screen.getByPlaceholderText('Digite aqui sua senha'), { target: { value: 'password123' } });

        fireEvent.submit(screen.getByRole('form'));

        await waitFor(() => expect(mutate).toHaveBeenCalledWith({ email: 'test@example.com', password: 'password123' }));
    });

    it('should be able to navigate to the register page', () => {
        renderWithRouter(<LoginForm />);

        const registerButton = screen.getByRole('button', { name: 'Cadastre-se' });
        fireEvent.click(registerButton);

        expect(window.location.pathname).toBe('/register');
    });
});

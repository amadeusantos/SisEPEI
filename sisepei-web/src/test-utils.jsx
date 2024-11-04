import React from 'react'
import { render } from '@testing-library/react'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { BrowserRouter } from 'react-router-dom';

const queryClient = new QueryClient();

const AllTheProviders = ({ children }) => {
    return (
        <BrowserRouter>
            <QueryClientProvider client={queryClient} >
                {children}
            </QueryClientProvider>
        </BrowserRouter>
    )
}

const customRender = (ui, options) =>
    render(ui, { wrapper: AllTheProviders, ...options })

// re-export everything
export * from '@testing-library/react'

// override render method
export { customRender as render }

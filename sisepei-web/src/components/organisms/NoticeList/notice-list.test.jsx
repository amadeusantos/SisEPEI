import { screen, cleanup } from '@testing-library/react';
import { beforeAll, afterEach, afterAll, describe, it, expect } from 'vitest';
import { http, HttpResponse } from 'msw';
import { setupServer } from 'msw/node';
import '@testing-library/jest-dom/vitest';

import { render } from '../../../test-utils';
import NoticeList from './index';
import { notices } from './list.mock';

const server = setupServer(
    http.get('http://localhost:8080/notices', () => {
        return HttpResponse.json(notices);
    }),
);

beforeAll(() => server.listen());
afterAll(() => server.close());

describe('<NoticeList />', () => {
    it('should render the notices', async () => {
        render(<NoticeList />);

        const cards = await screen.findAllByRole('notice');

        expect(cards.length).toBe(3);
    });
});

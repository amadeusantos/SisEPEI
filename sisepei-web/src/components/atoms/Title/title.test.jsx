import { screen } from '@testing-library/react'

import { render } from '../../../test-utils'
import Title from './index'

test("render's children of field", async () => {
    render(<Title>Filho</Title>)

    const paragraph = screen.getByText(/Filho/)

    expect(paragraph).toBeInTheDocument()
})

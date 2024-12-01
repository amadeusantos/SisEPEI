import { screen } from '@testing-library/react'

import { render } from '../../../test-utils'
import Field from './index'

test("render's children of field", async () => {
    render(<Field><p>Filho</p></Field>)

    const paragraph = screen.getByText(/Filho/)

    expect(paragraph).toBeInTheDocument()
})

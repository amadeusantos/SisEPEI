import { screen } from '@testing-library/react'

import { render } from '../../../test-utils'
import SubTitle from './index'

test("render's children of field", async () => {
    render(<SubTitle>Filho</SubTitle>)

    const paragraph = screen.getByText(/Filho/)

    expect(paragraph).toBeInTheDocument()
})

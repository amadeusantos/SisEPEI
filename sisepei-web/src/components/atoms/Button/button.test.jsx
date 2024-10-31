import { screen, fireEvent } from '@testing-library/react'
import { vitest } from 'vitest'

import { render } from '../../../test-utils'
import Button from './index'

test('calls onClick prop when clicked', async () => {
    const handleClick = vitest.fn()

    render(<Button onClick={handleClick}>Clique aqui!</Button>)

    const button = screen.getByText(/Clique aqui!/)

    fireEvent.click(button)

    expect(handleClick).toHaveBeenCalledTimes(1)
})

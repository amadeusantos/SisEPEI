import { render, screen, fireEvent, cleanup } from "@testing-library/react";
import { vitest, afterEach } from "vitest";
import '@testing-library/jest-dom/vitest'

import Input from "./index";

afterEach(() => {
    cleanup()
})

describe("Input component", () => {
    test("renders with correct placeholder", () => {
        render(<Input placeholder="Digite aqui" value="" onChange={() => { }} />);

        const input = screen.getByPlaceholderText("Digite aqui")

        expect(input).toBeInTheDocument();
    });

    test("renders with initial value", () => {
        render(<Input value="texto inicial" onChange={() => { }} />);

        const input = screen.getByDisplayValue("texto inicial");

        expect(input).toBeInTheDocument();
    });

    test("call onChange after change value", () => {
        const handleChange = vitest.fn();

        render(<Input value="" onChange={handleChange} />);

        const input = screen.getByRole("textbox");

        fireEvent.change(input, { target: { value: "novo valor" } });

        expect(handleChange).toHaveBeenCalledWith("novo valor");
    });
});

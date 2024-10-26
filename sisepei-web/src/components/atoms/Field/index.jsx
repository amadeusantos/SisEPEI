import Form from "react-bootstrap/Form";

/**
 * A form field component with a label and customizable properties.
 *
 * @param {Object} props - The properties object.
 * @param {string} props.label - The label text associated with the field.
 * @param {string} props.name - The name attribute for the field.
 * @param {React.ReactNode} props.children - The content or elements to be displayed inside the field.
 *
 * @returns {React.FC} The rendered field component.
 */
export function Field({ label, name, children }) {
  return (
    <Form.Group className="mb-3" controlId={name}>
      <Form.Label>{label}:</Form.Label>
      {children}
    </Form.Group>
  );
}

export default Field;

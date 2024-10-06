import Form from "react-bootstrap/Form";

/**
 * A form input component with a label and customizable properties.
 * 
 * @param {Object} props - The properties object.
 * @param {string} props.label - The label text associated with the input field.
 * @param {boolean} [props.required=false] - Whether the input is required. Default is `false`.
 * @param {string} [props.placeholder=''] - The placeholder text for the input field. Default is an empty string.
 * @param {string} props.name - The name attribute for the input field.
 * @param {string} props.value - The current value of the input field.
 * @param {Function} props.onChange - The function to handle changes in the input value.
 * @param {string} [props.type='text'] - The type of input field (e.g., text, password, email). Default is 'text'.
 * 
 * @returns {React.FC} The rendered input field component.
 */
export function InputText({
  label,
  required,
  placeholder,
  name,
  value,
  onChange,
  type = "text",
}) {
  const setValue = (e) => onChange(e.target.value);

  return (
    <Form.Group className="mb-3" controlId={name}>
      <Form.Label>{label}:</Form.Label>
      <Form.Control
        type={type}
        name={label}
        required={required}
        placeholder={placeholder}
        value={value}
        onChange={setValue}
      />
    </Form.Group>
  );
}

export default InputText;

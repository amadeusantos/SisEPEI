import Form from "react-bootstrap/Form";

export function InputText({
  label,
  required,
  placeholder,
  name,
  value,
  onChange,
  type = "text",
  isForm = false,
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

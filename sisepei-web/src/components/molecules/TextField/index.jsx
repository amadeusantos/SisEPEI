import { Input as InputAntd } from "antd";
import Field from "../../atoms/Field";

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
 * @param {string} [props.as] - Specifies if the field should render as a "textarea" or "input" (defaults to "input").
 * @param {number} [props.rows] - The number of rows for the textarea, if the component is rendered as a textarea.
 * @param {string} [props.type='text'] - The type of input field (e.g., text, password, email). Default is 'text'.
 * @param {import("antd/es/form").Rule[]} [props.rules]
 *
 * @returns {React.FC} The rendered input field component.
 */
export function TextField({
  label,
  required,
  placeholder,
  name,
  value,
  onChange,
  rules,
  rows,
  type = "",
}) {
  const Input = InputAntd[type] || InputAntd;
  const setValue = (e) => onChange(e.target.value);

  return (
    <Field label={label} name={name} rules={rules} required={required}>
      <Input
        size="large"
        placeholder={placeholder}
        value={value}
        onChange={setValue}
        required={required}
        rows={rows}
      />
    </Field>
  );
}

export default TextField;

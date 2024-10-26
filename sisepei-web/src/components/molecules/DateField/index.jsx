import { Field } from "../../atoms";
import "./style.css";

/**
 * A form input date component with a label and customizable properties.
 *
 * @param {Object} props - The properties object.
 * @param {string} props.label - The label text associated with the input date field.
 * @param {boolean} [props.required=false] - Whether the input date is required. Default is `false`.
 * @param {string} props.name - The name attribute for the input date field.
 * @param {string} props.value - The current value of the input date field.
 * @param {Function} props.onChange - The function to handle changes in the input date value.
 *
 * @returns {React.FC} The rendered input field component.
 */
export function DateField({ label, required, name, value, onChange }) {
  const setValue = (event) => onChange(event.target.value);
  return (
    <Field label={label} name={name}>
      <div className="date-input-container">
        <input
          id={name}
          value={value}
          type="date"
          className="custom-date-input"
          onChange={setValue}
          required={required}
        />
      </div>
    </Field>
  );
}

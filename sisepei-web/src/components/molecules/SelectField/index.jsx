import { Field } from "../../atoms";
import "./style.css";

/**
 * Renders a customizable select field component.
 *
 * @param {Object} props - The props for the select field component.
 * @param {string} props.label - The label text associated with the select field.
 * @param {boolean} props.required - If true, the field will be marked as required.
 * @param {string} props.name - The name attribute of the select field, used for form submission.
 * @param {Array<{ label: string, value: string }>} props.values - An array of options for the select field, where each option has a `label` and `value`.
 * @param {Function} props.onChange - Callback function to handle changes to the selected value.
 * @param {string} props.value - The currently selected value.
 *
 * @returns {React.FC} The rendered select field component.
 */
export function SelectField({ label, required, name, values, onChange, value }) {
  const setValue = (event) => onChange(event.target.value);
  return (
    <Field label={label} name={name}>
      <div className="select-container">
        <select
          id={name}
          className="custom-select"
          onChange={setValue}
          required={required}
          value={value}
        >
          {values.map((v, index) => (
            <option key={index} value={v.value}>{v.label}</option>
          ))}
        </select>
      </div>
    </Field>
  );
}

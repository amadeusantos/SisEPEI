import { Select } from "antd";
import { Field } from "../../atoms";

/**
 * Renders a customizable select field component.
 *
 * @param {Object} props - The props for the select field component.
 * @param {string} props.label - The label text associated with the select field.
 * @param {boolean} props.required - If true, the field will be marked as required.
 * @param {string} props.name - The name attribute of the select field, used for form submission.
 * @param {Array<{ label: string, value: string }>} props.options - An array of options for the select field, where each option has a `label` and `value`.
 * @param {Function} props.onChange - Callback function to handle changes to the selected value.
 * @param {import("antd/es/form").Rule[]} [props.rules]
 * @param {string} props.value - The currently selected value.
 *
 * @returns {React.FC} The rendered select field component.
 */
export function SelectField({
  label,
  required,
  name,
  options,
  onChange,
  rules,
  value,
}) {
  return (
    <Field label={label} name={name} rules={rules} required={required}>
      <Select
        size="large"
        style={{ width: "16em" }}
        required={required}
        options={options}
        value={value}
        onChange={(v) => onChange(v)}
      />
    </Field>
  );
}

import { DatePicker } from "antd";
import { Field } from "../../atoms";
import dayjs from "dayjs";

/**
 * A form input date component with a label and customizable properties.
 *
 * @param {Object} props - The properties object.
 * @param {string} props.label - The label text associated with the input date field.
 * @param {boolean} [props.required=false] - Whether the input date is required. Default is `false`.
 * @param {string} props.name - The name attribute for the input date field.
 * @param {string} props.value - The current value of the input date field.
 * @param {Function} props.onChange - The function to handle changes in the input date value.
 * @param {import("antd/es/form").Rule[]} [props.rules]
 *
 * @returns {React.FC} The rendered input field component.
 */
export function DateField({ label, required, name, value, onChange, rules }) {
  return (
    <Field label={label} name={name} rules={rules} required={required}>
      <DatePicker
        size="large"
        required={required}
        format={"DD/MM/YYYY"}
        value={dayjs(value)}
        onChange={(date) => {
          onChange(date.toDate());
        }}
      />
    </Field>
  );
}

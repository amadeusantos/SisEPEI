import { Field } from "../../atoms";
import "./style.css";

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

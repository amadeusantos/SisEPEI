import "./style.css";
/**
 * A form input component with a label and customizable properties.
 *
 * @param {Object} props - The properties object.
 * @param {string} [props.placeholder=''] - The placeholder text for the input. Default is an empty string.
 * @param {string} props.value - The current value of the input.
 * @param {Function} props.onChange - The function to handle changes in the input value.
 * @param {string} [props.type='text'] - The type of input (e.g., text, password, email). Default is 'text'.
 *
 * @returns {React.FC} The rendered input field component.
 */
export function Input({ value, type = "text", placeholder, onChange }) {
  const setValue = (e) => onChange(e.target.value);
  return (
    <div className="container-input">
      <input className="input"
        type={type}
        placeholder={placeholder}
        value={value}
        onChange={setValue}
      />
    </div>
  );
}

export default Input;

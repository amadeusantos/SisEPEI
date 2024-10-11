import ButtonBootstrap from "react-bootstrap/Button";
import "./style.css";

/**
 * A button component with customizable color, type, and click handler.
 *
 * @param {Object} props - The properties object.
 * @param {'primary'|'secondary'|'danger'} [props.color='primary'] - The color of the button. Default is 'primary'.
 * @param {'button'|'submit'|'reset'} [props.type='button'] - The type of the button. Default is 'button'.
 * @param {Function} [props.onClick=() => {}] - The function to be called when the button is clicked. Default is an empty function.
 * @param {boolean} [props.disabled=false] - If true, the button will be disabled and not clickable.
 * @param {React.ReactNode} props.children - The content or elements to be displayed inside the button.
 *
 * @returns {React.FC} The rendered button element.
 */
export function Button({
  color = "primary",
  type = "button",
  onClick = () => {},
  children,
  disabled = false,
}) {
  const className = `button ${color}`;
  return (
    <button
      disabled={disabled}
      className={className}
      variant="primary"
      type={type}
      onClick={onClick}
    >
      {children}
    </button>
  );
}

export default Button;

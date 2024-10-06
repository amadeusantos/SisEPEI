import ButtonBootstrap from "react-bootstrap/Button";
import "./style.css";

export function Button({
  color = "primary",
  type = "button",
  onClick = () => {},
  children,
}) {
  const className = `button ${color}`;
  return (
    <ButtonBootstrap
      className={className}
      variant="primary"
      type={type}
      onClick={onClick}
    >
      {children}
    </ButtonBootstrap>
  );
}

export default Button;

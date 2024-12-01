import "./style.css";

/**
 * Renders a title component.
 *
 * @param {Object} props - The props for the title component.
 * @param {JSX.Element} props.children - The content or elements to be displayed inside the title.
 *
 * @returns {React.FC} The rendered title component.
 */
export function Title({ children }) {
  return <h1 className="title">{children}</h1>;
}

export default Title;

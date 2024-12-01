import "./style.css";

/**
 * Renders a subtitle component.
 *
 * @param {Object} props - The props for the subtitle component.
 * @param {JSX.Element} props.children - The content or elements to be displayed inside the subtitle.
 *
 * @returns {React.FC} The rendered subtitle component.
 */
export function SubTitle({ children }) {
  return <h2 className="sub-title">{children}</h2>;
}

export default SubTitle;

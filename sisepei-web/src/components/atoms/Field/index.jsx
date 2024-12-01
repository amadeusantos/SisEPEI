import { Form, Tag, Typography } from "antd";

/**
 * A form field component with a label and customizable properties.
 *
 * @param {Object} props - The properties object.
 * @param {string} props.label - The label text associated with the field.
 * @param {string} props.name - The name attribute for the field.
 * @param {import("antd/es/form").Rule[]} [props.rules]
 * @param {React.ReactNode} props.children - The content or elements to be displayed inside the field.
 *
 * @returns {React.FC} The rendered field component.
 */
export function Field({ label, name, rules, required, children }) {
  return (
    <div>
      <label htmlFor={name}>
        <Typography.Title level={5}>{label}</Typography.Title>
      </label>
      <Form.Item
        name={name}
        rules={rules}
        required={required}
      >
        {children}
      </Form.Item>
    </div>
  );
}

export default Field;

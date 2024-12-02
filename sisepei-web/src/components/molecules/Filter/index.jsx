import { CaretDown, CaretUp, List, ListNumbers } from "@phosphor-icons/react";
import { Button, Input } from "../../atoms";
import "./style.css";

/**
 * Handles the filtering and sorting logic.
 *
 * @param {string} order - The current order of items, typically used for sorting (e.g., "asc" or "desc").
 * @param {Function} setOrder - Function to update the sorting order.
 * @param {Function} setFilter - Function to update the filter criteria.
 *
 * @returns {React.FC} The rendered filter component.
 */
export function Filter({ order, setOrder, setFilter }) {
  const Icon = order === "asc" ? CaretDown : order === "desc" ? CaretUp : List;

  const filterOrder = () => {
    switch (order) {
      case "asc":
        setOrder("desc");
        break;
      case "desc":
        setOrder(undefined);
        break;
      default:
        setOrder("asc");
        break;
    }
  };
  
  return (
    <div className="search-filter">
      <Input placeholder="Buscar" variant="borderless" onChange={setFilter} />
      <Button color="gray" onClick={filterOrder}>
        <Icon size={28} />
      </Button>
    </div>
  );
}

export default Filter;

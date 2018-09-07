import java.util.List;
import javax.swing.table.AbstractTableModel;


public class ItemTableModel extends AbstractTableModel{

    public static final int OBJECT_COL = -1;
    private static final int name_col = 0;
    private static final int id_col = 1;
    private static final int listPrice_col = 2;
    private static final int sellPrice_col = 3;
    private static final int quantity_col = 4;
    private static final int itemType_col = 5;
    private static final int date_col = 6;

    private String columnNames[] = {"Name", "id", "List price", "Sell price", "Quantity", "Item type", "Date added"};
    private List<item> items;
    public ItemTableModel(List<item> theitems){
        items = theitems;
    }
    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    public String getColumnName(int column){
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        item tempItem = items.get(rowIndex);

        switch (columnIndex){
            case name_col:
                return tempItem.getName();
            case id_col:
                return tempItem.getId();
            case listPrice_col:
                return tempItem.getListPrice();
            case sellPrice_col:
                return tempItem.getSellPrice();
            case quantity_col:
                return tempItem.getQuantity();
            case itemType_col:
                return tempItem.getTypeOfItem();
            case date_col:
                return tempItem.getDateAdded();
            case OBJECT_COL:
                return tempItem;
            default:
                return tempItem.getName();
        }
    }
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

}

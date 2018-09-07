import jdk.nashorn.internal.scripts.JO;
import sun.reflect.annotation.ExceptionProxy;
import sun.util.resources.cldr.ebu.CurrencyNames_ebu;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.JTable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;

public class home extends JFrame {
    private CurrentDAO currDAO;
    String itemStrings[] = {"Racquets", "Shoes", "Glasses", "Grips", "Clothing", "Miscellaneous"};
    //Home UI
    BorderLayout borderLayout = new BorderLayout();
    JPanel container = new JPanel();
    JPanel panelLeft = new JPanel();
    JPanel panelRight = new JPanel();
    public JPanel upperContainer = new JPanel();
    JPanel lowerContainer = new JPanel();
    JPanel buttonContainer = new JPanel();
    JPanel lowerBoxLayout = new JPanel();
    JButton newButton = new JButton("New");
    JButton changeButton = new JButton("Change");
    JButton purchaseButton = new JButton("Purchase");
    JButton deleteButton = new JButton("Delete");
    //sort info combo box
    String[] sortString = {"Current", "History", "Purchases", "Profit"};
    JComboBox sortInfo = new JComboBox(sortString);
    JPanel dropdownMenuPanel = new JPanel();
    JLabel infoLabel = new JLabel("Sort inventory by data: ");
    //sort date combo box
    JPanel dateSortPanel = new JPanel();
    JComboBox dateChooser = new JComboBox();
    JLabel dateLabel = new JLabel("Sort items by:                 ");
    //year combo box
    JComboBox yearChooser = new JComboBox();
    //month combo box
    JComboBox monthChooser = new JComboBox();

    //on new button click
    JPanel newButtonActionContainer = new JPanel();
    JPanel itemNamePanel = new JPanel();
    JPanel purchasePanel = new JPanel();
    JPanel sellPanel = new JPanel();
    JPanel quantityPanel = new JPanel();
    JTextField itemName = new JTextField(20);
    JTextField purchasePrice = new JTextField(20);
    JTextField sellPrice = new JTextField(20);
    JTextField quantity = new JTextField(20);
    JLabel itemNameLabel = new JLabel("Item name:        ");
    JLabel purchaseLabel = new JLabel("Purchase price: ");
    JLabel sellLabel = new JLabel("Retail price:        ");
    JLabel quantityLabel = new JLabel("Quantity of item: ");
    JComboBox itemTypeBox = new JComboBox(itemStrings);
    JPanel itemTypePanel = new JPanel();
    JButton confirmNew = new JButton("Click to add");
    JPanel addPanel = new JPanel();

    //on change button click
    JPanel changeButtonActionContainer = new JPanel();
    JPanel itemNamePanel1 = new JPanel();
    JPanel purchasePanel1 = new JPanel();
    JPanel sellPanel1 = new JPanel();
    JPanel quantityPanel1 = new JPanel();
    JTextField itemName1 = new JTextField(20);
    JTextField purchasePrice1 = new JTextField(20);
    JTextField sellPrice1 = new JTextField(20);
    JTextField quantity1 = new JTextField(20);
    JLabel itemNameLabel1 = new JLabel("Item name:         ");
    JLabel purchaseLabel1 = new JLabel("Purchase price: ");
    JLabel sellLabel1 = new JLabel("Retail price:        ");
    JLabel quantityLabel1 = new JLabel("Quantity of item: ");
    JButton confirmChangeButton = new JButton("Click to change");
    JPanel confirmPanel = new JPanel();
    JComboBox itemTypeBox2 = new JComboBox(itemStrings);

    JLabel racquetLabel = new JLabel("Racquets");
    JLabel shoeLabel = new JLabel("Shoes");
    JLabel glassesLabel = new JLabel("Glasses");
    JLabel gripsLabel = new JLabel("Grips");
    JLabel clothingLabel = new JLabel("Clothing");
    JLabel miscLabel = new JLabel("Miscellaneous");
    JPanel itemTypePanel2 = new JPanel();


    //on purchase button click
    JPanel purchaseBoxLayout = new JPanel();
    JPanel spinnerPanel = new JPanel();
    JSpinner quantitySpinner = new JSpinner(); // need to set default to the current quantity
    JLabel quantityLabel2 = new JLabel("Quantity of item: ");
    JButton confirmPurchaseButton = new JButton("Click to purchase");
    JPanel confirmPurchase = new JPanel();
    //right panel
    JLabel typeItem = new JLabel("Enter item name: ");
    JTextField searchTextbox = new JTextField();
    JButton searchButton = new JButton("Search");
    JPanel searchPanel = new JPanel();
    JPanel tablePanel = new JPanel();
    JTable table = new JTable();
    JScrollPane scrollpane = new JScrollPane(table);
    //filter checkboxes
    ButtonGroup group = new ButtonGroup();
    JPanel checkBoxPanel = new JPanel();
    JRadioButton allBox = new JRadioButton("All");
    JRadioButton racquetBox = new JRadioButton("Racquets");
    JRadioButton shoesBox = new JRadioButton("Shoes");
    JRadioButton glassesBox = new JRadioButton("Glasses");
    JRadioButton gripsBox = new JRadioButton("Grips");
    JRadioButton clothingBox = new JRadioButton("Clothing");
    JRadioButton miscBox = new JRadioButton("Miscellaneous");
    //set visible booleans
    Boolean newBoolean = false;
    Boolean changeBoolean = false;
    Boolean purchaseBoolean = false;
    Boolean trigger = false;
    Boolean bool = true;


    public void createAndShowGUI() throws Exception {
        currDAO = new CurrentDAO();
        final int Frame_Width = 1280;
        final int Frame_Height = 720;
        int countA = 0;
        int countB = 0;

        JFrame newFrame = new JFrame("Inventrack");
        //set layouts of major panels
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
        panelRight.setLayout(new BorderLayout());
        // panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
        upperContainer.setLayout(new BoxLayout(upperContainer, BoxLayout.Y_AXIS));
        lowerContainer.setLayout(new GridLayout(4, 1));
        buttonContainer.setLayout(new FlowLayout());
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));

        panelLeft.add(upperContainer);
        panelLeft.add(lowerContainer);
        upperContainer.add(buttonContainer);

        //container.setLayout(new GridLayout(1, 2));
        container.setLayout(new BorderLayout());
        container.add(panelLeft, BorderLayout.WEST);
        container.add(panelRight, BorderLayout.CENTER);
        newFrame.add(container);

        //new button action container

        newButtonActionContainer.setLayout(new BoxLayout(newButtonActionContainer, BoxLayout.PAGE_AXIS));
        itemNamePanel.add(itemNameLabel);
        itemNamePanel.add(itemName);
        purchasePanel.add(purchaseLabel);
        purchasePanel.add(purchasePrice);
        sellPanel.add(sellLabel);
        sellPanel.add(sellPrice);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantity);
        itemTypePanel.add(itemTypeBox);
        addPanel.add(confirmNew);
        newButtonActionContainer.add(itemNamePanel);
        newButtonActionContainer.add(purchasePanel);
        newButtonActionContainer.add(sellPanel);
        newButtonActionContainer.add(quantityPanel);
        newButtonActionContainer.add(itemTypePanel);
        newButtonActionContainer.add(addPanel);
        buttonContainer.add(newButton);
        buttonContainer.add(changeButton);
        buttonContainer.add(purchaseButton);
        buttonContainer.add(deleteButton);
        upperContainer.add(newButtonActionContainer);
        newButtonActionContainer.setVisible(false);

        //change button container
        changeButtonActionContainer.setLayout(new BoxLayout(changeButtonActionContainer, BoxLayout.PAGE_AXIS));
        upperContainer.add(changeButtonActionContainer);
        itemNamePanel1.add(itemNameLabel1);
        itemNamePanel1.add(itemName1);
        purchasePanel1.add(purchaseLabel1);
        purchasePanel1.add(purchasePrice1);
        sellPanel1.add(sellLabel1);
        sellPanel1.add(sellPrice1);
        quantityPanel1.add(quantityLabel1);
        quantityPanel1.add(quantity1);
        itemTypePanel2.add(itemTypeBox2);
        confirmPanel.add(confirmChangeButton);
        changeButtonActionContainer.add(itemNamePanel1);
        changeButtonActionContainer.add(purchasePanel1);
        changeButtonActionContainer.add(sellPanel1);
        changeButtonActionContainer.add(quantityPanel1);
        changeButtonActionContainer.add(itemTypePanel2);
        changeButtonActionContainer.add(confirmPanel);
        changeButtonActionContainer.setVisible(false);
        //purchase container
        purchaseBoxLayout.setLayout(new BoxLayout(purchaseBoxLayout, BoxLayout.Y_AXIS));
        upperContainer.add(purchaseBoxLayout);
        purchaseBoxLayout.add(spinnerPanel);
        spinnerPanel.add(quantityLabel2);
        spinnerPanel.add(quantitySpinner);
        quantitySpinner.setPreferredSize(new Dimension(50, 25));
        purchaseBoxLayout.add(confirmPurchase);
        confirmPurchase.add(confirmPurchaseButton);
        purchaseBoxLayout.setVisible(false);
        //show table
        searchPanel.add(typeItem);
        searchPanel.add(searchTextbox);
        searchPanel.add(searchButton);
        searchTextbox.setPreferredSize(new Dimension(300, 25));
        panelRight.add(scrollpane, BorderLayout.CENTER);
        scrollpane.setViewportView(table);
        //new actionlistener
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (changeBoolean = true || purchaseBoolean || true) {
                    changeButtonActionContainer.setVisible(false);
                    purchaseBoxLayout.setVisible(false);
                    changeBoolean = false;
                    purchaseBoolean = false;
                }
                if (trigger = false) {
                    newButtonActionContainer.setVisible(true);
                    newBoolean = true;
                    trigger = true;
                }
                if (trigger = true) {
                    newButtonActionContainer.setVisible(true);
                    newBoolean = true;
                }
            }

        });
        //change actionlistener
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newBoolean = true || purchaseBoolean || true) {
                    newButtonActionContainer.setVisible(false);
                    purchaseBoxLayout.setVisible(false);
                    newBoolean = false;
                    purchaseBoolean = false;
                }
                if (trigger = false) {
                    changeButtonActionContainer.setVisible(true);
                    changeBoolean = true;
                    trigger = true;
                }
                if (trigger = true) {
                    changeButtonActionContainer.setVisible(true);
                    changeBoolean = true;
                }
            }
        });
        //purchase actionlistener
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newBoolean = true || changeBoolean || true) {
                    newButtonActionContainer.setVisible(false);
                    changeButtonActionContainer.setVisible(false);
                    newBoolean = false;
                    changeBoolean = false;
                }
                if (trigger = false) {
                    purchaseBoxLayout.setVisible(true);
                    purchaseBoolean = true;
                    trigger = true;
                }
                if (trigger = true) {
                    purchaseBoxLayout.setVisible(true);
                    purchaseBoolean = true;
                }
            }
        });
        //delete action listener
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //onclick, delete the row
            }
        });
        //inventory sort combo box action listener
        sortInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selected = sortInfo.getSelectedItem();
                String itemString = (String) sortInfo.getSelectedItem();
                switch (itemString) {
                    case "Current":
                        List<item> items = null;
                        try {
                            showCurrentTable();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case "History":
                        try {
                            showHistoryTable();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case "Purchases":
                        try {
                            showPurchasesTable();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                }
            }
        });
        confirmNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
        confirmChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeItem();
            }
        });
        confirmPurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                purchase();
            }
        });
        dateChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selected = dateChooser.getSelectedItem();
                String itemString = (String) dateChooser.getSelectedItem();
                List<item> items = null;
                switch (itemString) {
                    case "Item name":
                        try {
                            showCurrentTable();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case "Ascending quantity":
                        try {

                            if (sortInfo.getSelectedItem().equals("Current")) {
                                items = currDAO.getCurrentdbAscending();
                                ItemTableModel model = new ItemTableModel(items);
                                table.setModel(model);
                            }
                            if (sortInfo.getSelectedItem().equals("History")) {
                                items = currDAO.getHistoryDBAscending();
                                ItemTableModel model = new ItemTableModel(items);
                                table.setModel(model);
                            }
                            if (sortInfo.getSelectedItem().equals("Purchases")){
                                items = currDAO.getPurchasesAscending();
                                ItemTableModel model = new ItemTableModel(items);
                                table.setModel(model);
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case "Descending quantity":
                        try {
                            if (sortInfo.getSelectedItem().equals("Current")) {
                                items = currDAO.getCurrentdbDescending();
                                ItemTableModel model = new ItemTableModel(items);
                                table.setModel(model);
                            }
                            if (sortInfo.getSelectedItem().equals("History")) {
                                items = currDAO.getHistoryDBDescending();
                                ItemTableModel model = new ItemTableModel(items);
                                table.setModel(model);

                            }
                            if (sortInfo.getSelectedItem().equals("Purchases")){
                                items = currDAO.getPurchasesDescending();
                                ItemTableModel model = new ItemTableModel(items);
                                table.setModel(model);
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String itemString = (String) sortInfo.getSelectedItem();
                String searchText = searchTextbox.getText();

                List<item> items = null;
                try {
                    if (itemString.equals("Current") && itemString != null && itemString.trim().length() > 0) {
                        items = currDAO.searchEmployeesCurrent(searchText);
                    }
                    if (itemString.equals("History") && itemString != null && itemString.trim().length() > 0) {
                        items = currDAO.searchEmployeesHistory(searchText);
                    }
                    if (itemString.equals("Purchases") && itemString != null && itemString.trim().length() > 0) {
                        items = currDAO.searchEmployeesPurchases(searchText);
                    }
                    ItemTableModel model = new ItemTableModel(items);
                    table.setModel(model);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }

            }
        });
        racquetBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<item> items = null;
                try {
                    String itemString = (String) sortInfo.getSelectedItem();
                    if (itemString.equals("Current")) {
                        items = currDAO.filterCurrent("Racquets");
                    }
                    if (itemString.equals("History")) {
                        items = currDAO.filterHistory("Racquets");
                    }
                    if (itemString.equals("Purchases")) {
                        items = currDAO.filterPurchases("Racquets");
                    }
                    ItemTableModel model = new ItemTableModel(items);
                    table.setModel(model);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }

            }
        });
        shoesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<item> items = null;
                try {
                    String itemString = (String) sortInfo.getSelectedItem();
                    if (itemString.equals("Current")) {
                        items = currDAO.filterCurrent("Shoes");
                    }
                    if (itemString.equals("History")) {
                        items = currDAO.filterHistory("Shoes");
                    }
                    if (itemString.equals("Purchases")) {
                        items = currDAO.filterPurchases("Shoes");
                    }
                    ItemTableModel model = new ItemTableModel(items);
                    table.setModel(model);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }

            }
        });
        glassesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<item> items = null;
                try {
                    String itemString = (String) sortInfo.getSelectedItem();
                    if (itemString.equals("Current")) {
                        items = currDAO.filterCurrent("Glasses");
                    }
                    if (itemString.equals("History")) {
                        items = currDAO.filterHistory("Glasses");
                    }
                    if (itemString.equals("Purchases")) {
                        items = currDAO.filterPurchases("Glasses");
                    }
                    ItemTableModel model = new ItemTableModel(items);
                    table.setModel(model);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }

            }
        });
        gripsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<item> items = null;
                try {
                    String itemString = (String) sortInfo.getSelectedItem();
                    if (itemString.equals("Current")) {
                        items = currDAO.filterCurrent("Grips");
                    }
                    if (itemString.equals("History")) {
                        items = currDAO.filterHistory("Grips");
                    }
                    if (itemString.equals("Purchases")) {
                        items = currDAO.filterPurchases("Grips");
                    }
                    ItemTableModel model = new ItemTableModel(items);
                    table.setModel(model);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }

            }
        });
        clothingBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<item> items = null;
                try {
                    String itemString = (String) sortInfo.getSelectedItem();
                    if (itemString.equals("Current")) {
                        items = currDAO.filterCurrent("Clothing");
                    }
                    if (itemString.equals("History")) {
                        items = currDAO.filterHistory("Clothing");
                    }
                    if (itemString.equals("Purchases")) {
                        items = currDAO.filterPurchases("Clothing");
                    }
                    ItemTableModel model = new ItemTableModel(items);
                    table.setModel(model);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }

            }
        });
        miscBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<item> items = null;
                try {
                    String itemString = (String) sortInfo.getSelectedItem();
                    if (itemString.equals("Current")) {
                        items = currDAO.filterCurrent("Miscellaneous");
                    }
                    if (itemString.equals("History")) {
                        items = currDAO.filterHistory("Miscellaneous");
                    }
                    if (itemString.equals("Purchases")) {
                        items = currDAO.filterPurchases("Miscellaneous");
                    }
                    ItemTableModel model = new ItemTableModel(items);
                    table.setModel(model);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }

            }
        });
        allBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<item> items = null;
                try {
                    items = currDAO.getCurrentdb();
                    ItemTableModel model = new ItemTableModel(items);
                    table.setModel(model);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }

            }
        });

        //right side
        panelRight.add(searchPanel, BorderLayout.NORTH);
        //panelRight.add(tablePanel);

        newFrame.setSize(Frame_Width, Frame_Height);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);
        CurrentDAO dao = new CurrentDAO();

    }

    public void displayLowerArea() {
        lowerContainer.add(lowerBoxLayout);
        lowerBoxLayout.setLayout(new BoxLayout(lowerBoxLayout, BoxLayout.Y_AXIS));
        dropdownMenuPanel.add(infoLabel);
        dropdownMenuPanel.add(sortInfo);
        lowerBoxLayout.add(dropdownMenuPanel);

        sortInfo.setPreferredSize(new Dimension(150, 35));

        lowerBoxLayout.add(dateSortPanel);
        lowerBoxLayout.add(checkBoxPanel);
        group.add(allBox);
        group.add(racquetBox);
        group.add(shoesBox);
        group.add(glassesBox);
        group.add(gripsBox);
        group.add(clothingBox);
        group.add(miscBox);
        checkBoxPanel.add(allBox);
        checkBoxPanel.add(racquetBox);
        checkBoxPanel.add(shoesBox);
        checkBoxPanel.add(glassesBox);
        checkBoxPanel.add(gripsBox);
        checkBoxPanel.add(clothingBox);
        checkBoxPanel.add(miscBox);
        allBox.setSelected(true);
        dateSortPanel.add(dateLabel);
        dateSortPanel.add(dateChooser);
        String[] sortDate = {"Date added", "Ascending quantity", "Descending quantity"};
        dateChooser.addItem(sortDate[0]);
        dateChooser.addItem(sortDate[1]);
        dateChooser.addItem(sortDate[2]);
        dateChooser.setPreferredSize(new Dimension(150, 35));
    }

    public void showCurrentTable() throws Exception {
        List<item> items = null;
        //CurrentDAO currDAO = new CurrentDAO();
        items = currDAO.getCurrentdb();
        ItemTableModel model = new ItemTableModel(items);
        table.setModel(model);
    }

    public void showHistoryTable() throws Exception {
        List<item> items = null;
        items = currDAO.getHistoryDB();
        ItemTableModel model = new ItemTableModel(items);
        table.setModel(model);
    }

    public void showPurchasesTable() throws Exception {
        List<item> items = null;
        items = currDAO.getPurchasesDB();
        ItemTableModel model = new ItemTableModel(items);
        table.setModel(model);
    }

    public BigDecimal convertStringToBigDecimal(String salaryStr) {

        BigDecimal result = null;

        try {
            double salaryDouble = Double.parseDouble(salaryStr);

            result = BigDecimal.valueOf(salaryDouble);
        } catch (Exception exc) {
            result = BigDecimal.valueOf(0.0);
        }

        return result;
    }

    public void addItem() {
        //get info from GUI
        String racquet = itemName.getText();
        int id = 0;
        String purchaseStr = purchasePrice.getText();
        BigDecimal purchase = convertStringToBigDecimal(purchaseStr);
        String sellStr = sellPrice.getText();
        BigDecimal sell = convertStringToBigDecimal(sellStr);
        int quant = Integer.parseInt(quantity.getText());
        String type = (String) itemTypeBox.getSelectedItem();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String date = sdf.format(timestamp);
        System.out.println(sdf.format(timestamp));

        item tempItem = new item(racquet, id, purchase, sell, quant, type, date);

        try {
            currDAO.addItem(tempItem);
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void purchase() {
        String itemString = (String) sortInfo.getSelectedItem();
        if (itemString.equals("History") || itemString.equals("Purchases")) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Purchases can only be made from the 'current' table");
            return;
        }
        int row = table.getSelectedRow();
        item oldItem = (item) table.getValueAt(row, ItemTableModel.OBJECT_COL);
        String name = oldItem.getName();
        System.out.println(name);
        int zero = 0;
        int id = oldItem.getId();
        BigDecimal listPrice = oldItem.getListPrice();
        BigDecimal sellPrice = oldItem.getSellPrice();
        int quantity = (int) quantitySpinner.getValue();
        System.out.println(quantity);
        String typeOfItem = oldItem.getTypeOfItem();
        String date = oldItem.getDateAdded();
        int newQuantity = oldItem.getQuantity() - quantity;
        System.out.println(newQuantity);
        System.out.println(id);
        item newPurchaseItem = new item(name, zero, listPrice, sellPrice, quantity, typeOfItem, date);
        item replaceCurrentItem = new item(name, id, listPrice, sellPrice, newQuantity, typeOfItem, date);
        try {
            List<item> items = null;
            currDAO.addPurchase(newPurchaseItem);
            currDAO.replaceQuantity(replaceCurrentItem);
            items = currDAO.getCurrentdb();
            ItemTableModel model = new ItemTableModel(items);
            table.setModel(model);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void changeItem() {
        int row = table.getSelectedRow();
        item oldItem = (item) table.getValueAt(row, ItemTableModel.OBJECT_COL);
        //get info from GUI
        String racquet = itemName1.getText();
        int id = oldItem.getId();
        String purchaseStr = purchasePrice1.getText();
        BigDecimal purchase = convertStringToBigDecimal(purchaseStr);
        String sellStr = sellPrice1.getText();
        BigDecimal sell = convertStringToBigDecimal(sellStr);
        int quant = Integer.parseInt(quantity1.getText());
        String type = "";
        if (newBoolean == true) {
            type = (String) itemTypeBox.getSelectedItem();
        }
        if (changeBoolean == true) {
            type = (String) itemTypeBox2.getSelectedItem();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String date = sdf.format(timestamp);
        System.out.println(sdf.format(timestamp));
        item tempItem = new item(racquet, id, purchase, sell, quant, type, date);

        try {
            List<item> items = null;
            currDAO.updateItem(tempItem);
            String itemString = (String) sortInfo.getSelectedItem();
            if (itemString.equals("Current")) {
                items = currDAO.getCurrentdb();
            }
            if (itemString.equals("History")) {
                items = currDAO.getHistoryDB();
            }
            ItemTableModel model = new ItemTableModel(items);
            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        try {
            List<item> items = null;

            if (newBoolean == true) {
                items = currDAO.getCurrentdb();
            }
            if (changeBoolean == true) {
                items = currDAO.getHistoryDB();
            }
            // update
            ItemTableModel model = new ItemTableModel(items);
            table.setModel(model);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        home newHome = new home();
        newHome.createAndShowGUI();
        newHome.displayLowerArea();
        newHome.showCurrentTable();

    }

}

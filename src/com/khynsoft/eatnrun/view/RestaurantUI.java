package com.khynsoft.eatnrun.view;


import com.khynsoft.eatnrun.controller.RestaurantController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class RestaurantUI extends JFrame {
    private final JTable tblItems;
    private final JTable tblOrders;
    private final JLabel lblSubtotal = new JLabel();
    private final JLabel lblTax = new JLabel();
    private final JLabel lblTotal = new JLabel();
    private final JTextField txtCustomerName = new JTextField();
    private final JButton btnPrintReceipt = new JButton("PRINT RECEIPT");

    private final DefaultTableModel ordersModel;

    public RestaurantUI() throws HeadlessException {
        setLayout(new BorderLayout(8, 8));

        JLabel lblCustomer = new JLabel();
        lblCustomer.setText("Customer name: ");
        lblSubtotal.setText("SUB TOTAL: 0.00");
        lblTax.setText("TAX (12%): 0.00");
        lblTotal.setText("TOTAL: 0.00");

        lblCustomer.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        lblSubtotal.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        lblTax.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        lblTotal.setFont(new Font(Font.SERIF, Font.BOLD, 20));

        Object[] itemsColumn = {"Menu", "Price"};
        Object[][] items = {
                {RestaurantController.blueberryMuffin.getItemName(), RestaurantController.blueberryMuffin.getPrice()},
                {RestaurantController.strawberryBasket.getItemName(), RestaurantController.strawberryBasket.getPrice()},
                {RestaurantController.liteYogurt.getItemName(), RestaurantController.liteYogurt.getPrice()},
                {RestaurantController.vanillaIceCream.getItemName(), RestaurantController.vanillaIceCream.getPrice()},
                {RestaurantController.hashBrowns.getItemName(), RestaurantController.hashBrowns.getPrice()},
                {RestaurantController.toast.getItemName(), RestaurantController.toast.getPrice()},
                {RestaurantController.frenchFries.getItemName(), RestaurantController.frenchFries.getPrice()},
                {RestaurantController.onionSoup.getItemName(), RestaurantController.onionSoup.getPrice()},
                {RestaurantController.coffee.getItemName(), RestaurantController.coffee.getPrice()},
                {RestaurantController.icedTea.getItemName(), RestaurantController.icedTea.getPrice()},
                {RestaurantController.hotChocolate.getItemName(), RestaurantController.hotChocolate.getPrice()},
        };
        DefaultTableModel itemsModel = new DefaultTableModel(items, itemsColumn) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblItems = new JTable(itemsModel);
        tblItems.getColumnModel().getColumn(0).setPreferredWidth(130);
        tblItems.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblItems.setSize(-1, 100);
        tblItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblItems.setFocusable(false);

        //ordered items
        Vector orderRow = new Vector<>();
        Vector<String> orderedItemsColumnNames = new Vector<>();
        orderedItemsColumnNames.add("Menu");
        orderedItemsColumnNames.add("Price");
        orderedItemsColumnNames.add("Quantity");
        orderedItemsColumnNames.add("Total");
        ordersModel = new DefaultTableModel(orderRow, orderedItemsColumnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblOrders = new JTable(ordersModel);
        tblOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblOrders.setFocusable(false);

        // UI Section
        //Left Panel
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(tblItems.getTableHeader(), BorderLayout.NORTH);
        leftPanel.add(tblItems, BorderLayout.CENTER);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));

        //Right Panel
        JPanel rightPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(rightPanel, BoxLayout.Y_AXIS);
        rightPanel.setLayout(boxLayout);

        Box orderBox = Box.createVerticalBox();
        JScrollPane scrollPane = new JScrollPane(tblOrders);
        scrollPane.setPreferredSize(new Dimension(480, 230));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 3, 20));
        orderBox.add(scrollPane);

        rightPanel.add(orderBox);

        Box hbox = Box.createHorizontalBox();
        hbox.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, 100));
        hbox.setPreferredSize(new Dimension(-1, 26));
        txtCustomerName.setMaximumSize(new Dimension(200, 26));
        hbox.add(lblCustomer);
        hbox.add(txtCustomerName);

        lblSubtotal.setHorizontalTextPosition(SwingConstants.LEFT);
        lblTotal.setHorizontalTextPosition(SwingConstants.LEFT);
        lblTax.setHorizontalTextPosition(SwingConstants.LEFT);

        Box statsBox = Box.createVerticalBox();
        statsBox.add(lblSubtotal);
        statsBox.add(lblTax);
        statsBox.add(lblTotal);

        JPanel statsPanel = new JPanel(new BorderLayout());
        statsPanel.setPreferredSize(new Dimension(-1, 150));
        statsPanel.add(hbox, BorderLayout.NORTH);
        statsPanel.add(statsBox, BorderLayout.CENTER);
        statsPanel.add(btnPrintReceipt, BorderLayout.SOUTH);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 6, 10));

        rightPanel.add(statsPanel);

        //Frame operations
        add(leftPanel, BorderLayout.BEFORE_LINE_BEGINS);
        add(rightPanel, BorderLayout.CENTER);
        setTitle("Eat n Run Cafe");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }


    public JTable getTblItems() {
        return tblItems;
    }

    public JTable getTblOrders() {
        return tblOrders;
    }

    public JLabel getLblSubtotal() {
        return lblSubtotal;
    }

    public JLabel getLblTax() {
        return lblTax;
    }

    public JLabel getLblTotal() {
        return lblTotal;
    }

    public JTextField getTxtCustomerName() {
        return txtCustomerName;
    }

    public JButton getBtnPrintReceipt() {
        return btnPrintReceipt;
    }

    public DefaultTableModel getOrdersModel() {
        return ordersModel;
    }
}

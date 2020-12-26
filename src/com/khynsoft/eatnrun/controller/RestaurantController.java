package com.khynsoft.eatnrun.controller;

import com.khynsoft.eatnrun.model.Bill;
import com.khynsoft.eatnrun.model.Menu;
import com.khynsoft.eatnrun.view.RestaurantUI;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class RestaurantController {
    public static final Menu blueberryMuffin = new Menu("Blueberry Muffin", 50.00);
    public static final Menu strawberryBasket = new Menu("Strawberry Basket", 30.00);
    public static final Menu liteYogurt = new Menu("Lite Yogurt", 40.00);
    public static final Menu vanillaIceCream = new Menu("Vanilla Ice cream", 90.00);
    public static final Menu hashBrowns = new Menu("Hash Browns", 35.00);
    public static final Menu toast = new Menu("Toast", 25.00);
    public static final Menu frenchFries = new Menu("French Fries", 50.00);
    public static final Menu onionSoup = new Menu("Onion Soup", 75.00);
    public static final Menu coffee = new Menu("Coffee", 30.00);
    public static final Menu icedTea = new Menu("Iced Tea", 50.00);
    public static final Menu hotChocolate = new Menu("Hot Chocolate", 30.00);

    private static final RestaurantUI restaurantUI = new RestaurantUI();
    private static final Bill bill = new Bill();
    private static long billNumber = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            restaurantUI.setVisible(true);
            init();
        });
    }

    private static void init() {
        //Table listeners
        restaurantUI.getTblItems().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onSelectItem(
                        new Menu(
                                (String) restaurantUI.getTblItems().getValueAt(restaurantUI.getTblItems().getSelectedRow(), 0),
                                (Double) restaurantUI.getTblItems().getValueAt(restaurantUI.getTblItems().getSelectedRow(), 1)
                        ));
            }
        });

        restaurantUI.getTblOrders().putClientProperty("terminateEditOnFocusLost", true);
        restaurantUI.getTblOrders().getModel().addTableModelListener(e -> RestaurantController.refreshStats());
        restaurantUI.getTblOrders().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onOrderChange();
            }
        });

        restaurantUI.getBtnPrintReceipt().addActionListener(e -> {
            try {
                displayReceipt();
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(
                        restaurantUI,
                        "Printing receipt is not possible, an error occured: " + ioException.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    public static synchronized void refreshStats() {
        double subtotal = Double.parseDouble(
                String.valueOf(restaurantUI.
                        getOrdersModel().
                        getDataVector().
                        stream().
                        mapToDouble(vector -> Double.parseDouble(vector.get(3).toString()))
                        .sum())
        );
        double taxedAmount = subtotal * .12;
        double total = subtotal - taxedAmount;

        restaurantUI.getLblSubtotal().setText(String.format("SUB TOTAL: %,.2f", subtotal));
        restaurantUI.getLblTax().setText(String.format("TAX (12%%): %,.2f", taxedAmount));
        restaurantUI.getLblTotal().setText(String.format("TOTAL: %,.2f", total));

        bill.setSubtotal(subtotal);
        bill.setTax(taxedAmount);
        bill.setTotalBill(total);
    }

    public static synchronized void onSelectItem(Menu menu) {
       String quantity = JOptionPane.showInputDialog(
               restaurantUI,
               String.format("How many %s do you want? ", menu.getItemName()),
               "Order Item",
               JOptionPane.INFORMATION_MESSAGE
       );

       if (quantity == null) return;

       if(quantity.matches("^([1-9][0-9]+)|(0?[1-9]+?)$")) {
           int much = Integer.parseInt(quantity);
           restaurantUI.getOrdersModel().addRow(new Object[]{
                   menu.getItemName(),
                   String.format("%.2f", menu.getPrice()),
                   much,
                   String.format("%.2f", menu.getPrice() * much)
           });

           bill.getMenuOrders().add(new Bill.MenuOrder(
               menu.getItemName(),
               menu.getPrice(),
               Integer.parseInt(quantity),
               menu.getPrice() * much
           ));
       } else {
           JOptionPane.showMessageDialog(
                   restaurantUI,
                   String.format("Cannot add item: %s\n\nQuantity must me greater than zero!",
                           menu.getItemName()),
                   "Error",
                   JOptionPane.ERROR_MESSAGE
           );
       }

    }

    private static synchronized void onOrderChange() {
        if (restaurantUI.getTblOrders().getSelectedRow() != -1) {
            int row = restaurantUI.getTblOrders().getSelectedRow();
            TableModel model = restaurantUI.getTblOrders().getModel();
            String quantity = JOptionPane.showInputDialog(
                    restaurantUI,
                    String.format("Update quantity: %s\nPrice: P%,.2f\nCurrent Order: %d",
                            model.getValueAt(row, 0),
                            Double.parseDouble(model.getValueAt(row, 1).toString()),
                            Integer.parseInt(model.getValueAt(row, 2).toString())
                    ),
                    "Update",
                    JOptionPane.INFORMATION_MESSAGE
            );

            if (quantity != null) {
                if (quantity.matches("^([1-9][0-9]+)|(0?[1-9]+?)$")) {
                    int much = Integer.parseInt(quantity);
                    model.setValueAt(
                            much,
                            row,
                            2
                    );
                    model.setValueAt(
                            String.format("%.2f", much *
                                    Double.parseDouble(model.getValueAt(row, 1).toString())),
                            row,
                            3
                    );

                    bill.getMenuOrders().get(row).setQuantity(much);

                    refreshStats();
                } else {
                    JOptionPane.showMessageDialog(
                            restaurantUI,
                            String.format("Cannot add item: %s\n\nQuantity must me greater than zero!",
                                    model.getValueAt(row, 0)),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }

    public static synchronized void displayReceipt() throws IOException {
        if (!restaurantUI.getTxtCustomerName().getText().isEmpty()) {
            bill.setCustomerName(restaurantUI.getTxtCustomerName().getText());

            File receipt = new File("receipt.txt");
            PrintWriter wr = new PrintWriter(new FileWriter(receipt), true);

            LocalDateTime dateTime = LocalDateTime.now();

            StringBuilder items = new StringBuilder();

            for(Bill.MenuOrder order : bill.getMenuOrders()) {
                items.append(String.format("%-31s%,-24.2f%-16d%,15.2f\n",
                        order.getOrderName(),
                        order.getPrice(),
                        order.getQuantity(),
                        order.getTotal()));
            }

            String output = String.format(
                    "Customer Name: %s\n" +
                            "Date: %s\n" +
                            "Bill Number: %d\n\n" +
                            "--------------------------------------------------------------------------------------\n" +
                            "Item/Description               Price                   Quantity                  Total\n" +
                            "--------------------------------------------------------------------------------------\n" +
                            "%s" +
                            "--------------------------------------------------------------------------------------\n\n" +
                            "Subtotal:          %,.2f\n" +
                            "Tax (12%%):         %,.2f\n" +
                            "Total:             %,.2f\n",
                    bill.getCustomerName(),
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(dateTime),
                    ++billNumber,
                    items.toString(), bill.getSubtotal(), bill.getTax(), bill.getTotalBill()
            );

            wr.println(output);
            wr.close();

            int openIt = JOptionPane.showConfirmDialog(
                    restaurantUI,
                    String.format("Receipt is now created in %s.\n\nWould you like to open it?", receipt.getAbsolutePath()),
                    "Open Receipt",
                    JOptionPane.YES_NO_OPTION);
            switch (openIt) {
                case JOptionPane.OK_OPTION:
                    if(Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().edit(receipt);
                    } else {
                        JOptionPane.showMessageDialog(
                                restaurantUI,
                                "Opening receipt is not supported in your system!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                    break;
                default:
                    return;
            }

        } else {
            JOptionPane.showMessageDialog(
                    restaurantUI,
                    "Please fill up your name first!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

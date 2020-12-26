package com.khynsoft.vending_machine;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class VendingMachine {
    private static final Dispenser candy = new Dispenser(7, 10);
    private static final Dispenser chip = new Dispenser(8, 23);
    private static final Dispenser gum = new Dispenser(9, 16);
    private static final Dispenser cookie = new Dispenser(3, 27);

    // TODO: optimize main() (optional)
    // This main() code can be optimized to gain a small amount of performance
    public static void main(String[] args) {
        //Save the items' quantity first before selling them
        final HashMap<String, Integer> totalMaxItems = new HashMap<>();
        totalMaxItems.put("candy", candy.getCount());
        totalMaxItems.put("chip", chip.getCount());
        totalMaxItems.put("gum", gum.getCount());
        totalMaxItems.put("cookie", cookie.getCount());

        CashRegister cashRegister = new CashRegister();

        boolean continues = true;

        do {

            int selection;
            try {
                selection = showSelection();
            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please select properly!",
                        "Invalid Option!",
                        JOptionPane.ERROR_MESSAGE
                );
                continue;
            }
            switch (selection) {
                case 1:
                    try {
                        sellProduct(candy, cashRegister);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Please deposit properly!",
                                "Invalid Cash",
                                JOptionPane.ERROR_MESSAGE
                        );
                        continue;
                    }
                    break;
                case 2:
                    try {
                        sellProduct(chip, cashRegister);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Please deposit properly!",
                                "Invalid Cash",
                                JOptionPane.ERROR_MESSAGE
                        );
                        continue;
                    }
                    break;
                case 3:
                    try {
                        sellProduct(gum, cashRegister);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Please deposit properly!",
                                "Invalid Cash",
                                JOptionPane.ERROR_MESSAGE
                        );
                        continue;
                    }
                    break;
                case 4:
                    try {
                        sellProduct(cookie, cashRegister);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Please deposit properly!",
                                "Invalid Cash",
                                JOptionPane.ERROR_MESSAGE
                        );
                        continue;
                    }
                    break;
                case 5:
                    try {
                        File saleFile = new File("sales.txt");
                        PrintWriter writer = new PrintWriter(new FileWriter(saleFile), true);

                        writer.printf(
                                "==================[[ Sales Report ]]==================\n" +
                                        "[Product]            [Quantity]           [Total Sold]\n" +
                                        "  Candy                %d                  Php %,d\n" +
                                        "  Potato Chip          %d                  Php %,d\n" +
                                        "  Chewing Gum          %d                  Php %,d\n" +
                                        "  Cookie               %d                  Php %,d\n" +
                                        "                            Total Income: Php %,d\n",

                                totalMaxItems.get("candy") - candy.getCount(),
                                (totalMaxItems.get("candy") - candy.getCount()) * candy.getProductCost(),

                                totalMaxItems.get("chip") - chip.getCount(),
                                (totalMaxItems.get("chip") - chip.getCount()) * chip.getProductCost(),

                                totalMaxItems.get("gum") - gum.getCount(),
                                (totalMaxItems.get("gum") - gum.getCount()) * gum.getProductCost(),

                                totalMaxItems.get("cookie") - cookie.getCount(),
                                (totalMaxItems.get("cookie") - cookie.getCount()) * cookie.getProductCost(),

                                //Total Sales (This part of code is not optimized but since this is a small program, it won't matter much :)
                                (totalMaxItems.get("candy") - candy.getCount()) * candy.getProductCost() +
                                        (totalMaxItems.get("chip") - chip.getCount()) * chip.getProductCost() +
                                        (totalMaxItems.get("gum") - gum.getCount()) * gum.getProductCost() +
                                        (totalMaxItems.get("cookie") - cookie.getCount()) * cookie.getProductCost()
                        );
                        writer.close();
                        JOptionPane.showMessageDialog(
                                null,
                                "Created or overridden file on: " + saleFile.getCanonicalPath()
                        );
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(
                                null,
                                "File can not be created due to " + e.getMessage(),
                                "File Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                    break;
                case 6:
                    try {
                        File inventoryFile = new File("inventory.txt");
                        PrintWriter writer = new PrintWriter(new FileWriter(inventoryFile), true);

                        writer.printf(
                                "==================[[ Item Inventory Report ]]==================\n" +
                                        "[Product]            [Remaining Quantity]\n" +
                                        "  Candy                    %d            \n" +
                                        "  Potato Chip              %d            \n" +
                                        "  Chewing Gum              %d            \n" +
                                        "  Cookie                   %d            \n",
                                candy.getCount(),
                                chip.getCount(),
                                gum.getCount(),
                                cookie.getCount()
                        );
                        writer.close();
                        JOptionPane.showMessageDialog(
                                null,
                                "Created/Overridden file on: " + inventoryFile.getCanonicalPath()
                        );
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(
                                null,
                                "File can not be created due to " + e.getMessage(),
                                "File Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                    break;
                case 7:
                    continues = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(
                            null,
                            "Please select properly ",
                            "Invalid Option!",
                            JOptionPane.ERROR_MESSAGE
                    );
            }

        } while (continues);

        JOptionPane.showMessageDialog(
                null,
                "Thank you for using the program (*/ω＼*)"
        );
    }

    public static int showSelection() throws NumberFormatException {
        String message = String.format(
                        "     [Product Name]                    [Price]\n" +
                        "     [1] Candy                                 P%d\n" +
                        "     [2] Potato Chip                       P%d\n" +
                        "     [3] Chewing Gum                   P%d\n" +
                        "     [4] Cookie                                P%d\n\n" +
                        "===============[[ Advanced Features ]]==========\n" +
                        "     [5] Sales Report\n     [6] Item Inventory\n     [7] Exit",

                candy.getProductCost(),
                chip.getProductCost(),
                gum.getProductCost(),
                cookie.getProductCost());

        return Integer.parseInt(
                JOptionPane.showInputDialog(
                        null,
                        message,
                        "Vending Machine",
                        JOptionPane.PLAIN_MESSAGE)
        );
    }

    public static void sellProduct(Dispenser product, CashRegister cashRegister) {
        int price, coinsInserted, coinsRequired;

        if (product.getCount() > 0) {
            price = product.getProductCost();
            coinsRequired = price;
            coinsInserted = 0;

            while (coinsRequired > 0) {
                coinsInserted += Integer.parseInt(
                        JOptionPane.showInputDialog("Please deposit " + coinsRequired + " cents: ")
                );
                coinsRequired = price - coinsInserted;
            }

            cashRegister.acceptAmount(coinsInserted);

            product.makeSale();

            JOptionPane.showMessageDialog(
                    null,
                    "Collect your item at the bottom and enjoy!",
                    "Item bought successfully",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Sorry this item is out of stock",
                    "Yikes",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}

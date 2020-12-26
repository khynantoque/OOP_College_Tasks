package com.khynsoft.calculator_le;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Calculator {

    //Detect the datatype of the input/s
    private static String detectInput(String data) {
        //Double
        if (data.matches("^-?\\d+\\.\\d{0,7}$")) return "float";
        else if (data.matches("^-?\\d+\\.\\d+$")) return "double";
        else if (data.matches("^-?\\d+$")) return "int";
        else return "letters";
    }

    //Detect a double when all float inputs have double: float->all float; double->there's a double
    //int-> if all int; string->confused screaming
    private static String allIdentity(String[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if(detectInput(nums[i]).equals("float")) {
                for (; i < nums.length; i++) {
                    if (detectInput(nums[i]).equals("double")) return "double";
                }
                return "float";
            }

            if(detectInput(nums[i]).equals("letters")) return "string";
        }

        return "int";

    }

    private static void printOperationMessage(int option, String[] operations, boolean wasRunning, Scanner sc) {
        System.out.println("=========[[ "+ operations[option - 1] +" ]]=========");
        if(!wasRunning && option != 5)
            System.out.println(
                "For multiple input number separate them in spaces.\n" +
                "Example: 15 13\n" +
                "For multiple numbers: 12 16 14 15 23");
        if(option < 5) {
            System.out.print("Input your numbers: ");
            sc.nextLine();
        }
    }

    public static void main(String[] args) {
        Arithmetic calc = new Arithmetic();
        Scanner sc = new Scanner(System.in);

        //wasRunning is an indicator if we will show again the input instruction
        boolean running = true, wasRunning = false;

        while (running) {
            System.out.println("=========[[ Calculator ]]=========");
            System.out.println("Please select a calculator operation:\n" +
                    "[1] Addition\n" +
                    "[2] Subtraction\n" +
                    "[3] Multiplication\n" +
                    "[4] Division\n" +
                    "[5] Exit");

            System.out.print("Enter your selection here: ");

            int option;

            //Try to detect if the user doesn't know how to use numbers
            try {
                option = sc.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Invalid option!");
                main(null);
                break;
            }

            String[] operations = {"Addition", "Subtraction", "Multiplication", "Division", "Exiting Program", "Error"};

            //Option input validation
            if (option > operations.length || option <= 0){
                System.err.println("Invalid operation!");
                continue;
            }

            printOperationMessage(option, operations, wasRunning, sc);

            String line = sc.nextLine();
            String[] nums = line.split("\\s+");

            //input validation
            //set title to error
            if (option > 5){
                System.out.println("Invalid operation!");
                continue;
            }
            if ((line.trim().isEmpty() || nums == null) && option != 5) {
                System.out.println("Invalid input.");
                continue;
            } else {
                if (allIdentity(nums).equals("string")) {
                    System.out.println("Invalid input number/s\n");
                    continue;
                }
            }

            //Operation Handler
            switch (option) {
                case 1: //Addition

                    if (nums.length == 2) {
                        if (detectInput(nums[0]).equals("float") || detectInput(nums[1]).equals("float")) {
                            System.out.printf(
                                    "Sum: %f\n",
                                    calc.add(Float.parseFloat(nums[0]),
                                            Float.parseFloat(nums[1])));
                        } else if (detectInput(nums[0]).equals("double") || detectInput(nums[1]).equals("double")) {
                            System.out.printf(
                                    "Sum: %g\n",
                                    calc.add(Double.parseDouble(nums[0]), Double.parseDouble(nums[1])));
                        } else if (detectInput(nums[0]).equals("int") || detectInput(nums[1]).equals("int")){
                            System.out.printf(
                                    "Sum: %d\n",
                                    calc.add(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])));
                        } else System.err.println("Invalid input");

                    } else {

                        if (allIdentity(nums).equals("float")) {

                            float[] floats = new float[nums.length];
                            IntStream.range(0, floats.length).forEach(i -> floats[i] = Float.parseFloat(nums[i]));

                            System.out.printf("Sum: %g\n", calc.add(floats));

                        } else if (allIdentity(nums).equals("double")){

                            double[] doubles = new double[nums.length];
                            Arrays.setAll(doubles, i -> Double.parseDouble(nums[i]));

                            System.out.printf("Sum: %f\n", calc.add(doubles));

                        } else if (allIdentity(nums).equals("int")){

                            int[] ints = new int[nums.length];
                            Arrays.setAll(ints, i -> Integer.parseInt(nums[i]));

                            System.out.printf("Sum: %d\n", calc.add(ints));

                        } else {
                            System.err.println("Invalid input number/s");
                        }
                    }

                    break;

                case 2: //Subtraction

                    if (nums.length == 2) {

                        if (detectInput(nums[0]).equals("float") || detectInput(nums[1]).equals("float")) {
                            System.out.printf(
                                    "Difference: %f\n",
                                    calc.subtract(Float.parseFloat(nums[0]),
                                            Float.parseFloat(nums[1])));
                        } else if (detectInput(nums[0]).equals("double") || detectInput(nums[1]).equals("double")) {
                            System.out.printf(
                                    "Difference: %g\n",
                                    calc.subtract(Double.parseDouble(nums[0]), Double.parseDouble(nums[1])));
                        }else if (detectInput(nums[0]).equals("int") || detectInput(nums[1]).equals("int")){
                            System.out.printf(
                                    "Difference: %d\n",
                                    calc.subtract(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])));
                        } else System.err.println("Invalid Input");

                    } else {

                        if (allIdentity(nums).equals("float")) {

                            float[] floats = new float[nums.length];
                            IntStream.range(0, floats.length).forEach(i -> floats[i] = Float.parseFloat(nums[i]));

                            System.out.printf("Difference: %g\n", calc.subtract(floats));

                        } else if (allIdentity(nums).equals("double")){

                            double[] doubles = new double[nums.length];
                            Arrays.setAll(doubles, i -> Double.parseDouble(nums[i]));

                            System.out.printf("Difference: %f\n", calc.subtract(doubles));

                        } else if (allIdentity(nums).equals("int")){

                            int[] ints = new int[nums.length];
                            Arrays.setAll(ints, i -> Integer.parseInt(nums[i]));

                            System.out.printf("Difference: %d\n", calc.subtract(ints));

                        } else {
                            System.err.println("Invalid input number/s");
                        }

                    }
                    break;

                case 3: //Multiplication

                    if (nums.length == 2) {

                        if (detectInput(nums[0]).equals("float") || detectInput(nums[1]).equals("float")) {
                            System.out.printf(
                                    "Product: %f\n",
                                    calc.multiply(Float.parseFloat(nums[0]),
                                            Float.parseFloat(nums[1])));
                        } else if (detectInput(nums[0]).equals("double") || detectInput(nums[1]).equals("double")) {
                            System.out.printf(
                                    "Product: %g\n",
                                    calc.multiply(Double.parseDouble(nums[0]), Double.parseDouble(nums[1])));
                        }else if (detectInput(nums[0]).equals("int") || detectInput(nums[1]).equals("int")){
                            System.out.printf(
                                    "Product: %d\n",
                                    calc.multiply(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])));
                        } else System.err.println("Invalid Input");

                    } else {

                        if (allIdentity(nums).equals("float")) {

                            float[] floats = new float[nums.length];
                            IntStream.range(0, floats.length).forEach(i -> floats[i] = Float.parseFloat(nums[i]));

                            System.out.printf("Product: %g\n", calc.multiply(floats));

                        } else if (allIdentity(nums).equals("double")){

                            double[] doubles = new double[nums.length];
                            for (int i = 0; i < doubles.length; i++) doubles[i] = Double.parseDouble(nums[i]);

                            System.out.printf("Product: %f\n", calc.multiply(doubles));

                        } else if (allIdentity(nums).equals("int")){

                            int[] ints = new int[nums.length];
                            Arrays.setAll(ints, i -> Integer.parseInt(nums[i]));

                            System.out.printf("Product: %d\n", calc.multiply(ints));

                        } else {
                            System.err.println("Invalid input number/s");
                        }

                    }

                    break;

                case 4: //Division
                    try {
                        if (nums.length == 2) {

                            if (detectInput(nums[0]).equals("float") || detectInput(nums[1]).equals("float")) {
                                System.out.printf(
                                        "Quotient: %f\n",
                                        calc.divide(Float.parseFloat(nums[0]),
                                                Float.parseFloat(nums[1])));
                            } else if (detectInput(nums[0]).equals("double") || detectInput(nums[1]).equals("double")) {
                                System.out.printf(
                                        "Quotient: %g\n",
                                        calc.divide(Double.parseDouble(nums[0]), Double.parseDouble(nums[1])));
                            } else if (detectInput(nums[0]).equals("int") || detectInput(nums[1]).equals("int")) {
                                System.out.printf(
                                        "Quotient: %d\n",
                                        calc.divide(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])));
                            } else System.err.println("Invalid Input");

                        } else {

                            if (allIdentity(nums).equals("float")) {
                                float[] floats = new float[nums.length];
                                IntStream.range(0, floats.length).forEach(i -> floats[i] = Float.parseFloat(nums[i]));

                                System.out.printf("Quotient: %g\n", calc.divide(floats));
                            } else if (allIdentity(nums).equals("double")) {
                                double[] doubles = new double[nums.length];
                                Arrays.setAll(doubles, i -> Double.parseDouble(nums[i]));

                                System.out.printf("Quotient: %f\n", calc.divide(doubles));
                            } else if (allIdentity(nums).equals("int")) {
                                int[] ints = new int[nums.length];
                                Arrays.setAll(ints, i -> Integer.parseInt(nums[i]));

                                System.out.printf("Quotient: %d\n", calc.divide(ints));
                            } else {
                                System.err.println("Invalid input number/s");
                            }

                        }
                    } catch (ArithmeticException ex) {
                        System.out.println("Invalid input, don't input 0 in division!");
                    }
                    break;
                case 5: //Exit
                    running = false;
                    System.out.println("Thank you for using the program :)");
                    break;
                default: //Pataka ra
                    System.err.println("Invalid option.");
            }

            if (option != 5) {
                System.out.print("\nPress Enter key to continue...");
                sc.nextLine();
            }

            wasRunning = true;
        }
        sc.close();
    }
}

package com.khynsoft.calculator_le;

public class Arithmetic {

    public int add(int a, int b) {return a + b;}
    public double add(double a, double b) {return a + b;}
    public float add(float a, float b) {return a + b;}

    public int add(int[] nums) {
        int sum = 0;
        for(int num : nums) sum += num;
        return sum;
    }

    public double add(double[] nums) {
        double sum = 0;
        for(double num : nums) sum += num;
        return sum;
    }

    public float add(float[] nums) {
        float sum = 0;
        for(float num : nums) sum += num;
        return sum;
    }

    public int subtract(int a, int b) {return a - b;}
    public double subtract(double a, double b) {return a - b;}
    public float subtract(float a, float b) {return a - b;}

    public int subtract(int[] nums) {
        int difference = 0;
        for (int i = 0, numsLength = nums.length; i < numsLength; i++) {
            if(i == 0)
                difference = nums[0];
            else
                difference -= nums[i];
        }
        return difference;
    }

    public double subtract(double[] nums) {
        double difference = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i == 0)
                difference = nums[0];
            else
                difference -= nums[i];
        }
        return difference;
    }

    public float subtract(float[] nums) {
        float difference = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i == 0)
                difference = nums[0];
            else
                difference -= nums[i];
        }
        return difference;
    }

    public int multiply(int a, int b) {return a * b;}
    public double multiply(double a, double b) {return a * b;}
    public float multiply(float a, float b) {return a * b;}

    public int multiply(int[] nums) {
        int product = 0;
        for (int i = 0;i < nums.length; i++) {
            if(i == 0)
                product = nums[0];
            else
                product *= nums[i];
        }
        return product;
    }

    public double multiply(double[] nums) {
        double product = 0;
        for (int i = 0;i < nums.length; i++) {
            if(i == 0)
                product = nums[0];
            else
                product *= nums[i];
        }
        return product;
    }

    public float multiply(float[] nums) {
        float product = 0;
        for (int i = 0;i < nums.length; i++) {
            if(i == 0)
                product = nums[0];
            else
                product *= nums[i];
        }
        return product;
    }

    public int divide(int a, int b) {
        System.out.println("Remainder: " + remainder(a, b));
        return a / b;
    }
    public double divide(double a, double b) {
        System.out.println("Remainder: " + remainder(a, b));
        return a / b;
    }
    public float divide(float a, float b) {
        System.out.println("Remainder: " + remainder(a, b));
        return a / b;
    }

    public int divide(int[] nums) {
        int quotient = 0;
        System.out.print("Remainder: ");
        for (int i = 0; i < nums.length; i++) {
            if (i == 0)
                quotient = nums[i];
            else {
                System.out.printf("%d ", remainder(quotient, nums[i]));
                quotient /= nums[i];
            }
        }
        System.out.println();
        return quotient;
    }

    public double divide(double[] nums) {
        double quotient = 0;
        System.out.print("Remainder: ");
        for (int i = 0; i < nums.length; i++) {
            if (i == 0)
                quotient = nums[i];
            else {
                System.out.printf("%d ", remainder(quotient, nums[i]));
                quotient /= nums[i];
            }
        }
        System.out.println();
        return quotient;
    }

    public float divide(float[] nums) {
        float quotient = 0;
        System.out.print("Remainder: ");
        for (int i = 0; i < nums.length; i++) {
            if (i == 0)
                quotient = nums[i];
            else {
                System.out.printf("%d ", remainder(quotient, nums[i]));
                quotient /= nums[i];
            }
        }
        System.out.println();
        return quotient;
    }

    private int remainder(int a, int b){
       return a != 0 && b != 0 ? a % b : 0;
    }

    private float remainder(double a, double b){
        return a != 0 && b != 0 ? (float) (a % b) : 0;
    }
}

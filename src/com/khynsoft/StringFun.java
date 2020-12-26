package com.khynsoft;

public class StringFun {

    public int getIntNumbers(String text) {
        if (text.isEmpty()) text = "-1";
        return Integer.parseInt(text.replaceAll("\\D", ""));
    }

    public boolean areAllNumber(String text) {
        for (char s :
                text.toCharArray()) {
            if(Character.isLetter(s)) return false;
        }
        return true;
    }

    public String replaceLetter(String beforeLetter, int index, char replaceLetter) {
        if(index > beforeLetter.length()) index = beforeLetter.length() - 1;
        return new String(new StringBuffer(beforeLetter).replace(index,
                index + 1,
                String.valueOf(replaceLetter)));
    }

    public static void main(String[] args) {
        StringFun s = new StringFun();
        System.out.println(s.getIntNumbers("asdasdiwen12sadasd"));
        System.out.println(s.replaceLetter("Hello", 6, 'H'));
        System.out.println(s.areAllNumber("12334") ? "All are number/s" : "Not all are number/s");
    }

}

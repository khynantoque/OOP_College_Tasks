package com.khynsoft.hashmap;

import java.util.HashMap;
import java.util.Map;

public class Bookshelf {
    public static void main(String[] args) {
        //Creating map of Books
        Map<Integer,Book> map = new HashMap<>();

        //Creating Books
        Book b1=new Book(
                121,
                "Let us C",
                "Gon O D'imm",
                "BPB",
                8);
        Book b2=new Book(
                233,
                "Operating System",
                "Galvin",
                "Wiley",
                6);
        Book b3=new Book(
                101,
                "Data Communications & Networking",
                "Forouzan","Mc Graw Hill",
                4);

        //Adding Books to map
        map.put(1,b1);
        map.put(2,b2);
        map.put(3,b3);

        //Traversing map
        for(Map.Entry<Integer, Book> entry:map.entrySet()){
            int key=entry.getKey();
            Book b=entry.getValue();
            System.out.println(key+" Details:");
            System.out.println(b.getId() +
                    " "+ b.getName() +
                    " "+ b.getAuthor() +
                    " "+ b.getPublisher() +
                    " "+ b.getQuantity());
        }
    }
}

package com.khynsoft.covid19;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ContactTracer {

    private static ArrayList<Person> getDefaultValues() {
        ArrayList<Person> persons = new ArrayList<>();

        persons.add(new Person(
                "Peele An",
                "Aplaya Digos City",
                "2020-01-15",
                "12:02PM",
                "09996368978",
                56.21f));
        persons.add(new Person(
                "Geralt of Rivia",
                "Lapu-Lapu St. Aplaya Digos City",
                "2020-01-15",
                "12:15PM",
                "09996468978",
                56.21f));
        persons.add(new Person(
                "Gaunter O'Dimm",
                "Luna St. Digos City",
                "2020-01-15",
                "2:02PM",
                "09998668978",
                56.21f));

        return persons;
    }

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean running = true, wasRunning = false;

        while(running) {
            if(!wasRunning) {
                System.out.println("Enter \"help\"");
            }

            //if wasRunning is true then there's no tutorial anymore
            wasRunning = true;

            String[] command = sc.nextLine().split(" ");

            //Input Validation
            if(command.length == 0 || command[0].isEmpty())
                continue;
            else if(command[0].equals("addperson") && command.length == 7) {
                if (!Pattern.compile("^\\d*\\.\\d{0,2}$")
                        .matcher(command[6])
                        .matches()) {

                    System.err.println("Invalid Temperature!");
                    continue;
                }
                if (!Pattern.compile("^(\\+?63|0)\\d{10}$")
                        .matcher(command[3])
                        .matches()) {

                    System.err.println("Invalid contact number!");
                    continue;
                }
                if (!Pattern.compile("^((0?\\d)|(1[0-2])):[0-5]\\d([aA][mM]|[pP][mM])$")
                        .matcher(command[5])
                        .matches()) {

                    System.err.println("Invalid time!");
                    continue;
                }
            } else if(command[0].equals("addperson") && command.length < 7) continue;

            switch (command[0]) {
                case "help":
                    System.out.println("========[[ Covid19 Contact Tracing Program ]]========\n");
                    System.out.println("[Command]\t\t\t[Usage]\n" +
                            "addperson\t\t" +
                            "addperson [name] " +
                            "[address(separate spaces with underscores)] " +
                            "[contact_number] " +
                            "[date (yyyy-mm-dd)] " +
                            "[time] " +
                            "[body_temperature(celsius)]");
                    System.out.println("\t\t\t\tExample: addperson John_Doe Lapu-Lapu_St.Aplaya 09995558787 2020-10-11 1:01PM 37.32");
                    System.out.println("loaddefault\t\tset the clients log from built-in default values.");
                    System.out.println("show\t\t\tshow all clients logged in.");
                    System.out.println("exit\t\t\tstop the program.");
                    break;
                case "loaddefault":
                    persons = getDefaultValues();
                    System.out.println("Default values assigned.");
                    break;
                case "addperson":
                    persons.add(new Person(
                            command[1].replaceAll("_", " "),
                            command[2].replaceAll("_", " "),
                            command[4],
                            command[5],
                            command[3],
                            Float.parseFloat(command[6])
                    ));
                    System.out.printf("%s added!\n", command[1].replaceAll("_", " "));
                    break;
                case "show":
                    System.out.println("[Name] \t [Address] \t [Contact Number] \t [Date] \t [Time] \t [Temperature (Celsius)]");

                    if(persons.size() < 1)
                        System.out.println("\t\t\t\tNo clients yet.");
                    else
                        for (Person person : persons) { System.out.println(person.toString()); }

                    break;
                case "exit":
                    System.out.println("Thank you for using the program :>");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Command!");
            }
        }

    }
}

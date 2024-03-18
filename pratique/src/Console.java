import java.util.Scanner;

public class Console {
    private static String reset = "\u001B[0m";
    private static String red = "\u001B[31m";
    private static String green = "\u001B[32m";
    private static String yellow = "\u001B[33m";
    private static String cyan = "\u001B[36m";

    public static void red(String line) {
        System.out.println(red + line + reset);
    }

    public static void green(String line) {
        System.out.println(green + line + reset);
    }

    public static void yellow(String line) {
        System.out.println(yellow + line + reset);
    }

    public static void println(String line) {
        System.out.println(line);
    }

    public static void print(String line) {
        System.out.print(line);
    }

    public static String getNextLine(Scanner s) {
        System.out.print("--> " + cyan);
        String line = s.nextLine();
        System.out.print(reset);
        return line;

    }
    public static void printLogo() {
        String art = green +
                "░█████╗░███████╗███████╗██╗████████╗░█████╗░███╗░░██╗░█████╗░░░░████████╗███████╗░█████╗░██╗░░██╗\n" +
                "██╔══██╗╚════██║██╔════╝██║╚══██╔══╝██╔══██╗████╗░██║██╔══██╗░░░╚══██╔══╝██╔════╝██╔══██╗██║░░██║\n" +
                "███████║░░███╔═╝█████╗░░██║░░░██║░░░██║░░██║██╔██╗██║███████║░░░░░░██║░░░█████╗░░██║░░╚═╝███████║\n" +
                "██╔══██║██╔══╝░░██╔══╝░░██║░░░██║░░░██║░░██║██║╚████║██╔══██║░░░░░░██║░░░██╔══╝░░██║░░██╗██╔══██║\n" +
                "██║░░██║███████╗███████╗██║░░░██║░░░╚█████╔╝██║░╚███║██║░░██║██╗░░░██║░░░███████╗╚█████╔╝██║░░██║\n" +
                "╚═╝░░╚═╝╚══════╝╚══════╝╚═╝░░░╚═╝░░░░╚════╝░╚═╝░░╚══╝╚═╝░░╚═╝╚═╝░░░╚═╝░░░╚══════╝░╚════╝░╚═╝░░╚═╝"
                + reset;
        System.out.println(art);

    }
}

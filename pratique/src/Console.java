public class Console {
    private static String reset = "\u001B[0m";
    private static String red = "\u001B[31m";
    private static String green = "\u001B[32m";
    private static String yellow = "\u001B[33m";

    public static String reset() {
        return reset;
    }

    public static String red() {
        return red;
    }

    public static String green() {
        return green;
    }

    public static String yellow() {
        return yellow;
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

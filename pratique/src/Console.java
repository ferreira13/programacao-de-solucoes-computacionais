public class Console {
    private static String reset = "\u001B[0m";
    private static String red = "\u001B[31m";
    private static String green = "\u001B[32m";
    private static String yellow = "\u001B[33m";

    public static void red(String line) {
        System.out.println(red + line + reset);
    }

    public static void green(String line) {
        System.out.println(green + line + reset);
    }

    public static void yellow(String line) {
        System.out.println(yellow + line + reset);
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

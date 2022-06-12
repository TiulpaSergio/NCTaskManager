package ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.controller;

import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.view.Container;

import org.apache.log4j.Logger;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Scanner;

public class Passwords {

    private static final Logger log = Logger.getLogger(Passwords.class);
    private static final File FILE = new File("Password.txt");
    private Scanner scanner;

    public void changePassword(String password) {
        String encryptedPassword = encrypt(password);

        try (FileWriter writer = new FileWriter(FILE)) {
            writer.write(encryptedPassword);
        } catch(IOException e){
            log.info(e);
        }
    }

    public boolean checkPassword() {
        scanner = new Scanner(System.in);

        if (isFileEmpty(FILE)) {
            setNewPassword();
            return true;
        } else {
            String encryptedPassword = null;
            String ps = null;
            String password = null;
            int i = 3;

            try(FileReader reader = new FileReader(FILE)) {
                Scanner scan = new Scanner(reader);

                while (scan.hasNextLine()) {
                    encryptedPassword = scan.nextLine();
                }
            } catch (IOException e) {
                log.info(e);
            }

            while (i-- != 0) {
                Container.print("Enter password: ");
                ps = scanner.nextLine();
                password = encrypt(ps);

                if (!password.equals(encryptedPassword)) {
                    Container.println("Bad password, try again!");
                    Container.println("You have " + i + " attempts left");
                } else {
                    Container.println("Correct password!");
                    Container.println("Want to set a new password? (Yes:No)");
                    String answer = scanner.nextLine();
                    if (answer.equals("Yes") || answer.equals("yes")) {
                        setNewPassword();
                    }
                    return true;
                }
            }

        }
        return false;
    }

    private void setNewPassword() {
        scanner = new Scanner(System.in);
        Container.print("Enter new password: ");
        String password = scanner.nextLine();
        changePassword(password);
    }

    public boolean isFileEmpty(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return br.readLine() == null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String encrypt(String password) {
        MessageDigest md5;

        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes());
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
package account;

import java.util.ArrayList;
import java.util.Random;

public class MembershipSystem {

    public static ArrayList<String> usernames = new ArrayList<>();

    private ArrayList<String> passwords = new ArrayList<>();
    private ArrayList<String> securityCode = new ArrayList<>();

    private String holdPassword;
    private String holdUsername;
    private String holdGeneratedCode;

    private int temp;

    private String[] generation = {
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
        "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
        "u", "v", "w", "x", "y", "z",

        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",

        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
        "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
        "U", "V", "W", "X", "Y", "Z",

        "!", "@", "#", "$", "%", "^", "&", "*", "(", ")"
    };

    private Random rand = new Random();

    public MembershipSystem() {
    }

    public boolean membershipStatus(String username, String password) {

        if (username == null || password == null) {
            System.out.println(
                "LOGIN UNSUCCESSFUL. USERNAME OR PASSWORD CANNOT BE NULL."
            );
            return false;
        }

        int index = usernames.indexOf(username);

        if (index != -1) {

            if (passwords.get(index).equals(password)) {

                System.out.println("LOGIN SUCCESSFUL.");

                holdPassword = password;
                holdUsername = username;

                return true;

            } else {

                System.out.println(
                    "LOGIN UNSUCCESSFUL. INCORRECT PASSWORD."
                );
            }

        } else {

            System.out.println(
                "LOGIN UNSUCCESSFUL. INCORRECT USERNAME."
            );
        }

        return false;
    }

    public boolean checkIdentity(String code) {

        if (code == null) {
            System.out.println("SECURITY CODE CANNOT BE NULL.");
            return false;
        }

        int index = usernames.indexOf(holdUsername);

        return index != -1 &&
               securityCode.get(index).equals(code);
    }

    public void changeUsername(String newUsername) {

        if (newUsername == null) {
            System.out.println("NEW USERNAME CANNOT BE NULL.");
            return;
        }

        temp = passwords.indexOf(holdPassword);

        if (temp != -1) {
            usernames.set(temp, newUsername);
            holdUsername = newUsername;
        }
    }

    public void changePassword(String newPassword) {

        if (newPassword == null) {
            System.out.println("NEW PASSWORD CANNOT BE NULL.");
            return;
        }

        temp = usernames.indexOf(holdUsername);

        if (temp != -1) {
            passwords.set(temp, newPassword);
            holdPassword = newPassword;
        }
    }

    public boolean addMembership(
        String username,
        String password
    ) {

        if (username == null || password == null) {
            System.out.println(
                "USERNAME OR PASSWORD CANNOT BE NULL."
            );
            return false;
        }

        if (usernames.contains(username)) {
            System.out.println("Username already exists.");
            return false;
        }

        usernames.add(username);
        passwords.add(password);
        securityCode.add("");

        return true;
    }

    public String createOneTimePassword() {

        String code = "";
        boolean flag = true;

        while (flag) {

            code = "";

            for (int i = 0; i < 6; i++) {
                code += generation[
                    rand.nextInt(generation.length)
                ];
            }

            if (!securityCode.contains(code)) {
                flag = false;
            }
        }

        holdGeneratedCode = code;

        int index = usernames.indexOf(holdUsername);

        if (index != -1) {
            securityCode.set(index, holdGeneratedCode);
        }

        return holdGeneratedCode;
    }
}
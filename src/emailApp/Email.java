/* Known issues:

        - Special characters like Ğ, Ü, Ç, Ö are NOT removed when generating an email, resulting in an invalid address.
        - Writing two words/names during the "first name" prompt skips the input for last name, presumably because of .nextInt .
            - FIXED
                - Using .nextLine instead of .next or .nextInt makes the program properly wait for inputs.

*/

package emailApp;

import java.util.Scanner;
import java.text.Normalizer;

public class Email {
    private String username;
    private String password;
    private String email;
    private int defaultMailCapacity=500;
    private String department;
    private int defaultPasswordLength = 12;
    private String alternateEmail;
    private String companyName="companyName";

    // Constructor for receiving first/last name

    public Email () {
        System.out.println("Email Creation");

    // Get username
        this.username = setUsername();


    // Method to ask department / return department

        this.department = setDepartment();

    // Method for the randomly generated password
        this.password = randomPassword(defaultPasswordLength);

    // Get user's alternate e-mail
        this.alternateEmail = alternateEmailGen();

    // Generate email address
        username = normalizeUsername();
        email = username.toLowerCase().replaceAll("\\s","") + "@" + department.toLowerCase() + companyName.toLowerCase() + ".com";
    }

    private String setUsername() {
        System.out.print("Input new user's first name: ");
        Scanner sc = new Scanner(System.in);
        String userFirstName = sc.nextLine();
        System.out.print("Input new user's last name: ");
        String userLastName = sc.nextLine();

        return  (userFirstName + " " + userLastName);
    }

    private String normalizeUsername () {
        String normalized = Normalizer.normalize(username, Normalizer.Form.NFD);

        normalized = normalized.replaceAll("[^\\p{ASCII}]","");
        return normalized;
    }

    private String setDepartment(){
        System.out.print("Select Department\n1 - Sales\n2 - Development\n3 - Accounting\n4 - Quality Control\n5 - Documentation\n6 - Human Resources\n0 - None");
        Scanner in = new Scanner(System.in);
        int depChoice = in.nextInt();

        if (depChoice == 1) {
            return "sales.";
        }
        else if (depChoice == 2) {
            return "dev.";

        }
        else if (depChoice == 3) {
            return "acct.";
        }
        else if (depChoice == 4) {
            return "qc.";
        }
        else if (depChoice == 5) {
            return "doc.";
        }
        else if (depChoice == 6) {
            return "hr.";
        }
        else {
            return "";
        }

    }

    private String alternateEmailGen() {
        System.out.print("Input new user's personal address: ");
        Scanner sc = new Scanner(System.in);
        String personalAddress = sc.next();
        return personalAddress;
    }

    // Method for random password
    private String randomPassword(int length) {
        String passwordSet = "abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMONPQRSTUVWXYZ0123456789!@#$";
        char[] password = new char[length];
        for (int i=0; i<length; i++) {
            int rand = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(rand);
        }
        return new String(password);
   }

   public void setMailboxCapacity(int capacity) {
        this.defaultMailCapacity = capacity;
   }

   public void changePassword(String password) {
        this.password = password;
   }
    public int getMailboxCapacity() { return defaultMailCapacity; }
    public String getalternateEmail() { return alternateEmail; }
    public String getPassword() { return password; }

    public String getInfo() {
        return "Name: " + username + "\nCompany E-mail: " + email + "\nMailbox Capacity: " + defaultMailCapacity + " GB" + "\nAlternate E-mail: " + alternateEmail;
    }
}

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/*
 * 1. The ability to register a user with a username and password
 * 2. Ability to register the seller with username and password
 * 3. The ability to log in as a user/seller
 * 4. The ability to exit the account without closing the program (logout)
 * 5. Unique menu design for each user role (user/seller)
 * 6. As a user:
 * a. Initially, he has an empty shopping cart
 * b. Can add/remove any number of products to the shopping cart
 * c. See your shopping cart
 * d. Charge your account
 * e. See your account information
 * f. Search for a product by product name or code
 * g. See the list of goods
 * h. Clear his shopping cart (that is, buy all the goods in his shopping cart)
 * i. view the history of your purchases *
 * 7. As a seller:
 * a. Can add/delete goods
 * b. See the list of goods
 * c. Change the information of a specific product
 * d. View the number of times each product has been purchased *
 */
public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        List<Goods> goods = new ArrayList<>();
        List<Buy> buys = new ArrayList<>();

    }

    public static boolean checkPassword(String password) {
        if (password.length() < 8) {
            System.out.println("Password must have at least 8 characters");
            return false;
        }
        boolean hasNumber = false;
        boolean hasCapital = false;
        boolean hasSmall = false;
        boolean hasSpecial = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                hasNumber = true;
            } else if (Character.isUpperCase(password.charAt(i))) {
                hasCapital = true;
            } else if (Character.isLowerCase(password.charAt(i))) {
                hasSmall = true;
            } else if (password.charAt(i) == '*' || password.charAt(i) == '%' || password.charAt(i) == '$'
                    || password.charAt(i) == '#' || password.charAt(i) == '@' || password.charAt(i) == '!') {
                hasSpecial = true;
            }
        }
        if (hasNumber && hasCapital && hasSmall && hasSpecial) {
            return true;
        } else {
            System.out.println(
                    "Password must contain at least one number and Capital and small letter and * % $ # @ ! characters");
            return false;
        }
    }

    public static User registerUser() {
        Scanner scanner = new Scanner(System.in);
        String position;
        String username;
        String password;
        String name;
        String family;
        String phoneNumber;
        while (true) {
            try {
                System.out.println("Enter your position (1- user/2- seller): ");
                position = scanner.nextLine();
                if (position.equals("1") || position.equals("2")) {
                    position = position.equals("1") ? "user" : "seller";
                    break;
                } else {
                    System.out.println("Invalid input");
                }

            } finally {
                System.out.println("Invalid input");
            }
        }
        while (true) {
            try {
                System.out.println("Enter your username: ");
                username = scanner.nextLine();
                if (username.matches("[a-zA-Z0-9]+")) {
                    break;
                }

            } finally {
                System.out.println("username is not valid, ");
            }
        }
        while (true) {
            try {
                System.out.println("Enter your password: ");
                password = scanner.nextLine();
                if (checkPassword(password)) {
                    break;
                } else {
                    System.out.println("password is not valid, ");
                }
            } finally {
                System.out.println("password is not valid, ");
            }
        }
        while (true) {
            try {
                System.out.println("Enter your name: ");
                name = scanner.nextLine();
                if (name.matches("[a-zA-Z]+")) {
                    break;
                } else {
                    System.out.println("name is not valid, ");
                }
            } finally {
                System.out.println("name is not valid, ");
            }
        }
        while (true) {
            try {
                System.out.println("Enter your family: ");
                family = scanner.nextLine();
                if (family.matches("[a-zA-Z]+")) {
                    break;
                } else {
                    System.out.println("family is not valid, ");
                }
            } finally {
                System.out.println("family is not valid, ");
            }
        }
        while (true) {
            try {
                System.out.println("Enter your phone number: ");
                phoneNumber = scanner.nextLine();
                // must starts with 09 and have 11 digits
                if (phoneNumber.matches("09[0-9]{9}")) {
                    break;
                } else {
                    System.out.println("phone number is not valid, ");
                }
            } finally {
                System.out.println("phone number is not valid, ");
            }
        }
        // now create user
        User user = new User();
        user.setPosition(position);
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(family);
        user.setPhone(phoneNumber);
        System.out.println("user created successfully");
        return user;

    }
}
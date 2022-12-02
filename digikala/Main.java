import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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

    public static User register() {
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

    /*
     * login menu
     */
    public static void HomeMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1- login");
        System.out.println("2- register");
        System.out.println("3- exit");
        String input = scanner.nextLine();
        if (input.equals("1")) {
            // login();
        } else if (input.equals("2")) {
            register();
        } else if (input.equals("3")) {
            System.exit(0);
        } else {
            System.out.println("invalid input");
        }
    }

    /*
     * login menu
     * login as user or seller
     */
    public static void loginMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1- login as user");
        System.out.println("2- login as seller");
        System.out.println("3- exit");
        String input = scanner.nextLine();
        if (input.equals("1")) {
            // login();
        } else if (input.equals("2")) {
            // login();
        } else if (input.equals("3")) {
            System.exit(0);
        } else {
            System.out.println("invalid input");
        }
        /*
         * signup menu
         * signup as user or seller
         */
    }

    /*
     * user menu
     * 1- Buy
     * 2- Cart
     * 3- charge account
     * 4- account information
     * -5 search using code
     * -6 Goods list
     * -7 Order
     * 8- order history
     */
    public static void userMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1- Buy");
        System.out.println("2- Cart");
        System.out.println("3- charge account");
        System.out.println("4- account information");
        System.out.println("5- search using code");
        System.out.println("6- Goods list");
        System.out.println("7- Order");
        System.out.println("8- order history");
        System.out.println("9- logout");
        String input = scanner.nextLine();
        if (input.equals("1")) {
            // login();
        } else if (input.equals("2")) {
            // login();
        } else if (input.equals("3")) {
            // login();
        } else if (input.equals("4")) {
            // login();
        } else if (input.equals("5")) {
            // login();
        } else if (input.equals("6")) {
            // login();
        } else if (input.equals("7")) {
            // login();
        } else if (input.equals("8")) {
            // login();
        } else if (input.equals("9")) {
            //
        } else {
            System.out.println("invalid input");
        }
    }

    /*
     * seller menu
     * 1- add goods
     * 2- edit goods
     * 3- delete goods
     * 4- goods list
     * 6- order history
     * 7- account information
     * 8- logout
     */
    public static void sellerMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1- add goods");
        System.out.println("2- edit goods");
        System.out.println("3- delete goods");
        System.out.println("4- goods list");
        System.out.println("5- order history");
        System.out.println("6- account information");
        System.out.println("7- logout");
        String input = scanner.nextLine();
        if (input.equals("1")) {
            // login();
        } else if (input.equals("2")) {
            // login();
        } else if (input.equals("3")) {
            // login();
        } else if (input.equals("4")) {
            // login();
        } else if (input.equals("5")) {
            // login();
        } else if (input.equals("6")) {
            // login();
        } else if (input.equals("7")) {
            // login();
        } else if (input.equals("8")) {
            // login();
        } else {
            System.out.println("invalid input");
        }
    }
}

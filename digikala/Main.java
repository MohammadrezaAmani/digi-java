import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        List<Goods> goods = new ArrayList<>();
        List<User> currentUser = new ArrayList<>();
        HomeMenu(users, goods, currentUser);

    }

    public static void HomeMenu(List<User> users, List<Goods> goods, List<User> currentUser) {
        Scanner scanner = new Scanner(System.in);
        currentUser.clear();
        System.out.println("1- login");
        System.out.println("2- register");
        System.out.println("3- exit");
        String input = scanner.nextLine();
        if (input.equals("1")) {
            login(users, goods, currentUser);
        } else if (input.equals("2")) {
            register(users, goods, currentUser);
        } else if (input.equals("3")) {
            System.exit(0);
        } else {
            System.out.println("invalid input");
        }
        scanner.close();
    }

    public static void login(List<User> users, List<Goods> goods, List<User> currentUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        scanner.close();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("login successfully");
                if (user.getPosition().equals("user")) {
                    currentUser.clear();
                    currentUser.add(user);
                    userMenu(users, goods, currentUser);
                } else {
                    currentUser.clear();
                    currentUser.add(user);
                    sellerMenu(users, goods, currentUser);
                }
            }
        }
        System.out.println("username or password is not correct");
    }

    public static void register(List<User> users, List<Goods> goods, List<User> currentUser) {
        Scanner scanner = new Scanner(System.in);
        // id = 6 random number
        int id = new Random().nextInt(1000000);
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

            } catch (Exception e) {
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

            } catch (Exception e) {
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
            } catch (Exception e) {
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
            } catch (Exception e) {
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
            } catch (Exception e) {
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
            } catch (Exception e) {
                System.out.println("phone number is not valid, ");
            }
        }
        scanner.close();
        // now create user
        User user = new User();
        user.setId(id);
        user.setPosition(position);
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(family);
        user.setPhone(phoneNumber);
        System.out.println("user created successfully");
        users.add(user);
        // remove everything from current user
        currentUser.clear();
        // add current user
        currentUser.add(user);
        if (position.equals("seller")) {
            sellerMenu(users, goods, currentUser);
        } else {
            userMenu(users, goods, currentUser);
        }

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

    public static void Buy(List<User> users, List<Goods> goods, List<User> currentUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the code of the product you want to buy: ");
        int code = scanner.nextInt();
        scanner.close();
        for (User user : users) {
            if (user.getId() == currentUser.get(0).getId()) {
                for (Goods good : goods) {
                    if (good.getCode() == code) {
                        if (good.getQuantity() > 0) {
                            good.setQuantity(good.getQuantity() - 1);
                            Cart cart = user.getCart();
                            cart.add(good);
                            user.setCart(cart);
                            System.out.println("product bought successfully");
                            userMenu(users, goods, currentUser);
                            return;
                        } else {
                            System.out.println("product is not available");
                            userMenu(users, goods, currentUser);
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void chargeAccount(List<User> users, List<User> currentUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Home many money do you want to charge your account: ");
        int money = scanner.nextInt();
        scanner.close();
        for (User user : users) {
            if (user.getId() == currentUser.get(0).getId()) {
                Payment payment = user.getPayment();
                payment.setCharge(payment.getCharge() + money);
                System.out.println("your account charged successfully");
                return;
            }
        }
    }

    public static void accountInformation(List<User> users, List<User> currentUser) {
        for (User user : users) {
            if (user.getId() == currentUser.get(9).getId()){
                System.out.println(user);
                return;
            }
        }
    }

    public static void cart(List<User> users, List<User> currentUser) {
        for (User user : users) {
            if (user.getId() == currentUser.get(9).getId()){
                Cart cart1 = user.getCart();
                System.out.println(cart1);
                return;
            }
        }
    }

    public static void searchUsingCode(List<Goods> goods) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the code of the product you want to search: ");
        int code = scanner.nextInt();
        scanner.close();
        for (Goods good : goods) {
            if (good.getCode() == code) {
                System.out.println(good);
                return;
            }
        }
        System.out.println("product not found");
    }

    public static void goodsList(List<Goods> goods) {
        for (Goods good : goods) {
            System.out.println(good);
        }
    }

    public static void order(List<User> users, List<User> currentUser) {
        for (User user : users) {
            if (user.getId() == currentUser.get(9).getId()){
                Cart cart1 = user.getCart();
                Payment payment = user.getPayment();
                if (payment.getCharge() >= cart1.getTotalPrice()) {
                    payment.setCharge(payment.getCharge() - cart1.getTotalPrice());
                    System.out.println("order successfully");
                    return;
                } else {
                    System.out.println("your account is not enough");
                    return;
                }
            }
        }
    }

    public static void orderHistory(List<User> users, List<User> currentUser) {
        for (User user : users) {
            if (user.getId() == currentUser.get(9).getId()){
                Cart cart1 = user.getCart();
                System.out.println(cart1);
                return;
            }
        }
    }

    public static void userMenu(List<User> users, List<Goods> goods, List<User> currentUser) {
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
        scanner.close();
        if (input.equals("1")) {
            Buy(users, goods, currentUser);
        } else if (input.equals("2")) {
            cart(users, currentUser);
        } else if (input.equals("3")) {
            chargeAccount(users, currentUser);
        } else if (input.equals("4")) {
            accountInformation(users, currentUser);
        } else if (input.equals("5")) {
            searchUsingCode(goods);
        } else if (input.equals("6")) {
            goodsList(goods);
        } else if (input.equals("7")) {
            order(users, currentUser);
        } else if (input.equals("8")) {
            orderHistory(users, currentUser);
        } else if (input.equals("9")) {
            HomeMenu(users, goods, currentUser);
        } else {
            System.out.println("invalid input");
        }
    }

    public static void addGoods(List<User> users, List<Goods> goods, List<User> currentUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the code of the product you want to add: ");
        int code = scanner.nextInt();
        for (Goods good : goods) {
            if (good.getCode() == code) {
                System.out.println("this code is already exist");
                scanner.close();
                return;
            }
        }
        System.out.println("Enter the name of the product you want to add: ");
        String name = scanner.nextLine();
        System.out.println("Enter the Expiration date of the product you want to add: ");
        String expirationDate = scanner.nextLine();
        System.out.println("Enter the price of the product you want to add: ");
        int price = scanner.nextInt();
        System.out.println("Enter the number of the product you want to add: ");
        int number = scanner.nextInt();
        scanner.close();
        Goods good = new Goods(code, name, expirationDate, price, number);
        good.setOwner(number);
        goods.add(good);
        System.out.println("product added successfully");
        scanner.close();
    }

    // for edit goods: seller must be the owner of the product
    public static void editGoods(List<User> users, List<Goods> goods, List<User> currentUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the code of the product you want to edit: ");
        int code = scanner.nextInt();
        for (Goods good : goods) {
            if (good.getCode() == code) {
                if (good.getOwner() == 0) {
                    System.out.println("Enter the name of the product you want to edit: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter the Expiration date of the product you want to edit: ");
                    String expirationDate = scanner.nextLine();
                    System.out.println("Enter the price of the product you want to edit: ");
                    int price = scanner.nextInt();
                    System.out.println("Enter the number of the product you want to edit: ");
                    int number = scanner.nextInt();
                    scanner.close();
                    good.setName(name);
                    good.setExpirDate(expirationDate);
                    good.setPrice(price);
                    good.setQuantity(number);
                    System.out.println("product edited successfully");
                    return;
                } else {
                    System.out.println("you are not the owner of this product");
                    scanner.close();
                    return;
                }
            }
            System.out.println("product not found");
        }
        scanner.close();
    }

    // for delete goods: seller must be the owner of the product
    public static void deleteGoods(List<User> users, List<Goods> goods, List<User> currentUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the code of the product you want to delete: ");
        int code = scanner.nextInt();
        scanner.close();
        for (Goods good : goods) {
            if (good.getCode() == code) {
                if (good.getOwner() == 0) {
                    goods.remove(good);
                    System.out.println("product deleted successfully");
                    return;
                } else {
                    System.out.println("you are not the owner of this product");
                    return;
                }
            }
            System.out.println("product not found");
        }
    }

    // for goods list: seller must be the owner of the product
    public static void goodsListSeller(List<User> users, List<Goods> goods, List<User> currentUser) {
        for (Goods good : goods) {
            if (good.getOwner() == 0) {
                System.out.println(good);
            }
        }
    }

    // for order history: seller must be the owner of the product
    public static void orderHistorySeller(List<User> users, List<Goods> goods, List<User> currentUser) {
        for (User user : users) {
            if (user.getId() == currentUser.get(9).getId()){
                Cart cart1 = user.getCart();
                System.out.println(cart1);
                return;
            }
        }
    }

    public static void sellerMenu(List<User> users, List<Goods> goods, List<User> currentUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1- add goods");
        System.out.println("2- edit goods");
        System.out.println("3- delete goods");
        System.out.println("4- goods list");
        System.out.println("5- order history");
        System.out.println("6- account information");
        System.out.println("7- logout");
        String input = scanner.nextLine();
        scanner.close();
        if (input.equals("1")) {
            addGoods(users, goods, currentUser);
        } else if (input.equals("2")) {
            editGoods(users, goods, currentUser);
        } else if (input.equals("3")) {
            deleteGoods(users, goods, currentUser);
        } else if (input.equals("4")) {
            goodsListSeller(users, goods, currentUser);
        } else if (input.equals("5")) {
            orderHistorySeller(users, goods, currentUser);
        } else if (input.equals("6")) {
            accountInformation(users, currentUser);
        } else if (input.equals("7")) {
            HomeMenu(users, goods, currentUser);
        } else {
            System.out.println("invalid input");
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pressKeyToContinue() {
        System.out.println("Press any key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }
}
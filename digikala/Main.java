import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        List<Goods> goods = new ArrayList<>();
        int currentID = 0;
        currentID += currentID + 1;
        HomeMenu(users, goods);

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

    public static void register(List<User> users, List<Goods> goods) {
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
        user.setPosition(position);
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(family);
        user.setPhone(phoneNumber);
        System.out.println("user created successfully");
        users.add(user);
        if (position.equals("seller")) {
            sellerMenu(users, goods);
        } else {
            userMenu(users, goods);
        }

    }

    public static void HomeMenu(List<User> users, List<Goods> goods) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1- login");
        System.out.println("2- register");
        System.out.println("3- exit");
        String input = scanner.nextLine();
        scanner.close();
        if (input.equals("1")) {
            login(users, goods);
        } else if (input.equals("2")) {
            register(users, goods);
        } else if (input.equals("3")) {
            System.exit(0);
        } else {
            System.out.println("invalid input");
        }
    }

    public static void login(List<User> users, List<Goods> goods) {
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
                    userMenu(users, goods);
                } else {
                    sellerMenu(users, goods);
                }
            }
        }
        System.out.println("username or password is not correct");
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
    public static void Buy(List<User> users, List<Goods> goods) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the code of the product you want to buy: ");
        int code = scanner.nextInt();
        scanner.close();
        for (User user : users) {
            if (user.getPosition().equals("user")) {
                for (Goods good : goods) {
                    if (good.getCode() == code) {
                        Cart cart1 = user.getCart();
                        cart1.add(good);
                        System.out.println("product added to cart successfully");
                        return;
                    }
                }
            }
        }
        System.out.println("product not found");
    }

    public static void chargeAccount(List<User> users) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Home many money do you want to charge your account: ");
        int money = scanner.nextInt();
        scanner.close();
        for (User user : users) {
            if (user.getId() == 0) {
                Payment payment = user.getPayment();
                payment.setCharge(payment.getCharge() + money);
                System.out.println("your account charged successfully");
                return;
            }
        }
    }

    public static void accountInformation(List<User> users) {
        for (User user : users) {
            if (user.getId() == 0) {
                System.out.println(user);
                return;
            }
        }
    }

    public static void cart(List<User> users) {
        for (User user : users) {
            if (user.getId() == 0) {
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

    public static void order(List<User> users) {
        for (User user : users) {
            if (user.getId() == 0) {
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

    public static void orderHistory(List<User> users) {
        for (User user : users) {
            if (user.getId() == 0) {
                Cart cart1 = user.getCart();
                System.out.println(cart1);
                return;
            }
        }
    }

    public static void userMenu(List<User> users, List<Goods> goods) {
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
            Buy(users, goods);
        } else if (input.equals("2")) {
            cart(users);
        } else if (input.equals("3")) {
            chargeAccount(users);
        } else if (input.equals("4")) {
            accountInformation(users);
        } else if (input.equals("5")) {
            searchUsingCode(goods);
        } else if (input.equals("6")) {
            goodsList(goods);
        } else if (input.equals("7")) {
            order(users);
        } else if (input.equals("8")) {
            orderHistory(users);
        } else if (input.equals("9")) {
            HomeMenu(users, goods);
        } else {
            System.out.println("invalid input");
        }
    }
    
    public static void addGoods(List<User> users, List<Goods> goods) {
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
    public static void editGoods(List<User> users, List<Goods> goods) {
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
    public static void deleteGoods(List<User> users, List<Goods> goods) {
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
    public static void goodsListSeller(List<User> users, List<Goods> goods) {
        for (Goods good : goods) {
            if (good.getOwner() == 0) {
                System.out.println(good);
            }
        }
    }

    // for order history: seller must be the owner of the product
    public static void orderHistorySeller(List<User> users, List<Goods> goods) {
        for (User user : users) {
            if (user.getId() == 0) {
                Cart cart1 = user.getCart();
                System.out.println(cart1);
                return;
            }
        }
    }

    public static void sellerMenu(List<User> users, List<Goods> goods) {
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
            addGoods(users, goods);
        } else if (input.equals("2")) {
            editGoods(users, goods);
        } else if (input.equals("3")) {
            deleteGoods(users, goods);
        } else if (input.equals("4")) {
            goodsListSeller(users, goods);
        } else if (input.equals("5")) {
            orderHistorySeller(users, goods);
        } else if (input.equals("6")) {
            accountInformation(users);
        } else if (input.equals("7")) {
            HomeMenu(users, goods);
        } else {
            System.out.println("invalid input");
        }
    }
}

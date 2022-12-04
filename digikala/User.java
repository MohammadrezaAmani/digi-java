public class User {
    private int id;
    private String position;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private Cart cart;
    private Payment payment;

    public User() {
    }

    public String getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public boolean setUsername(String username) {
        // must contain only letters and numbers
        if (username.matches("[a-zA-Z0-9]+")) {
            this.username = username;
            return true;
        }
        return false;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        // must have at least 8 characters and contain at least one number and Capital
        // and small letter and * % $ # @ ! characters
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
            this.password = password;
            return true;
        } else {
            System.out.println(
                    "Password must contain at least one number and Capital and small letter and * % $ # @ ! characters");
            return false;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public boolean setPhone(String phone) {
        // must start with 09 and have 11 digits
        if (phone.startsWith("09") && phone.length() == 11) {
            this.phone = phone;
            return true;
        } else {
            System.out.println("Phone number is not valid");
            return false;
        }
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
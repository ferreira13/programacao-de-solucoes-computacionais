import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private List<User> userList;
    private String userFilePath = System.getProperty("user.dir") + "/pratique/data/users.txt";

    public UserService() {
        this.userList = Persistence.readUsersFromFile(userFilePath);
    }

    private boolean userDataIsValid(String email, String name, String cpf, String password, Boolean isUnique) {
        email = email.replaceAll(",", "").toLowerCase();
        name = name.replaceAll(",", "").toUpperCase();
        cpf = cpf.replaceAll("\\.", "").replaceAll("-", "");


        boolean emailIsOk = isValidEmail(email);
        boolean nameIsOk = isValidName(name);
        boolean cpfIsOk = isValidCpf(cpf);
        boolean userExists = userExists(email) && isUnique;
        boolean passwordIsOk = isValidPassword(password);

        if (!emailIsOk) {
            System.err.println("Invalid email format");
        }
        if (!nameIsOk) {
            System.err.println("Invalid first name format, pleas provide the full name");
        }
        if (!cpfIsOk) {
            System.err.println("Invalid CPF format. Be sure to enter it as xxx.xxx.xxx-xx");
        }
        if (userExists) {
            System.err.println("User already exists");

        }
        if (!passwordIsOk) {
            System.err.println("Password contain invalid character");
        }

        return emailIsOk && nameIsOk && cpfIsOk && !userExists && passwordIsOk;
    }

    public Boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
    }

    public Boolean isValidName(String name) {
        String nameRegex = "^[a-zA-Z]+(?:\\s+[a-zA-Z]+)+$";
        Pattern namePattern = Pattern.compile(nameRegex);
        Matcher nameMatcher = namePattern.matcher(name);
        return nameMatcher.matches();
    }

    public Boolean isValidCpf(String cpf) {
        String cpfRegex = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$";
        Pattern cpfPattern = Pattern.compile(cpfRegex);
        Matcher cpfMatcher = cpfPattern.matcher(cpf);
        return cpfMatcher.matches();
    }

    public Boolean isValidPassword(String password) {
        return !password.contains(",");
    }

    public boolean userExists(String email) {
        if (userList.isEmpty()) {
            return false;
        }
        return userList.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    public List<User> getUserList() {
        return new ArrayList<>(userList);
    }

    public User getUserByEmail(String email) {

        for (User user : this.userList) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null; // User not found
    }

    public void createUser(String email, String name, String cpf, String password, LocalDate birthday) {
        cpf = cpf.replaceAll("\\.", "").replaceAll("-", "");
        if (!userDataIsValid(email, name, cpf, password, true)) {
            System.err.println("Failed to create user, please check the provided data");
        } else {
            User user = new User(email, name, cpf, password, birthday);
            this.userList.add(user);
            Persistence.writeUserToFile(userFilePath, user);
        }
    }

    public void updateUser(String email, String name, String cpf, String password, LocalDate birthday) {
        if (!userDataIsValid(email, name, cpf, password, false)) {
            System.err.println("Failed to update user, please check the provided data");
        } else {
            removeUser(email);
            createUser(email, name, cpf, password, birthday);
        }

    }

    public void removeUser(String email) {
        Persistence.removeUserFromFile(userFilePath, email);
        userList.removeIf(user -> user.getEmail().equals(email));
    }

}

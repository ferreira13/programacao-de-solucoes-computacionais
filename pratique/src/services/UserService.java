import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private List<User> userList;
    private String userFilePath = System.getProperty("user.dir") + "/data/users.txt";

    public UserService() {
        this.userList = Persistence.readUsersFromFile(userFilePath);
    }

    private boolean validateUserData(String email, String name, String cpf, String password) {
        email = email.replaceAll(",", "").toLowerCase();
        name = name.replaceAll(",", "").toUpperCase();
        cpf = cpf.replaceAll(",", "");

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        String nameRegex = "^[a-zA-Z]+(?:\\s+[a-zA-Z]+)+$";
        String cpfRegex = "^(?:(?!000\\.?000\\.?000-?00).)*$|^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";

        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        Pattern namePattern = Pattern.compile(nameRegex);
        Matcher nameMatcher = namePattern.matcher(name);
        Pattern cpfPattern = Pattern.compile(cpfRegex);
        Matcher cpfMatcher = cpfPattern.matcher(cpf);

        boolean emailIsOk = emailMatcher.matches();
        boolean nameIsOk = nameMatcher.matches();
        boolean cpfIsOk = cpfMatcher.matches();
        boolean userIsUnique = userExists(email, cpf);
        boolean passwordIsOk = !password.contains(",");

        if (!emailIsOk) {
            System.err.println("Invalid email format");
        }
        if (!nameIsOk) {
            System.err.println("Invalid first name format, pleas provide the full name");
        }
        if (!cpfIsOk) {
            System.err.println("Invalid CPF format. Be sure to enter it as xxx.xxx.xxx-xx");
        }
        if (!userIsUnique) {
            System.err.println("User already exists");

        }
        if (!passwordIsOk) {
            System.err.println("Password contain invalid character");
        }

        return emailIsOk && nameIsOk && cpfIsOk && userIsUnique && passwordIsOk;
    }

    public boolean userExists(String email, String cpf) {
        return userList.stream().anyMatch(user -> user.getEmail().equals(email) || user.getCpf().equals(cpf));
    }

    public List<User> getUserList() {
        return new ArrayList<>(userList);
    }

    public void createUser(String email, String name, String cpf, String password, LocalDate birthday) {
        if (!validateUserData(email, name, cpf, password)) {
            System.err.println("Failed to create user, please check the provided data");
        } else {
            User user = new User(email, name, cpf, password, birthday);
            this.userList.add(user);
            Persistence.writeUserToFile(userFilePath, user);
        }
    }

    public void updateUser(String email, String name, String cpf, String password, LocalDate birthday) {
        if (!validateUserData(email, name, cpf, password)) {
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
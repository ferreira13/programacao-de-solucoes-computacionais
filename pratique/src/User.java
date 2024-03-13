import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String name;
    private String cpf;
    private String birthday;
    private String password;
    private List<String> events;

    private static List<String> emailList = new ArrayList<>();
    private static List<String> cpfList = new ArrayList<>();

    public User(String email, String name, String cpf, String password, String birthday) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        String cpfRegex = "^(?:(?!000\\.?000\\.?000-?00).)*$|^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
        String birthdayRegex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";

        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        Pattern cpfPattern = Pattern.compile(cpfRegex);
        Matcher cpfMatcher = cpfPattern.matcher(cpf);
        Pattern birthdayPattern = Pattern.compile(birthdayRegex);
        Matcher birthdayMatcher = birthdayPattern.matcher(birthday);

        Boolean emailIsOk = emailMatcher.matches();
        Boolean nameIsOk = name.length() > 0;
        Boolean cpfIsOk = cpfMatcher.matches();
        Boolean birthdayIsOk = birthdayMatcher.matches();
        Boolean passwordIsOk = password.length() > 8;

        if (!emailIsOk) {
            throw new IllegalArgumentException("Por favor, verifique se o e-mail fornecido está correto");
        }
        if (!nameIsOk) {
            throw new IllegalArgumentException("Por favor, verifique se o Nome fornecido está correto");
        }
        if (!cpfIsOk) {
            throw new IllegalArgumentException("Por favor, verifique se o CPF fornecido está correto");
        }
        if (!passwordIsOk) {
            throw new IllegalArgumentException("Por favor, verifique se a senha fornecida está correta");
        }
        if (!birthdayIsOk) {
            throw new IllegalArgumentException("Por favor, verifique se a data de nascimento fornecida está correta");
        }
        if (emailList.contains(email)) {
            throw new IllegalArgumentException("email ja cadastrado");
        }
        if (cpfList.contains(cpf)) {
            throw new IllegalArgumentException("cpf ja cadastrado");
        }

        this.email = email;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.birthday = birthday;
        this.events = new ArrayList<>();

        emailList.add(email);
        cpfList.add(cpf);

    }

    // getters
    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getbirthday() {
        return this.birthday;
    }

    public Boolean checkCpfMatches(String cpf) {
        return cpf.equals(this.cpf);
    }

    public Boolean checkPasswordMatches(String password) {
        return password.equals(this.password);
    }

    public Boolean checkUserListedOnEvent(String event) {
        return this.events.contains(event);
    }

    public Boolean confirmUserInEvent(String event) {
        if (this.events.contains(event)) {
            System.err.println(String.format("User alreadly listed on event %s", event));
            return false;
        } else {
            this.events.add(event);
            System.out.println(String.format("User successfully listed on event %s", event));
            return true;
        }
    }

}
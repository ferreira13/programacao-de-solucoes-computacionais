import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

/*******************************************************
 * TODO:
 * check if user is unique before creation
 * 
 *******************************************************/

public class User {
    public String email;
    public String name;
    private String cpf;
    private String birthDate;
    private String password;
    public List<String> events;

    public User(String email, String name, String cpf, String password, String birthDate) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        String cpfRegex = "^(?:(?!000\\.?000\\.?000-?00).)*$|^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
        String birthDateRegex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";

        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        Pattern cpfPattern = Pattern.compile(cpfRegex);
        Matcher cpfMatcher = cpfPattern.matcher(cpf);
        Pattern birthDatePattern = Pattern.compile(birthDateRegex);
        Matcher birthDateMatcher = birthDatePattern.matcher(birthDate);

        Boolean emailIsOk = emailMatcher.matches();
        Boolean nameIsOk = name.length() > 0;
        Boolean cpfIsOk = cpfMatcher.matches();
        Boolean birthDateIsOk = birthDateMatcher.matches();
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
        if (!birthDateIsOk) {
            throw new IllegalArgumentException("Por favor, verifique se a data de nascimento fornecida está correta");
        }

        this.email = email;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.birthDate = birthDate;
        this.events = new ArrayList<>();

    }

    public Boolean checkCpfMatches(String cpf) {
        return cpf.equals(this.cpf);
    }

    public Boolean checkPasswordMatches(String password) {
        return password.equals(this.password);
    }

    public Boolean checkIsUserBirthday(String date) {
        return date.equals(this.birthDate);
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
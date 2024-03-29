import java.time.LocalDate;

public class User {
    private String email;
    private String name;
    private String cpf;
    private LocalDate birthday;
    private String password;


    public User(String email, String name, String cpf, String password, LocalDate birthday) {
        this.email = email;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.birthday = birthday;
    }

    public User() {

    }

    // getters
    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getPassword() {
        return this.password;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    // setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
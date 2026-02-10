package ru.praktikumService.qascooter;

//Класс для сериализации даных для авторизации
public class CourierAuthorization {
    String login;
    String password;

    public CourierAuthorization(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public CourierAuthorization() {
    }

    public static CourierAuthorization from(Courier courier) {
        return new CourierAuthorization(courier.getLogin(), courier.getPassword());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

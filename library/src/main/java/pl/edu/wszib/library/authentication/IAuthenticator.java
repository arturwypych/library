package pl.edu.wszib.library.authentication;

public interface IAuthenticator {
    boolean authenticate(String username, String password);
}

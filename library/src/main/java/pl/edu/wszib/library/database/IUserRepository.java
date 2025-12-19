package pl.edu.wszib.library.database;

import pl.edu.wszib.library.model.User;

import java.util.List;

public interface IUserRepository {
    User findUserByLogin(String login);
}

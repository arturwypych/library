package pl.edu.wszib.library.authentication;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import pl.edu.wszib.library.database.IUserRepository;
import pl.edu.wszib.library.model.User;

@Component
@RequiredArgsConstructor
public class Authenticator implements IAuthenticator{
    private final IUserRepository userRepository;

    @Override
    public boolean authenticate(String username, String password) {
        User user = this.userRepository.findUserByLogin(username);
        if(user != null && user.getPassword().equals(DigestUtils.md5Hex(password))) {
            return true;
        }
        return false;

    }
}

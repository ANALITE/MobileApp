package analite.mobileapp.data.persistence;

import analite.mobileapp.data.entities.User;

public interface UserRepository {

    User findUserById(int user_id);

    User findUserByEmail(String user_email);

    void createUser(String user_nick, String user_email, String user_Pass);
}

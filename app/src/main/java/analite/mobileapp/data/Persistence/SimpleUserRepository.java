package analite.mobileapp.data.Persistence;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import analite.mobileapp.data.entities.User;

public class SimpleUserRepository implements UserRepository {
    private List<User> dummy_credentials = new ArrayList<User>();

    public SimpleUserRepository() {
        User user1 = new User();User user2 = new User();User user3 = new User();
        user1.setId(1);user1.setEmail("jossiemt@gmail.com");user1.setName("Jossie");user1.setPassword("jossie");
        user2.setId(2);user2.setEmail("sebastian.mr47@gmail.com");user2.setName("Sebastian");user2.setPassword("sebastian");
        user3.setId(3);user3.setEmail("camilotorres.9576@gmail.com");user3.setName("Camilo");user3.setPassword("camilo");
        this.dummy_credentials.add(user1);
        this.dummy_credentials.add(user2);
        this.dummy_credentials.add(user3);
    }

    @Override
    public User findUserById(int user_id) {
        for (User posUser : dummy_credentials){
            if (posUser.getId()==user_id){
                return posUser;
            }
        }
        return null;
    }

    @Override
    public User findUserByEmail(String user_email) {
        for (User posUser : dummy_credentials){
            if (posUser.getEmail().equals(user_email)){
                return posUser;
            }
        }
        return null;
    }

    @Override
    public void createUser(String user_nick, String user_email, String user_Pass) {
        User new_user = new User();
        new_user.setId(dummy_credentials.size()+1);new_user.setEmail(user_email);new_user.setName(user_nick);new_user.setPassword(user_Pass);
        dummy_credentials.add(new_user);
    }

}

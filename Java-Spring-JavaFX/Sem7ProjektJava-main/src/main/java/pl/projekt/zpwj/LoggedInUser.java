package pl.projekt.zpwj;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.projekt.zpwj.model.User;

@Component
@NoArgsConstructor
public class LoggedInUser{
    private static User loggedInUser;

    public static User getInstance(){
        if (null == loggedInUser){
            loggedInUser = new User();
        }
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        LoggedInUser.loggedInUser = loggedInUser;
    }
}

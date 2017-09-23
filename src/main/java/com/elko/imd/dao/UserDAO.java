/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.dao;

import com.elko.imd.model.Friendship;
import com.elko.imd.model.GetFriend;
import com.elko.imd.model.NewsFeed;
import com.elko.imd.model.NewsRecipient;
import com.elko.imd.model.Subscribe;
import com.elko.imd.model.User;
import com.elko.imd.model.UserAction;
import java.util.List;
import javax.validation.Valid;
import org.hibernate.validator.constraints.Email;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author elko
 */

@Validated
public interface UserDAO {
    public List<User> IsFriend(@Email(message = "use email format") String userOne,@Email(message = "use email format") String userTwo) ;
    public List<Friendship> getFriend(@Email(message = "use email format") String user) ;
    public boolean subscribe(@Valid UserAction userAction,int status);
    public List<Subscribe> getSubscribe (@Email(message = "use email format") String email);
    public boolean postNews (@Valid NewsFeed newsFeed);
}

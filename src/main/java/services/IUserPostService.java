package services;

import models.UserModel;
import models.UserPostModel;

import java.util.List;

public interface IUserPostService {
    UserPostModel insert (UserPostModel post);
    UserPostModel update (UserPostModel post);
    void delete (UserPostModel post);

    UserPostModel selectById (Long id);

    List<UserPostModel> selectAll ();
    List<UserPostModel> selectByUser (UserModel user);

    Long selectNumberOfAllPost ();
    Long selectNumberOfPostUser (UserModel user);
}

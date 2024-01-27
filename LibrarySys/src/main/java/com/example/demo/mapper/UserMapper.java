package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.demo.entities.User;
import com.example.demo.controlers.resources.UserRes;

import java.util.List;

@Mapper(uses = {BookMapper.class, FineMapper.class})
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    User userToResuorce(UserRes resource);

    UserRes userToUserRes(User user);

    List<UserRes> toUserResList(List<User> users);

    public static UserRes mapUserToUserRes(User user) {
        UserRes userRes = new UserRes();
        userRes.setId(user.getId());
        userRes.setName(user.getName());
        userRes.setAddress(user.getAddress());
        userRes.setEmail(user.getEmail());
        userRes.setPhone(user.getPhone());
        for (int i = 0; i < user.getBorrowedBooks().size(); i++) {
            userRes.getBorrowedBooks().add(user.getBorrowedBooks().get(i));
        };
        for (int i = 0; i < user.getFines().size(); i++) {
            userRes.getFines().add(user.getFines().get(i));
        }
        return userRes;
    }
}

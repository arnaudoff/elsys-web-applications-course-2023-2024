package com.example.demo.services.implementation;

import com.example.demo.controlers.resources.UserRes;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.BookService;
import com.example.demo.services.FineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.services.UserService;
import java.util.List;

import static com.example.demo.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final FineService fineService;
    private final BookService bookService;

    @Override
    public List<UserRes> AllUsers() {
        return USER_MAPPER.toUserResList(userRepository.findAll());
    }

    @Override
    public UserRes findById(Long id) {
        return USER_MAPPER.userToUserRes(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserRes save(UserRes user) {
        User userTemp = userRepository.save(USER_MAPPER.userToResuorce(user));
        user.setId(userTemp.getId());
        user.setBorrowedBooks(null);
        user.setFines(null);

        return user;
    }

    @Override
    public UserRes update(User user, Long id) {
        User userTemp = userRepository.save(USER_MAPPER.userToResuorce(user));

        userTemp.setName(userTemp.getName());
        userTemp.setAddress(userTemp.getAddress());
        userTemp.setPhone(userTemp.getPhone());
        bookService.updateAll(user.getBorrowedBooks(), userTemp.getBorrowedBooks());
        fineService.updateAll(user.getFines(), userTemp.getFines());

        return USER_MAPPER.userToUserRes(userRepository.save(userTemp));

    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}

package com.example.nvh_Website.service.impl;

import com.example.nvh_Website.dto.*;
import com.example.nvh_Website.entity.User;
import com.example.nvh_Website.repository.BookingRepo;
import com.example.nvh_Website.repository.UserRepo;
import com.example.nvh_Website.service.UserService;
import com.example.nvh_Website.utilities.ConvertUserToDto;
import com.example.nvh_Website.utilities.SessionUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;//thu vien so sanh mat khau

import java.util.stream.Collectors;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    BookingRepo bookingRepo;
    @Override
    public Page<UserDTO> findAllUser(String sdt, String email, String ho_ten, Pageable pageable) {
        Page<User> page= userRepo.findAll(sdt,email,ho_ten, pageable);
        Page<UserDTO> userDTOPage= new PageImpl<>(
                page.getContent().stream().map(user -> {
                    UserDTO userDTO= ConvertUserToDto.convertUsertoDto(user);
                    return userDTO;
                }).collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
        return userDTOPage;
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> user= userRepo.findById(id);
        if(user.isPresent()){
            return user.get();
        }

        return null;
    }

    @Override
    public User findUserByUsername(String username) {
       User user=userRepo.findByUsername(username);
        if(user!=null){
            return user;
        }
        return null;
    }

    @Override
    public boolean saveUser(User user) {
        if(this.userRepo.save(user)!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean login(LoginDTO user) {
        User userCheck = this.findUserByUsername(user.getUsername());

        if(userCheck==null) {
            return false;
        }

        log.info("userCheck:{}",userCheck.getUsername());

        if(BCrypt.checkpw(user.getPassword(), userCheck.getPassword())) {
            SessionUtilities.setUsername(userCheck.getUsername());
            SessionUtilities.setUser(ConvertUserToDto.convertUsertoDto(userCheck));

            log.info("userCheck:{}",SessionUtilities.getUsername());//luu lai thong tin vao session
            return true;
        }

        return false;
    }

    @Override
    public boolean register(RegisterDTO user) {
        User userCheckByUserName = this.findUserByUsername(user.getUsername());
        User userCheckByEmail = this.userRepo.getUserByEmail(user.getEmail());
        if(userCheckByUserName!=null || userCheckByEmail!=null) {
            return false;
        }

        User newuser= new User();
        newuser.setUsername(user.getUsername());
        newuser.setHo_ten(user.getHo_ten());
        newuser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        newuser.setEmail(user.getEmail());
        newuser.setGioi_tinh(user.getGioi_tinh());
        newuser.setRole(1);
        newuser.setDia_chi(user.getDia_chi());
        newuser.setSdt(user.getSdt());


        return this.saveUser(newuser);
    }

    @Override
    public boolean updateUser(UpdateUserDTO updateUserDTO) {
        if(SessionUtilities.getUser()!=null) {
            Long user_id = SessionUtilities.getUser().getId();

            User user = this.userRepo.findById(user_id).get();

            user.setSdt(updateUserDTO.getSdt());
            user.setUsername(updateUserDTO.getUsername());
            user.setEmail(updateUserDTO.getEmail());
            user.setDia_chi(updateUserDTO.getDia_chi());
            user.setHo_ten(updateUserDTO.getHo_ten());
            user.setGioi_tinh(updateUserDTO.getGioi_tinh());
            this.userRepo.save(user);
            SessionUtilities.setUser(ConvertUserToDto.convertUsertoDto(user));
            return true;

        }
        return false;
    }

    @Override
    public boolean deleteUserById(Long id) {
        Optional<User> user = this.userRepo.findById(id);
        if(user.isPresent()) {
            if(this.bookingRepo.findBookingByUserId(id)==null || this.bookingRepo.findBookingByUserId(id).size()==0) {
                this.userRepo.deleteById(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin() {
        return SessionUtilities.getUsername()!=null;
    }

    @Override
    public boolean changePassword(ChangePasswordDTO changePasswordDTO) {
        if(SessionUtilities.getUser()!=null) {
            Long user_id = SessionUtilities.getUser().getId();

            User user = this.userRepo.findById(user_id).get();

            if(BCrypt.checkpw(changePasswordDTO.getOldPassword(),user.getPassword()) && changePasswordDTO.getNewPassword()!=null) {
                user.setPassword(BCrypt.hashpw(changePasswordDTO.getNewPassword(), BCrypt.gensalt(10)));
                this.userRepo.save(user);
                return true;
            }
            return false;

        }
        return false;
    }

    @Override
    public boolean updateUserByAdmin(UpdateUserDTO updateUserDTO, Long id) {
        return false;
    }

    @Override
    public boolean adminLogin(LoginDTO user) {
        User userCheck = this.findUserByUsername(user.getUsername());

        if(userCheck==null) {
            return false;
        }

        log.info("userCheck:{}",userCheck.getUsername());

        if(BCrypt.checkpw(user.getPassword(), userCheck.getPassword()) && userCheck.getRole()==0) {

            SessionUtilities.setAdmin(ConvertUserToDto.convertUsertoDto(userCheck));

            log.info("userCheck:{}",SessionUtilities.getAdmin().getUsername());

            return true;
        }


        return false;
    }

    @Override
    public boolean checkAdminLogin() {
        return SessionUtilities.getAdmin()!=null;
    }

    @Override
    public void adminLogout() {
        SessionUtilities.setAdmin(null);
    }

    @Override
    public boolean resetPass(Long id) {
        User user = this.userRepo.findById(id).get();

        user.setPassword(BCrypt.hashpw("123@123a", BCrypt.gensalt(10)));

        if(this.userRepo.save(user)!=null) {
            return true;
        }
        return false;
    }
}

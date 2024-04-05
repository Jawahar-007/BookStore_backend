package com.jawa.bookstoreapp.user.service;

import com.jawa.bookstoreapp.user.dto.LoginDTO;
import com.jawa.bookstoreapp.user.dto.ResetPasswordDTO;
import com.jawa.bookstoreapp.user.entity.UserEntity;
import com.jawa.bookstoreapp.user.repositories.UserRepository;
import com.jawa.bookstoreapp.user.util.EmailSender;
import com.jawa.bookstoreapp.user.util.UserJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository repo;

    @Autowired
    UserJWT jwt;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    EmailSender emailSender;

    @Override
    public String userRegister(UserEntity userEntity) {
        String encryptPassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encryptPassword);

        repo.save(userEntity);

        String toEmail = userEntity.getEmail();
        String body = "Thanks for registering in the Bookstore app. Click here to verify your account: http://localhost:8081/register";
        String subject = "Verification Pending";
        System.out.println(userEntity.getEmail());
        emailSender.sendMail(toEmail, subject, body);
        return "User Registration Done !!!!";
    }

    @Override

    public String userLogin(LoginDTO loginDTO) {

//        String encodePassword = passwordEncoder.encode(loginDTO.getUserPassword());
//      System.out.println(encodePassword);

      UserEntity userEntity = repo.findByEmailID(loginDTO.getUserEmailId());
      if(userEntity !=null && passwordEncoder.matches(loginDTO.getUserPassword(),userEntity.getPassword())){
        String token = jwt.createToken(userEntity.getFirstname());
        String body = "Registered Succesfully";
        String subject = "account verified";
        emailSender.sendMail(loginDTO.getUserEmailId(),subject,body);
        return "Login Successful. JWT token: "+ token;
      }
      else {
            return "Invalid Credentials. Login Failed .";
      }
    }
    @Override
    public void sendOTPToEmail(String email){
        String otp = generateOTP();

        // Send OTP to EMAIL
        String body = "Your OTP for Password reset : " + otp;
        String subject = "Reset Password OTP";
        emailSender.sendMail(email,subject,body);

        // store otp to database
        UserEntity userEntity = repo.findByEmailID(email);
        if(userEntity!= null){
            userEntity.setOtp(otp);
            repo.save(userEntity);
        }
        else {
            throw new RuntimeException("User not found at email : " + email);
        }
    }


    @Override
    public void resetPassword(String email,String otp , String newPassword){
        UserEntity userEntity = repo.findByEmailID(email);
        // check
        if(userEntity != null && validateOtp(email,otp)){
            // password reset
            String encryptPassword = passwordEncoder.encode(newPassword);
            userEntity.setPassword(encryptPassword);
            // clearing OTP after Password reset
            userEntity.setOtp(null);
            repo.save(userEntity);
            String body = "Your password has been changed successfully.";
            String subject = "Password changed ";
            emailSender.sendMail(email,subject,body);
        }
        else {
            throw new RuntimeException("Invalid OTP or email");
        }
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity deleteId = repo.findById(id).orElseThrow( () -> new RuntimeException("Employee ID not Found"));

            repo.deleteById(deleteId.getId());

    }

    private boolean validateOtp(String email, String otp)
    {   UserEntity userEntity = repo.findByEmailID(email);
        return userEntity != null && userEntity.getOtp() != null && userEntity.getOtp().equals(otp);
    }
    private String generateOTP(){
        Random random = new Random();
        int otpNumber = 100000 + random.nextInt(900000);
        return String.valueOf(otpNumber);
    }
}



//    @Override
//    public String userLogin(LoginDTO loginDTO) {
//
//
//
//        return null;
//    }
//
//    @Override
//    public Optional<UserEntity> getUserByJWT(String token) {
//        return Optional.empty();
//    }
//@Override
//public List<UserRegistrationDTO> getallUser() {
//    return repo.findAll()
//            .stream()
//            .map(UserEntity -> new UserRegistrationDTO(
//                    UserEntity.getId(),
//                    UserEntity.getFirstname(),
//                    UserEntity.getLastname(),
//                    UserEntity.getDob()
//            )).collect(Collectors.toList());
//}
//
//    public List<UserLoginDto> getUserById(@PathVariable int id){
//       return repo.findById(id).stream()
//               .map(userEntity -> new UserLoginDto(
//                       userEntity.getUserId(),
//                       userEntity.getUserFirstName(),
//                       userEntity.getUserLastName()
//
//               )).collect(Collectors.toList());
//       // return repo.findById(id).get();
//    }
//
//    public void postUser(@RequestBody UserEntity userEntity){
//        repo.save(userEntity);
//    }
//
//    public UserEntity putUserDetails(@PathVariable int id){
//        UserEntity userEntity = repo.findById(id).get();
//        userEntity.setUserFirstName("John");
//        userEntity.setUserLastName("Doe");
//        repo.save(userEntity);
//        return userEntity;
//    }
//    public void deleteUser(@PathVariable int id){
//        UserEntity userEntity = repo.findById(id).get();
//        repo.delete(userEntity);
//    }

//}

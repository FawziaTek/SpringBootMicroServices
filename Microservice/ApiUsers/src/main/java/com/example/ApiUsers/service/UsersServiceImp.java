package com.example.ApiUsers.service;

import com.example.ApiUsers.data.UserEntity;
import com.example.ApiUsers.data.UserRepository;
import com.example.ApiUsers.shared.UserDto;
import org.bouncycastle.jcajce.provider.asymmetric.dh.BCDHPrivateKey;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service

public class UsersServiceImp implements UsersService {

    UserRepository userRepository;

        BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UsersServiceImp(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository=userRepository;
        this.bCryptPasswordEncoder =bCryptPasswordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity=modelMapper.map(userDetails, UserEntity.class);
       // userEntity.setEncryptedPassword("test");


        userRepository.save(userEntity);
        UserDto returnValue = modelMapper.map(userEntity,UserDto.class);
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       // userRepository.findByEmail(username);
     UserEntity userEntity=   userRepository.findByEmail(username);
     if(userEntity == null) throw new UsernameNotFoundException(username);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }

    public UserDto getUserDetailByEmail(String email)  {

        UserEntity userEntity=   userRepository.findByEmail(email);
        if(userEntity == null) throw new UsernameNotFoundException(email);

        return new ModelMapper().map(userEntity,UserDto.class);   }


}

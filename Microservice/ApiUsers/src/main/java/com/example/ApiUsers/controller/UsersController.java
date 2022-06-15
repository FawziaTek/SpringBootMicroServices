package com.example.ApiUsers.controller;
import org.springframework.http.MediaType;
import com.example.ApiUsers.model.CreateUserModel;
import com.example.ApiUsers.model.CreateUserResponseModel;
import com.example.ApiUsers.service.UsersService;
import com.example.ApiUsers.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private Environment env;
    @Autowired
    UsersService usersService;

    @GetMapping("/status/check")
    public String status(){
        return "Working on port "+env.getProperty("local.server.port");
    }

    @PostMapping (consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
   /* public String createUser(@Valid @RequestBody  CreateUserModel userDetails)
        {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            UserDto userDto = modelMapper.map(userDetails,UserDto.class);
            usersService.createUser(userDto);
            return "Create user method is called ";
        }
*/
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody  CreateUserModel userDetails)
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userDetails,UserDto.class);
        UserDto createUser = usersService.createUser(userDto);

        CreateUserResponseModel returnValue =modelMapper.map(createUser,CreateUserResponseModel.class);

        return  ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }


}

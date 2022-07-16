package com.tinie.Update.User.controllers;

import com.tinie.Update.User.models.UserDetails;
import com.tinie.Update.User.repositories.UserDetailsRepository;
import com.tinie.Update.User.requests.UpdateUserRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class UpdateController {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    /**
     * Write new user's phone number and name to database
     * @param httpServletRequest An object of type {@link HttpServletRequest} containing all the information about the request.
     * @param requestBody {@link UpdateUserRequest} containing phone number and username
     * @return A {@link Response} whose payload is a {@link Map}.
     * */
    @PostMapping("add-new-user")
    @ApiOperation(value = "Add new User details to database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "WRITE SUCCESSFUL")
    })
    public ResponseEntity<?> addNewUser(HttpServletRequest httpServletRequest,
                                                           @RequestBody UpdateUserRequest requestBody) {
        var dataMap = new HashMap<String, Object>();

        dataMap.put("phonenumber", requestBody.getPhonenumber());
        dataMap.put("usernamewritestatus", "OK");
        var savedUser = userDetailsRepository
                .findByPhoneNumberAndUsernameIgnoreCase(requestBody.getPhonenumber(), requestBody.getUsername());

        if (savedUser.isPresent()) {
            dataMap.put("username", requestBody.getUsername());
        } else {
            var userDetails = new UserDetails(requestBody.getPhonenumber(), requestBody.getUsername());
            userDetailsRepository.save(userDetails);
            dataMap.put("username", "New User");
        }

        return ResponseEntity.ok(dataMap);
    }
}

package com.example.gameserver;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Save new userName related to current userId into database
     * @param user
     * @return success if saved else fail
     */
    @PostMapping("/saveUser")
    @CrossOrigin("*")
    public String saveUser(@RequestBody User user){
        if (user == null){
            return "fail";
        }
        this.userRepository.save(user);
        return "success";
    }

    /**
     * Find all userName created by the same userId
     * @param json {"userId" : "userId returned by Google"}
     * @return return all userName related to this userId
     */
    @PostMapping("/findAllUserName")
    @ResponseBody
    @CrossOrigin("*")
    public List<User> findAllUserNameByUserId(@RequestBody Map<String, String> json){
        if (json == null) {
            return Collections.emptyList();
        }
        return this.userRepository.findAllByUserId(json.get("userId"));
    }
}

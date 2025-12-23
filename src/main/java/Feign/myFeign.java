package Feign;

import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface myFeign {
    @RequestLine("GET /users?name={name}")
    List<String> getAll(@Param("name") String name);

    @RequestLine("POST /users/add?name={name}")
    void addUserName(@RequestBody String name);

    @RequestLine("POST /users/delete?name={name}")
    void deleteUserName(@RequestBody String name);

}

package ru.genzo.spring.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.genzo.spring.rest.entity.User;

import java.util.List;

@Component
public class Communication {

    private RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders httpHeaders = new HttpHeaders();
    private final String URL = "http://94.198.50.185:7081/api/users";
    private String SESSION_ID;

    public List<User> getAllUsers() {

        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null
                , new ParameterizedTypeReference<List<User>>() {
                });

        List<User> allUsers = responseEntity.getBody();
        List<String> strings = responseEntity.getHeaders().get("Set-Cookie");
        SESSION_ID = strings.get(0).substring(0, strings.get(0).indexOf(';'));
        return allUsers;
    }

    public void saveUser(User user) {
        httpHeaders.set("Cookie", SESSION_ID);
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        System.out.println("first part of code - " + exchange.getBody());
    }

    public void updateUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        System.out.println("second part of code - " + exchange.getBody());
    }

    public void deleteUser(Long id) {
        HttpEntity<User> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class);
        System.out.println("third part of code - " + exchange.getBody());
    }

}

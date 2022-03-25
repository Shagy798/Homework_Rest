package com.example.demo.service;

import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {

    private static final String SALARY_KEY = "salary";

    @Value("#{'${user-service-url}' + '${report-service-path}'}")
    private String userServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    public Employee getById(String id){
        String url = userServiceUrl + "/{id}";
        return restTemplate.getForObject(url, Employee.class, id);
    }

    public ResponseEntity<String> setSalary(String id, long salary)
    {
        String url = userServiceUrl + "/{id}";
        Map<String, Long> reguestBody = new HashMap<>();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/merge-patch+json");
        reguestBody.put(SALARY_KEY, salary);

        HttpEntity request = new HttpEntity(reguestBody, headers);
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate.exchange(url, HttpMethod.PATCH, request, String.class, id);

    }
}




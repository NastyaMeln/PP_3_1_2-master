package ru.kata.spring.boot_security.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleConverter implements Converter<String, Role> {

    @Override
    public Role convert(String id) {
        long parseId = Integer.parseInt(id);
        for (Role role: Role.values()) {
            if (role.ordinal() == parseId) {
                return role;
            }
        }
        return null;
    }
}

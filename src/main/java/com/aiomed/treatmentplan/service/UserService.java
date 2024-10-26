package com.aiomed.treatmentplan.service;

import com.aiomed.treatmentplan.model.User;

public interface UserService {

    User create(User user);
    User update(User user);
    User findById(Integer id);
    void deleteById(Integer id);

}

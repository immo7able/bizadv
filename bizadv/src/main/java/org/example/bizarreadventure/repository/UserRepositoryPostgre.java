package org.example.bizarreadventure.repository;

import org.example.bizarreadventure.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryPostgre implements UserRepository{
    @Override
    public void storeUser(User user) {

    }

}

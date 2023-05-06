package com.example.htmlproduct.repo;

import com.example.htmlproduct.models.CartData;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository

public interface PostRepository extends CrudRepository<CartData, Long> {
}

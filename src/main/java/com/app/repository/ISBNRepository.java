package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.ISBN;

@Repository
public interface ISBNRepository extends JpaRepository<ISBN, String>{}

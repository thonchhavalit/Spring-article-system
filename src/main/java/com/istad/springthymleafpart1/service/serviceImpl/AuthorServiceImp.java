package com.istad.springthymleafpart1.service.serviceImpl;

import com.istad.springthymleafpart1.model.Author;
import com.istad.springthymleafpart1.repository.AuthorRepo;
import com.istad.springthymleafpart1.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorServiceImp implements AuthorService {

    private AuthorRepo authorRepo;
    AuthorServiceImp(){
        authorRepo = new AuthorRepo();
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepo.getAllAuthors();
    }
}

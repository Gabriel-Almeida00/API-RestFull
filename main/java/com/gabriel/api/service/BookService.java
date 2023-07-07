package com.gabriel.api.service;

import com.gabriel.api.controller.BookController;
import com.gabriel.api.data.vo.v1.BookVO;
import com.gabriel.api.exceptions.RequiredObjectIsNullException;
import com.gabriel.api.exceptions.ResourceNotFoundException;
import com.gabriel.api.model.Book;
import com.gabriel.api.repository.BookRepository;
import com.gabriel.api.service.mapper.DozerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookVO> findAll(){
        var books = DozerMapper.parseListObjects(bookRepository.findAll(), BookVO.class);
        books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
        return books;
    }

    public BookVO findById(int id){
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    public BookVO create (BookVO bookVO){
        if(bookVO == null) throw  new RequiredObjectIsNullException();
        var entity = DozerMapper.parseObject(bookVO, Book.class);
        var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO bookVO){
        if(bookVO == null) throw new RequiredObjectIsNullException();
        var entity = bookRepository.findById(bookVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setAuthor(bookVO.getAuthor());
        entity.setLaunch_date(bookVO.getLaunch_date());
        entity.setTitle(bookVO.getTitle());
        entity.setPrice(bookVO.getPrice());

        var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(int id){
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        bookRepository.delete(entity);
    }

}

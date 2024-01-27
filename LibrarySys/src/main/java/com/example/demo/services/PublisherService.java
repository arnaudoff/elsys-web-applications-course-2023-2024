package com.example.demo.services;

import com.example.demo.controlers.resources.PublisherRes;
import com.example.demo.entities.Publisher;

import java.util.List;

public interface PublisherService {
    List<PublisherRes> AllPublishers();
    PublisherRes findById(Long id);
    PublisherRes save(PublisherRes publisher);
    PublisherRes update(Publisher publisher, Long id);
    void deleteById(Long id);
}

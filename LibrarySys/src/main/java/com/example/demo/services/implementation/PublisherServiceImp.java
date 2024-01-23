package com.example.demo.services.implementation;

import com.example.demo.controlers.resources.PublisherRes;
import com.example.demo.entities.Publisher;
import com.example.demo.repository.PublisherRepository;
import com.example.demo.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.services.PublisherService;
import java.util.List;

import static com.example.demo.mapper.PublisherMapper.PUBLISHER_MAPPER;

@Service
@RequiredArgsConstructor
public class PublisherServiceImp implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final BookService bookService;

    @Override
    public List<PublisherRes> AllPublishers() {
        return PUBLISHER_MAPPER.toPublisherResList(publisherRepository.findAll());
    }

    @Override
    public PublisherRes findById(Long id) {
        return PUBLISHER_MAPPER.publisherToPublisherRes(publisherRepository.findById(id).orElse(null));
    }

    @Override
    public PublisherRes save(PublisherRes publisherRes) {
        Publisher publisherTemp = publisherRepository.save(PUBLISHER_MAPPER.publisherToResuorce(publisherRes));
        publisherRes.setId(publisherTemp.getId());
        publisherRes.setBooks(null);

        return publisherRes;
    }

    @Override
    public PublisherRes update(Publisher publisher, Long id) {
        Publisher publisherTemp = publisherRepository.getReferenceById(id);

            publisherTemp.setName(publisher.getName());
            publisherTemp.setAddress(publisher.getAddress());
            publisherTemp.setPhone(publisher.getPhone());
            publisherTemp.setEmail(publisher.getEmail());
            bookService.updateAll(publisher.getBooks(), publisherTemp.getBooks());

        return PUBLISHER_MAPPER.publisherToPublisherRes(publisherRepository.save(publisherTemp));
    }

    @Override
    public void deleteById(Long id) {
        publisherRepository.deleteById(id);
    }

}

package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.example.demo.entities.Publisher;
import com.example.demo.controlers.resources.PublisherRes;

import java.util.List;

@Mapper
public interface PublisherMapper {
    PublisherMapper PUBLISHER_MAPPER = Mappers.getMapper(PublisherMapper.class);

    @Mapping(target = "books", source = "resource.books")
    Publisher publisherToResuorce(PublisherRes resource);

    @Mapping(target = "books", source = "publisher.books")
    PublisherRes publisherToPublisherRes(Publisher publisher);
    List<PublisherRes> toPublisherResList(List<Publisher> publishers);
}

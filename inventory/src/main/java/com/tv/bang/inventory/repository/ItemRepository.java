package com.tv.bang.inventory.repository;

import com.tv.bang.inventory.domain.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "items", path = "items")
public interface ItemRepository extends MongoRepository<Item, String> {

    List<Item> findByIdIn(@Param("ids") List<String> ids);
}

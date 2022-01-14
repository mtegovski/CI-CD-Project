package mk.finki.ukim.wp.lab.repository.impl;


import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {
    public List<Manufacturer> findAll() {
        return DataHolder.manufacturers;
    }
    public Optional<Manufacturer> findById(Long id) {
        return DataHolder.manufacturers.stream().filter(m -> m.getId().equals(id)).findFirst();
    }
}
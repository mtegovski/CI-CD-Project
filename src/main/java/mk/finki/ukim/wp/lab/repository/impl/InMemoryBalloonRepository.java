//package mk.finki.ukim.wp.lab.repository.impl;
//
//import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
//import mk.finki.ukim.wp.lab.model.Balloon;
//import mk.finki.ukim.wp.lab.model.Manufacturer;
//import org.springframework.stereotype.Repository;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class InMemoryBalloonRepository {
//
//    public InMemoryBalloonRepository() {}
//
//    public List<Balloon> findAllBalloons() {
//        return DataHolder.baloons.stream().sorted(Comparator.comparing(Balloon::getName)).collect(Collectors.toList());
//    }
//
//    public List<Balloon> findAllByNameOrDescription(String text) {
//        return DataHolder.baloons.stream()
//                .filter(balloon -> balloon.getName().contains(text) || balloon.getDescription().contains(text))
//                .collect(Collectors.toList());
//    }
//
//    public Optional<Balloon> findById(Long id) {
//        return DataHolder.baloons.stream().filter(p -> p.getId().equals(id)).findFirst();
//    }
//
//    public Optional<Balloon> save(Long id,String name,String description,Manufacturer manufacturer) {
//        DataHolder.baloons.removeIf(b -> b.getId().equals(id));
//        Balloon balloon = new Balloon(name,description,manufacturer);
//        DataHolder.baloons.add(balloon);
//        return Optional.of(balloon);
//    }
//
//    public void delete(Long id) {
//        DataHolder.baloons.removeIf(balloon -> balloon.getId().equals(id));
//    }
//
//}

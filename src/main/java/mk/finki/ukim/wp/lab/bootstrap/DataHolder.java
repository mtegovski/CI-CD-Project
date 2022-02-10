package mk.finki.ukim.wp.lab.bootstrap;

import mk.finki.ukim.wp.lab.model.Balloon;
import mk.finki.ukim.wp.lab.model.Manufacturer;
import mk.finki.ukim.wp.lab.repository.jpa.BalloonRepository;
import mk.finki.ukim.wp.lab.repository.jpa.ManufacturerRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataHolder {

    private final ManufacturerRepository manufacturerRepository;
    private final BalloonRepository balloonRepository;

    public DataHolder(ManufacturerRepository manufacturerRepository, BalloonRepository balloonRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.balloonRepository = balloonRepository;
    }

    public void init(){
        manufacturerRepository.save(new Manufacturer(1L,"Nike","Serbia","Belgrade"));
        manufacturerRepository.save(new Manufacturer(2L,"Adidas","Croatia","Zagreb"));

        Manufacturer m1 = manufacturerRepository.findAll().get(0);
        Manufacturer m2 = manufacturerRepository.findAll().get(1);

        balloonRepository.save(new Balloon(1L,"Black","The color black",m1));
        balloonRepository.save(new Balloon(2L,"White","The color white",m1));
        balloonRepository.save(new Balloon(3L,"Red","The color red",m1));
        balloonRepository.save(new Balloon(4L,"Blue","The color blue",m1));
        balloonRepository.save(new Balloon(5L,"Green","The color green",m1));
        balloonRepository.save(new Balloon(6L,"Yellow","The color yellow",m2));
        balloonRepository.save(new Balloon(7L,"Pink","The color pink",m2));
        balloonRepository.save(new Balloon(8L,"Purple","The color purple",m2));
        balloonRepository.save(new Balloon(9L,"Orange","The color orange",m2));
        balloonRepository.save(new Balloon(10L,"Cyan","The color cyan",m2));

    }


}

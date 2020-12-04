package com.rk.rentacarapp.bootstrap;

import com.rk.rentacarapp.model.*;
import com.rk.rentacarapp.repository.*;
import org.apache.tomcat.jni.Local;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    AuthorityRepository authorityRepository;
    AddressRepository addressRepository;
    ExtraRepository extraRepository;
    RentRepository rentRepository;
    VehicleRepository vehicleRepository;
    UserRepository userRepository;


    public DevBootstrap(AuthorityRepository authorityRepository,
                        AddressRepository addressRepository,
                        ExtraRepository extraRepository,
                        RentRepository rentRepository,
                        VehicleRepository vehicleRepository,
                        UserRepository userRepository) {

                 this.authorityRepository = authorityRepository;
                 this.addressRepository = addressRepository;
                 this.extraRepository = extraRepository;
                 this.rentRepository = rentRepository;
                 this.vehicleRepository = vehicleRepository;
                 this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        Address adr1 = Address.builder()
                .country("Serbia")
                .province("Vojvodina")
                .postalCode("21000")
                .city("Novi Sad")
                .street("Maksima Gorkog 14/4")
                .build();

        System.out.println("\n\n" + adr1 + "\n\n");

        Address adr2 = Address.builder()
                .country("Serbia")
                .province("Vojvodina")
                .postalCode("24000")
                .city("Subotica")
                .street("Maksima Gorkog 12")
                .build();

        User user1 = User.builder()
                        .name("Mirko")
                        .surname("Mirkovic")
                        .email("mirko@email.com")
                        .password("1234")
                        .lastPasswordResetDate(new Timestamp(new Date().getTime()))
                        .address(adr1)
                        .accountEnabled(true)
                        .accountExpired(false)
                        .accountLocked(false)
                        .authorities(new HashSet<Authority>())
                        .build();

        Authority a1 = new Authority();
        user1.addAuthority(a1);

        User user2 = User.builder()
                        .name("Milan")
                        .surname("Mikic")
                        .email("mimiki@email.com")
                        .password("1234")
                        .lastPasswordResetDate(new Timestamp(new Date().getTime()))
                        .address(adr2)
                        .accountEnabled(true)
                        .accountExpired(false)
                        .accountLocked(false)
                        .authorities(new HashSet<Authority>())
                        .build();

        Authority a2 = new Authority();
        a2.setAuthority("ROLE_ADMIN");
        user2.addAuthority(a2);

        Vehicle v1 = Vehicle.builder()
                            .regPlate("NS512AB")
                            .manufacturer("Fiat")
                            .model("500L")
                            .vehicleType(VehicleType.CARS)
                            .manufactureDate(LocalDate.now().minusYears(1))
                            .photoA("https://autorepublika.com/wp-content/uploads/2019/08/Fiat-500L-Trekking.jpg")
                            .photoB("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/2012_Geneva_Motor_Show_-_Fiat_500L.jpg/1200px-2012_Geneva_Motor_Show_-_Fiat_500L.jpg")
                            .photoC("https://nikomauto.rs/FIAT/500l/slike/170522_Fiat_New-500L-Lounge_01.jpg")
                            .rentPrice(2000.0)
                            .build();

        Vehicle v2 = Vehicle.builder()
                            .regPlate("NS128RT")
                            .manufacturer("Suzuki")
                            .model("SV650")
                            .vehicleType(VehicleType.MOTORCYCLES)
                            .manufactureDate(LocalDate.of(2008, 10, 10))
                            .photoA("https://i.pinimg.com/originals/d9/7f/94/d97f94ca1495f94c918b803c3b25f781.jpg")
                            .photoB("https://i.pinimg.com/originals/e3/3f/2d/e33f2dd908b5aa2fbe1d3b59453ebd98.jpg")
                            .photoC("https://www.southbayriders.com/forums/attachments/413921/")
                            .rentPrice(2500.0)
                            .build();


        Vehicle v3 = Vehicle.builder()
                .regPlate("NS128LO")
                .manufacturer("Audi")
                .model("A1 Sportback")
                .vehicleType(VehicleType.CARS)
                .manufactureDate(LocalDate.now().minusYears(1))
                .photoA("https://www.audi.rs/media/TextImage_ImageEnlarge_Component/51397-455323-image/dh-1920-d8ec43/d4e4daae/1585829258/1920x1080-mtc-xl-aa1-181002-oe.jpg")
                .photoB("https://www.audi.rs/media/Theme_Banners_Banner_Image_Component/51396-banner-285825-image/dh-1920-918e09/47891673/1585827702/1920x600-stage-aa1-181001-oe.jpg")
                .photoC("https://car-images.bauersecure.com/pagefiles/85097/audi_a1_review_05.jpg")
                .rentPrice(3000.0)
                .build();

        Vehicle v4 = Vehicle.builder()
                .regPlate("NS128LO")
                .manufacturer("Audi")
                .model("A3 Kabriolet")
                .vehicleType(VehicleType.CARS)
                .manufactureDate(LocalDate.now().minusYears(1))
                .photoA("https://cdn.motor1.com/images/mgl/jLJkg/s1/audi-a3-cabrio.jpg")
                .photoB("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTeU5Mctpt6U3geUgUjCNR8L6d6E-QsQoceUw&usqp=CAU")
                .photoC("https://cdcssl.ibsrv.net/cimg/www.carsdirect.com/680x382_85/867/A161816_medium-487867.jpg")
                .rentPrice(3500.0)
                .build();

        Rent rent1 = Rent.builder()
                         .rentState(RentState.RENT_COMPLETED)
                         //.user(user1)
                         .vehicle(v1)
                         .extras(new HashSet<>())
                         .startDate(LocalDate.of(2020, 9,1))
                         .endDate(LocalDate.of(2020, 9, 5))
                         .build();

        vehicleRepository.save(v1);
        vehicleRepository.save(v2);
        vehicleRepository.save(v3);
        vehicleRepository.save(v4);

        addressRepository.save(adr1);
        addressRepository.save(adr2);

        authorityRepository.save(a1);
        authorityRepository.save(a2);

        userRepository.save(user1);
        userRepository.save(user2);

        rentRepository.save(rent1);

    }

}

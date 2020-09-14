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
        user2.addAuthority(a2);

        Vehicle v1 = Vehicle.builder()
                            .registrationPlate("NS512AB")
                            .manufacturer("Fiat")
                            .model("500L")
                            .vehicleType(VehicleType.CAR)
                            .manufactureDate(LocalDate.now().minusYears(1))
                            .photoA("https://autorepublika.com/wp-content/uploads/2019/08/Fiat-500L-Trekking.jpg")
                            .photoB("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/2012_Geneva_Motor_Show_-_Fiat_500L.jpg/1200px-2012_Geneva_Motor_Show_-_Fiat_500L.jpg")
                            .photoC("https://nikomauto.rs/FIAT/500l/slike/170522_Fiat_New-500L-Lounge_01.jpg")
                            .rentPrice(2000.0)
                            .build();

        Rent rent1 = Rent.builder()
                         .rentState(RentState.RENT_COMPLETED)
                         .user(user1)
                         .vehicle(v1)
                         .extras(new HashSet<>())
                         .startDate(LocalDate.of(2020, 9,1))
                         .endDate(LocalDate.of(2020, 9, 5))
                         .build();

        vehicleRepository.save(v1);

        addressRepository.save(adr1);
        addressRepository.save(adr2);

        authorityRepository.save(a1);
        authorityRepository.save(a2);

        userRepository.save(user1);
        userRepository.save(user2);

        rentRepository.save(rent1);






    }

}

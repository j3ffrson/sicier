package org.fesc.sicier;

import org.springframework.boot.SpringApplication;

public class TestSicierApplication {

    public static void main(String[] args) {
        SpringApplication.from(SicierApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}

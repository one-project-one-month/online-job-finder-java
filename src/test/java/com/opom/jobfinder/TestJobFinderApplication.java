package com.opom.jobfinder;

import org.springframework.boot.SpringApplication;

public class TestJobFinderApplication {

        public static void main(String[] args){
                SpringApplication.from(JobFinderApplication::main)
                        .with(TestContainersConfiguration.class)
                        .run(args);
        }
        
}

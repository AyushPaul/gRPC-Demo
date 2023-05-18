package com.ayush;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class MultiplesServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(6565)
                .addService(new MultiplesService())
                .build();

        // start
        server.start();

        // shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Amazon server is shutting down!");
            server.shutdown();
        }));

        server.awaitTermination();
    }
}

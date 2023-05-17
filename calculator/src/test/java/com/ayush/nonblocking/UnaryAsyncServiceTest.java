package com.ayush.nonblocking;

import com.ayush.OutputStreamObserver;
import com.ayush.calculator.CalculatorServiceGrpc;
import com.ayush.calculator.Input;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnaryAsyncServiceTest {
    private ManagedChannel channel;
    private CalculatorServiceGrpc.CalculatorServiceStub clientStub;


    @Before
    public void setup(){
        this.channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();
        this.clientStub = CalculatorServiceGrpc.newStub(channel);
    }

    @Test
    public void unaryServiceTest() throws InterruptedException {
        // build the request object
        Input input = Input.newBuilder()
                .setNumber(5)
                .build();

        // receive the response
        this.clientStub.findFactorial(input, new OutputStreamObserver());

        // block here for testing or count down latch
        Thread.sleep(3000);
    }

    @After
    public void teardown(){
        this.channel.shutdown();
    }

}

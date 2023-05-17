package com.ayush.blocking;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UnaryServiceTest {

    private ManagedChannel channel;
    private com.ayush.calculator.CalculatorServiceGrpc.CalculatorServiceBlockingStub clientStub;

    @Before
    public void setup(){
        this.channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();
        this.clientStub = com.ayush.calculator.CalculatorServiceGrpc.newBlockingStub(channel);
    }

    @Test
    public void unaryServiceTest(){
        // build the request object
        com.ayush.calculator.Input input = com.ayush.calculator.Input.newBuilder()
                .setNumber(2)
                .build();

        // receive the response
        com.ayush.calculator.Output output = this.clientStub.findFactorial(input);

        //check the result
        Assert.assertEquals(2, output.getResult());
    }

    @After
    public void teardown(){
        this.channel.shutdown();
    }

}

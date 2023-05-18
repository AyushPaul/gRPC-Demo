package com.ayush;

import com.ayush.multiples.Input;
import com.ayush.multiples.MultipleServiceGrpc;
import com.ayush.multiples.Output;
import io.grpc.stub.StreamObserver;

public class MultiplesService extends MultipleServiceGrpc.MultipleServiceImplBase {
    @Override
    public void findMultiple(Input request, StreamObserver<Output> responseObserver) {
        for (int i = 1 ; i<=10 ; i++){
            Output output = Output.newBuilder().setMultiples(i*request.getNum()).build();
            responseObserver.onNext(output);
        }

        responseObserver.onCompleted();
    }
}

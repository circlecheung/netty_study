package com.zhihao.miao.gprc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GrpcServer {

    private Server server;

    private void start() throws IOException{
        this.server = ServerBuilder.forPort(8899).addService(new StudentServiceImpl()).build().start();

        System.out.println("server start!");

        //jvm回调钩子的作用
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            GrpcServer.this.stop();
            System.err.println("*** server shut down");
        }));

        System.out.println("执行到这里");
    }

    private void stop(){
        if(null != this.server){
            this.server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if(null != this.server){
            this.server.awaitTermination();
            //this.server.awaitTermination(3000, TimeUnit.MILLISECONDS);
        }
    }

    public static void main(String[] args) throws IOException,InterruptedException{
        GrpcServer server = new GrpcServer();

        server.start();;
        server.blockUntilShutdown();
    }
}

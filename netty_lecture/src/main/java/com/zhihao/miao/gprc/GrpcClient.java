package com.zhihao.miao.gprc;

import com.zhihao.miao.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.Iterator;

public class GrpcClient {

    public static void main(String[] args) throws Exception{
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost",8899)
                .usePlaintext(true).build();

        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);

        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);


        MyResponse response =blockingStub.getRealNameByUsername(MyRequest.newBuilder()
                .setUsername("miaozhihao").build());

        System.out.println(response.getRealname());

        System.out.println("----------------------------");

        Iterator<StudentResponse> iterator = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());
        while(iterator.hasNext()){
            StudentResponse studentResponse = iterator.next();

            System.out.println(studentResponse.getName()+", "+studentResponse.getAge()+", "+studentResponse.getCity());
        }

        System.out.println("------------------------------");


        //构建请求的回调对象
        StreamObserver<StudentResponseList> studentResponseListStreamObserver = new StreamObserver<StudentResponseList>() {
            @Override
            public void onNext(StudentResponseList value) {
                value.getStudentResponseList().forEach(studentResponse -> {
                    System.out.println("name="+studentResponse.getName()+",age="+studentResponse.getAge()+
                            ",city="+studentResponse.getCity());
                    System.out.println("*********************");
                });
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("completed!");
            }
        };


        //只要客户端是以流式向服务端发送请求，那么一定要通过异步的形式进行调用
        StreamObserver<StudentRequest> studentRequestStreamObserver = stub.getStudentsWapperByAges(studentResponseListStreamObserver);


        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(30).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(40).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(50).build());


        studentRequestStreamObserver.onCompleted();

        //Thread.sleep(5000);


        System.out.println("----------------------------------------------------");


        StreamObserver<StreamRequest> requestStreamObserver = stub.biTalk(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                System.out.println(value.getResponseInfo());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompled!");
            }
        });


        for (int i = 0; i <10 ; i++) {
            requestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());

            Thread.sleep(1000);
        }
        Thread.sleep(5000);


































    }
}

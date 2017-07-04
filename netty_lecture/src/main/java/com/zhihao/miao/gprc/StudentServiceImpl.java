package com.zhihao.miao.gprc;

import com.zhihao.miao.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase{


    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端信息： "+request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();


    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接收到客户端信息："+request.getAge());

        responseObserver.onNext(StudentResponse.newBuilder().setName("张三").setAge(20).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("李四").setAge(30).setCity("天津").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("王五").setAge(40).setCity("成都").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("苗志浩").setAge(50).setCity("苏州").build());

        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<StudentRequest> getStudentsWapperByAges(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            //表示服务端接收客户端的一个message
            @Override
            public void onNext(StudentRequest value) {
                System.out.println("onNext: "+value.getAge());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            //服务器端接收完客户端的所有messages
            @Override
            public void onCompleted() {
                StudentResponse studentResponse1 = StudentResponse.newBuilder().setName("张三").setAge(20).setCity("西安").build();

                StudentResponse studentResponse2 = StudentResponse.newBuilder().setName("李四").setAge(30).setCity("苏州").build();

                StudentResponseList studentResponseList = StudentResponseList.newBuilder().addStudentResponse(studentResponse1)
                        .addStudentResponse(studentResponse2).build();

                responseObserver.onNext(studentResponseList);
                responseObserver.onCompleted();
            }
        };


    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest value) {
                System.out.println(value.getRequestInfo());

                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            //双向流服务端也要关闭流
            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}

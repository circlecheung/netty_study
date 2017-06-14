package com.zhihao.miao.protobuf;

//实际使用protobuf序列化框架客户端将对象转译成字节数组，然后通过协议传输到服务器端，服务器端可以是其他的语言框架（比如说python）将
//字节对象反编译成java对象
public class ProtobuffTest {
    public static void main(String[] args) throws Exception{
        DataInfo.Student student = DataInfo.Student.newBuilder().
                setName("张三").setAge(20).setAddress("北京").build();

        //将对象转译成字节数组,序列化
        byte[] student2ByteArray = student.toByteArray();

        //将字节数组转译成对象,反序列化
        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);

        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());
    }
}

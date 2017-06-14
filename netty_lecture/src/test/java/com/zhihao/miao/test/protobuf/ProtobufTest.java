package com.zhihao.miao.test.protobuf;


public class ProtobufTest {
    public static void main(String[] args) throws Exception{
        AddressBookProtos.Person.PhoneNumber phoneNumber = AddressBookProtos.Person.PhoneNumber.
                newBuilder().setNumber("444-3333").build();


        //mergeFrom
        AddressBookProtos.Person person = AddressBookProtos.Person.newBuilder().
                setName("zhihao.miao").setId(20).setEmail("1023233232@qq.com").
                addPhones(AddressBookProtos.Person.PhoneNumber.newBuilder().setNumber("555-4321")
                        .setType(AddressBookProtos.Person.PhoneType.HOME).mergeFrom(phoneNumber).build()).build();

        AddressBookProtos.AddressBook addressBook = AddressBookProtos.AddressBook.
                newBuilder().addPeople(person).build();

        //将对象转译成字节数组,序列化
        byte[] addressBookArray = addressBook.toByteArray();

        //将字节数组转译成对象,反序列化
        AddressBookProtos.AddressBook addressBookfrom = AddressBookProtos.AddressBook.parseFrom(addressBookArray);


        System.out.println(addressBookfrom.getPeopleList());
    }
}

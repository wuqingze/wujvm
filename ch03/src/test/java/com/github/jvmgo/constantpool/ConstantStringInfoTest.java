
package com.github.jvmgo.constantpool;

public class ConstantStringInfoTest{
    public String hello = "hello world";

    private String test(String arg0, int arg1){
	return "hello world";
    }
    
    private void hello(){}

    private class hello{}
    
    private <T>	T hello(T args){
	System.out.println(args);
	return args;
    }

    public static void main(String[] args){
//	ConstantStringInfoTest t = new ConstantStringInfoTest();
//	System.out.println(t.hello);
	System.out.println("hello world");
    }
}

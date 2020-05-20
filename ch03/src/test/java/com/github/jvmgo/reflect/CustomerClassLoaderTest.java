package com.github.jvmgo.reflect;

import org.junit.Test; 
import java.util.Scanner;
import com.github.jvmgo.reflect.CustomerClassLoader;
import java.io.File;

public class CustomerClassLoaderTest{


    @Test
    public void test00(){
	System.out.println("hello world");
    }

    @Test
    public void test01(){
	Scanner in = new Scanner(System.in);

//	while(in.hasNextLine()){
//	    String args = in.nextLine();
//	    System.out.println(args);
//	}
    }

    @Test
    public void test02(){
        //System.out.println(this.class.getClassLoader());
	//this.getClass().getClassLoader().getResource("./com/github/jvmgo/relect/CustomerClassLoader.class").getPath();
	System.out.println(this.getClass().getClassLoader().getResource("").getPath());
    }

    @Test 
    public void test03() throws Exception{
//	CustomerClassLoader cl = new CustomerClassLoader();
//	cl.findClass("com.github.jvmgo.CustomerClassLoader");
	System.out.println(this.getClass().getClassLoader().getResource("/") == null);
	File f = new File("target/classes/com/github/jvmgo/reflect/CustomerClassLoader.class");
        System.out.println(f.exists());

    }

    @Test 
    public void test04() throws Exception{
	CustomerClassLoader cl = new CustomerClassLoader();
	cl.findClass("com.github.jvmgo.reflect.CustomerClassLoader");
	File f = new File("target/classes/com/github/jvmgo/reflect/CustomerClassLoader.class");
        System.out.println(f.exists());


    }

    @Test 
    public void test05() throws Exception{
	Class c = Class.forName("java.lang.Integer"); 
	System.out.println(c);
    }

}

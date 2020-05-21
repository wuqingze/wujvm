package com.github.jvmgo.reflect;

import org.junit.Test; 
import java.util.Scanner;
import com.github.jvmgo.reflect.CustomerClassLoader;
import java.io.File;
import java.lang.reflect.*;

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

    @Test
    public void test06() throws Exception{
	Class c = Class.forName("com.github.jvmgo.reflect.CustomerClassLoader");
	Method m = c.getDeclaredMethod("testString", new Class[] {java.lang.String.class});
	//Method m = c.getDeclaredMethod("testString", java.lang.String.class);
	m.setAccessible(true);
	m.invoke(c.newInstance(), "hello world teststring");
    }

    @Test
    public void test07() throws Exception{
	Class c = Class.forName("com.github.jvmgo.reflect.CustomerClassLoader");
	Method m = c.getDeclaredMethod("testString", new Class[] {java.lang.String.class, java.lang.
	Integer.class});
	//Method m = c.getDeclaredMethod("testString", java.lang.String.class);
	m.setAccessible(true);
	m.invoke(c.newInstance(), "hello world teststring", 1024);
    }

    @Test
    public void test08() throws Exception{
	Class c = Class.forName("com.github.jvmgo.reflect.CustomerClassLoader");
	Method m = c.getDeclaredMethod("testString", new Class[] {java.lang.String.class,
	    java.lang.Integer.class});
	m.setAccessible(true);
	m.invoke(c.newInstance(), "hello world teststring", 39);
    }

    @Test
    public void test09() throws Exception{
	Class c = Class.forName("com.github.jvmgo.reflect.CustomerClassLoader");
	Method m = c.getDeclaredMethod("testString", new Class[] {java.lang.String.class,
	    java.lang.Double.class});
	m.setAccessible(true);
	m.invoke(c.newInstance(), "hello world teststring", 3.139);
    }

    @Test
    public void test10() throws Exception{
//	Class c = Class.forName("com.github.jvmgo.reflect.CustomerClassLoader");
//	Method m = c.getDeclaredMethod("testString", new Class[] {java.lang.String.class,
//	    java.lang.Float.class});
//	m.setAccessible(true);
//	m.invoke(c.newInstance(), "hello world teststring", 3.1);
    }
    
    @Test
    public void test11() throws Exception{
	Class c = Class.forName("com.github.jvmgo.reflect.CustomerClassLoader");
	String[] classes = new String[]{ "java.lang.String", "java.lang.Integer"};
	Class[] args = new Class[classes.length];
	for(int i=0;i<classes.length;i++){
	    args[i] = Class.forName(classes[i]);
	}
	Method m = c.getDeclaredMethod("testString",args); 
	m.setAccessible(true);
	Object[] ar = new Object[2];
	ar[0] = "hahah";
	ar[1] = 3;
	m.invoke(c.newInstance(), ar);
    }

    @Test
    public void test12() throws Exception{
	System.out.println(new Integer("11"));
	System.out.println(new String("asdkjfa"));
	System.out.println(new Double("3.14"));
    } 

    @Test
    public void test13() throws Exception{
	Class c = Class.forName("com.github.jvmgo.reflect.CustomerClassLoader");
	Class[] args = new Class[1];
	//args[0] = java.lang.Integer.class; 
	args[0] = int.class; 
	Method m = c.getDeclaredMethod("testString",args); 
	m.setAccessible(true);
	m.invoke(c.newInstance(),111);
    }

    @Test
    public void test14() throws Exception{
	Class c = Class.forName("com.github.jvmgo.reflect.CustomerClassLoader");
	Class[] args = new Class[1];
	//args[0] = java.lang.Integer.class; 
	args[0] = double.class; 
	Method m = c.getDeclaredMethod("testString",args); 
	m.setAccessible(true);
	m.invoke(c.newInstance(),111);
    }

    @Test
    public void test15() throws Exception{
	Class c = Class.forName("com.github.jvmgo.reflect.CustomerClassLoader");
	Class[] args = new Class[2];
	args[0] = java.lang.String.class;
	args[1] = java.lang.Double.class;
	Method m = c.getDeclaredMethod("testString",args); 
	m.setAccessible(true);
	Object[] o = new Object[2];
	o[0] = "test15";
	o[1] = 15.0;
	m.invoke(c.newInstance(), o);
    }

    @Test
    public void test16() throws Exception{
	int a = Integer.parseInt("12");
	double b = Double.parseDouble("12.11");
	boolean c = Boolean.parseBoolean("true");
	System.out.println(a);
	System.out.println(b);
	System.out.println(c);
    }

    
}

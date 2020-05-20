package com.github.jvmgo.classfile;

import org.junit.Test;
import org.junit.Before;
import java.io.File;
import java.util.Arrays;
import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.github.jvmgo.classfile.ClassReader;
import com.github.jvmgo.classpath.Classpath;


public class ClassReaderTest{

    ClassReader classReader;

    @Before
    public void init(){
	classReader = new ClassReader(new byte[]{0x04, 0x00});
    }
    
    @Test
    public void test00(){
	assertEquals(4,classReader.nextU1toInt());
	assertEquals(0,classReader.nextU1toInt());
	classReader.back(2);
	assertEquals(4,classReader.nextU1toInt());
	assertEquals(0,classReader.nextU1toInt());
	classReader.back(2);

    }

    @Test
    public void test01(){
	System.out.println(classReader.nextU2ToInt());
	classReader.back(2);
	assertEquals(1024,classReader.nextU2ToInt());
	classReader.back(2);

    }

    @Test
    public void test02(){
	classReader = new ClassReader(new byte[]{0x01,0x00,0x00,0x00});
	System.out.println(classReader.nextU4ToInt());
	classReader.back(4);
	assertEquals(256*256*256,classReader.nextU4ToInt());
	classReader.back(4);

    }

    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void test03(){
	classReader = new ClassReader(new byte[]{0x01,0x00,0x00,0x00});
	classReader.nextBytes(5);

    }
    
    @Test
    public void test04(){
	byte[] t = new byte[]{0x01,0x00,0x00,0x00};
	classReader = new ClassReader(new byte[]{0x01,0x00,0x00,0x00});
	assertTrue(Arrays.equals(t, classReader.nextBytes(4)));
	classReader.back(4);
	System.out.println(Arrays.toString(classReader.nextBytes(4)));
	classReader.back(4);
    }

    @Test
    public void test05(){
	classReader = new ClassReader(new byte[]{0x01,0x00,0x00,0x7b});
	System.out.println(classReader.nextU4ToHexString());
    }

    @Test
    public void test06(){
	classReader = new ClassReader(new byte[]{0x00,0x01,0x00,0x7b});
	System.out.println(Arrays.toString(classReader.nextUint16s()));
    }

    @Test
    public void test07() throws Exception{
	Classpath cp = new Classpath(null, null);
	byte[] d = cp.readClass("java/lang/Object");
	FileOutputStream out = new FileOutputStream(new File("Object.class"));
	out.write(d);
	out.close();
	System.out.println(Arrays.toString(d));
    }
    @Test
    public void test08(){
	System.out.println("hello world--------------------------------test08");
    }

    /**
     * 将String字节码读取到String.java文件中，便于classpy工具和其他类进行比较分析
     */
    @Test
    public void test09() throws Exception{
	Classpath cp = new Classpath(null, null);
	byte[] d = cp.readClass("java/lang/String");
	FileOutputStream out = new FileOutputStream(new File("String.class"));
	out.write(d);
	out.close();
	System.out.println(Arrays.toString(d));
    }
    
    @Test
    public void test10() throws Exception{
	Scanner in = new Scanner(System.in);
	while(in.hasNextLine()){
	    System.out.println(in.nextLine());
	}
    }
}

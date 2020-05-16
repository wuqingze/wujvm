package com.github.jvmgo.classFile;

import org.junit.Test;
import org.junit.Before;
import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.github.jvmgo.classfile.ClassReader;

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
}

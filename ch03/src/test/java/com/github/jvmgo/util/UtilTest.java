
package com.github.jvmgo.util;

import org.junit.Test;
import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.github.jvmgo.util.Util;

public class UtilTest{
    
    @Test
    public void test00(){
	System.out.println(Util.byteToInt(new byte[]{0x11,0x11}));
	System.out.println(Util.byteToInt(new byte[]{0x04,0x00}));
	assertEquals(1024, Util.byteToInt(new byte[]{0x04,0x00}));
	System.out.println(Util.byteToInt(new byte[]{0x05,0x00}));
	assertEquals(1280, Util.byteToInt(new byte[]{0x05,0x00}));
    }
}

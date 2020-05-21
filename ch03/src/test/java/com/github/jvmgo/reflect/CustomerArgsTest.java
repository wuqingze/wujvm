
package com.github.jvmgo.reflect;

import org.junit.Test; 
import java.util.Scanner;
import com.github.jvmgo.reflect.CustomerArgs;
import java.io.File;
import java.lang.reflect.*;
import java.util.*;

public class CustomerArgsTest{
    

    @Test
    public void test00(){
	String t = "-c java.lang.String -m test -t java.lang.String -v helloworld";
	System.out.println(t);
	String[] args = t.split(" ");
	CustomerArgs ags = CustomerArgs.parse(args);
	System.out.println("mainclassandargs--"+ags.mainClassAndArgs);
	System.out.println("__class"+ags._class);
	System.out.println("method---"+ags.method);
	System.out.println("type--"+ags.type);
	System.out.println("value--"+ags.value);
	System.out.println("types--"+Arrays.toString(ags.types));
	System.out.println("values--"+Arrays.toString(ags.values));
	System.out.println(ags.ok);
    }
}

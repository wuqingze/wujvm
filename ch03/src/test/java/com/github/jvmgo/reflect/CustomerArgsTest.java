
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


    private void initArray(Object[] array){
	for(int i=0;i<3;i++){
	    array[i] = i;
	}
    }

    @Test
    public void test01(){
	Object[] a = new Object[3];
	initArray(a);
	System.out.println(Arrays.toString(a));
    }

    @Test
    public void test02() throws Exception{
	Scanner in = new Scanner(System.in);
	while(in.hasNextLine()){
	    String argsString = in.nextLine();
	    System.out.println(argsString);

	    CustomerArgs customerArgs = CustomerArgs.parse(argsString.split(" "));

	    System.out.println("mainclassandargs--"+customerArgs.mainClassAndArgs);
	    System.out.println("__class"+customerArgs._class);
	    System.out.println("method---"+customerArgs.method);
	    System.out.println("type--"+customerArgs.type);
	    System.out.println("value--"+customerArgs.value);
	    System.out.println("types--"+Arrays.toString(customerArgs .types));
	    System.out.println("constructorvalues--"+Arrays.toString(customerArgs.constructorValues));
	    System.out.println("constructortypes--"+Arrays.toString(customerArgs.constructorTypes));
	    System.out.println("constructortype--"+customerArgs.constructorType);
	    System.out.println("constructorvalue--"+customerArgs.constructorValue);

	    Class c = Class.forName(customerArgs._class);
	    Method method = c.getDeclaredMethod(customerArgs.method, customerArgs.types);
	    Constructor constructor = c.getConstructor(customerArgs.constructorTypes);
	    Object o = method.invoke(constructor.newInstance(customerArgs.constructorValues), customerArgs.values);
	    System.out.println(o);
	}
    }
}

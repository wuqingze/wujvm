package com.github.jvmgo.reflect;
import java.io.*;

public class CustomerClassLoader extends ClassLoader{

    /**
     * name是为包名+类名的样子，比如java.lang.String
     * 不想涉及到复杂的类的加载，这个方法只使用项目
     * 中类
     **/
    @Override
    public Class findClass(String name) throws ClassNotFoundException{
	try{
	System.out.println("---------"+name);
	String file = name.replaceAll("\\.","/")+".class";
	System.out.println("============"+file);
	InputStream in = new FileInputStream(new File("target/classes/"+file));
	byte[] b = new byte[in.available()];
	in.read(b);
	System.out.println(b.length);
	}catch(Exception e){
	    System.out.println(e.getMessage());
	}
	return null;
    }
}

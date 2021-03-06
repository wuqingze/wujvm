package com.github.jvmgo;

import com.github.jvmgo.classfile.ClassFile;
import com.github.jvmgo.classfile.MemberInfo;
import com.github.jvmgo.classfile.constantpool.ConstantInfo;
import com.github.jvmgo.classfile.constantpool.ConstantPool;
import com.github.jvmgo.classpath.Classpath;
import java.util.*;
import java.util.stream.IntStream;

public class Main {


    private ClassFile cf;

    public static void main(String[] argv) {
        //Args args = Args.parse(argv);
        //if (!args.ok || args.helpFlag) {
        //    System.out.println("Usage: <main class> [-options] class [args...]");
        //} else if (args.versionFlag) {
        //    System.out.println("java version \"1.8.0\"");
        //} else {
	    //System.out.println(Arrays.toString(argv));
	    //System.out.println(args.classpath);
	    //System.out.println(args.mainClassAndArgs);
        ////    startJVM(args);
        //}

        //IntStream.range(0, 4).forEach(System.out::print);

        System.out.println(Arrays.toString(
        IntStream.range(0,10)
                .mapToObj(i-> new String("hello world"))
                .toArray(String[]::new)
        ));
    }

    private static void startJVM(Args args) {
        Classpath cp = new Classpath(args.jre, args.classpath);
        System.out.printf("classpath:%s class:%s args:%s\n",
                cp, args.getMainClass(), args.getAppArgs());

        String className = args.getMainClass().replace(".", "/");
        try {
            byte[] classData = cp.readClass(className);

            ClassFile classFile = new ClassFile(classData);
            printClassInfo(classFile);
           
        } catch (Exception e) {
            System.out.print(e.getMessage());
            System.out.println("Could not find or load main class " + args.getMainClass());
        }
    }

    private static void printClassInfo(ClassFile cf) {
        System.out.println("version: "+ cf.getMajorVersion()+"."+cf.getMinorVersion());

        ConstantPool constantPool = cf.getConstantPool();
        ConstantInfo[] constantInfos = constantPool.getConstantInfos();
        System.out.println("constants count: "+ constantPool.getConstantPoolSize());
        for(int i = 1; i < constantPool.getConstantPoolSize() ; i++) {

           if(constantInfos[i]!=null){
               System.out.println(i+": "+constantInfos[i]);
           }
        }


        System.out.format("access flags: 0x%x\n", cf.getAccessFlag());
        System.out.println("this class: "+ constantPool.getUTF8(cf.getClassNameIndex()));
        System.out.println("super class: "+ constantPool.getUTF8(cf.getSuperClassNameIndex()));
        System.out.println("interfaces: "+cf.getInterfaceIndexes().length);
        MemberInfo[] fields = cf.getFields();
        System.out.println("fields count: "+ fields.length);
        for (MemberInfo memberInfo : fields) {
            System.out.format("  %s\n", constantPool.getUTF8(memberInfo.getNameIndex()));
        }

        MemberInfo[] methods = cf.getMethods();
        System.out.println("methods count: "+ methods.length);
        for (MemberInfo memberInfo : methods) {
            System.out.format("  %s\n", constantPool.getUTF8(memberInfo.getNameIndex()));
        }

    }


    public static void panic(String msg) {
    	System.out.println(msg);
    	System.exit(0);
    }

}

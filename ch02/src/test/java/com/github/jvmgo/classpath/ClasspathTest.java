package com.github.jvmgo.classpath;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

import org.junit.Before;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.sound.sampled.SourceDataLine;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.github.jvmgo.classpath.Classpath;
import com.github.jvmgo.classpath.Entry;

public class ClasspathTest {

    Classpath classpath;

   @Before
    public void init() {
        Arrays.asList("a").stream().forEach(System.out::println);
        classpath = new Classpath(null, null);
    }

    /**
     * hello world
     */
    @Test
    public void test00() {
        System.out.println("hello world");
    }

    /**
     * 空指针
     */
    @Test(expected = NullPointerException.class)
    public void test01() {
        Classpath.join(null);
    }

    /**
     * Classpath.join
     */
    @Test
    public void test02() {
        System.out.println(Classpath.join("hello", "world"));
        System.out.println(Classpath.join("/mnt/java", "/mnt/c/Users/surface/java"));
    }

    /**
     * 私有方法、变量测试
     */
    @Test
    public void test03() throws Exception {
        Method method = Classpath.class.getDeclaredMethod("getJreDir", String.class);
        method.setAccessible(true);
        String jreOption = "/opt/java/jdk1.8.0_141/jre";
        String t = (String) method.invoke(classpath, jreOption);
        System.out.println(t);
        assertEquals(jreOption, t);
        t = (String) method.invoke(classpath, "--");
        System.out.println(t);
        assertEquals(jreOption, t);
        t = (String) method.invoke(classpath, "./target/classes/");
        System.out.println(t);
        assertEquals(jreOption, t);
    } 

    /**
     * paths
     * @throws Exception
     */ 
    @Test
    public void test04() throws Exception {
        System.out.println(Paths.get("./jre"));
        System.out.println(Files.exists(Paths.get("./jre")));
        System.out.println(Files.exists(Paths.get("java")));
        System.out.println(Paths.get(".").toAbsolutePath());
        System.out.println("202005150232===");
    }
    
    /**
     * system enviroments 
     * @throws Exception
     */ 
    @Test
    @Ignore
    public void test05() throws Exception {
        System.getenv().entrySet().forEach((e) ->{
            System.out.println(e.getKey()+"----"+e.getValue());
        });
    } 

    /**
     * paths.get ""
     */
    @Test
    public void test06(){
	    System.out.println(Paths.get(""));
	    System.out.println("-------");
    }

}

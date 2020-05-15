package com.github.jvmgo;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArgsTest {

    @Test
    public void test() {
        assertTrue(Args.parse(new String[]{"-?"}).helpFlag);
        assertTrue(Args.parse(new String[]{"-help"}).helpFlag);
        assertTrue(Args.parse(new String[]{"-version"}).versionFlag);
        assertFalse(Args.parse(new String[]{"-cp"}).ok);
        assertFalse(Args.parse(new String[]{"-classpath"}).ok);
        assertEquals("foo.jar", Args.parse(new String[]{"-cp", "foo.jar"}).classpath);
        assertEquals("foo.jar", Args.parse(new String[]{"-classpath", "foo.jar"}).classpath);
        assertEquals("foo/bar", Args.parse(new String[]{"-Xjre", "foo/bar"}).jre);
        assertEquals(Arrays.asList("Main", "foo"), Args.parse(new String[]{"Main", "foo"}).mainClassAndArgs);
    }

    /**
     * 测试流处理collect join的效果
     */
    @Test
    public void test_01() throws IOException {
//        String r = Files.walk(Paths.get("C:\\jdk1.8.0_211\\lib"))
//                .filter(Files::isRegularFile)
//                .map(Path::toString)
//                .filter(p -> p.endsWith(".jar") || p.endsWith(".JAR"))
//                .collect(Collectors.joining(File.pathSeparator));
//
//        System.out.println(r);
//        System.out.println(File.pathSeparator);
    }

}

package com.wuqingze.jvm;

public class Main {

    public static void main(String[] argv) {
        Args args = Args.parse(argv);
        if (!args.ok || args.helpFlag) {
            System.out.println("Usage: <main class> [-options] class [args...]");
        } else if (args.versionFlag) {
            System.out.println("java version \"1.8.0\"");
        } else {
            startJVM(args);
        }
    }

    private static void startJVM(Args args) {
        System.out.printf("classpath:%s class:%s args:%s\n",
                args.classpath, args.getMainClass(), args.getAppArgs());
    }

}

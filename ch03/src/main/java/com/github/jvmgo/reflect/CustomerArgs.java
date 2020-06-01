package com.github.jvmgo.reflect;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.util.List;
import java.lang.reflect.*;
import java.util.Arrays;

public class CustomerArgs {


    @Parameter(names = {"-c", "-class"}, description = "class", order=1)
    public String _class;

    @Parameter(names = {"-m", "-method"}, description = "method", order=2)
    public String method;

    /**
     * 作为内部中间临时变量，不能往外传
     **/
    @Parameter(names = {"-t", "-type"}, description = "args class type", order=3)
    public String type;

    /**
     * 作为内部中间临时变量，不能往外传
     **/
    @Parameter(names = {"-v", "-value"}, description = "args value", order=4)
    public String value;

    @Parameter(names = {"-r", "-result"}, description = "result", order=5)
    public String result;

    @Parameter(names = {"-rt", "-resultType"}, description = "result type", order=6)
    public String resultType;

    /**
     * 作为内部中间临时变量，不能往外传
     **/
    @Parameter(names = {"-ct", "-constructorType"}, description = "constructor type", order=7)
    public String constructorType;

    /**
     * 作为内部中间临时变量，不能往外传
     **/
    @Parameter(names = {"-cv", "-construcorValue"}, description = "constructor value", order=8)
    public String constructorValue;

    @Parameter(description = "main class and args")
    public List<String> mainClassAndArgs;

    public Class[] types;

    public Object[] values;

    public Class[] constructorTypes;

    public Object[] constructorValues;

    public boolean ok;

    static CustomerArgs parse(String[] argv) {
        CustomerArgs args = new CustomerArgs();
        JCommander cmd = JCommander.newBuilder()
                .addObject(args)
                .build();
        try {
            cmd.parse(argv);
	    if(args.type == null || "".equals(args.type)){
		System.out.println("---------------1");
		args.ok = false;
                args.types = new Class[0];
		args.values = new Object[0];
	    }else{
		System.out.println("---------------2");
                args.ok = true;
		String[] argsTypes = args.type.split(" ");
		String[] argsValues = args.value.split(" ");
		args.types = new Class[argsTypes.length];
		args.values = new Object[argsValues.length];
	        initTypeAndValue(argsTypes, argsValues, args.types, args.values);
	    }
	    if(args.constructorType == null || "".equals(args.constructorType)){
		System.out.println("---------------3");
		args.constructorTypes = new Class[0];
		args.constructorValues = new Object[0];
            } else{
		System.out.println("---------------4");
		String[] constructorTypes = args.constructorType.split(" ");
		String[] constructorValues = args.constructorValue.split(" ");
		args.constructorTypes = new Class[constructorTypes.length];
		args.constructorValues = new Object[constructorValues.length];
	        initTypeAndValue(constructorTypes,  constructorValues, args.constructorTypes, args.constructorValues);
	    }
	} catch (ParameterException ignored) {
	    System.out.println(ignored.getMessage());
	} catch (Exception e){
	    System.out.println(e.getMessage());
	}
        return args;
    }

    public static void initTypeAndValue(String[] typeStrings, String[] valueStrings, Class[] types, Object[] values) throws Exception{
	for(int i=0; i<typeStrings.length; i++){
	    switch(typeStrings[i]){
		case "int":
		   types[i] = int.class;
		   values[i] = Integer.parseInt(valueStrings[i]);
		   break;
		case "double":
		    types[i] = double.class;
		    values[i] = Double.parseDouble(valueStrings[i]);
		    break;
		case "boolean":
		    types[i] = boolean.class;
		    values[i] = Boolean.parseBoolean(valueStrings[i]);
		    break;
		case "float":
		    types[i] = float.class;
		    values[i] = Float.parseFloat(valueStrings[i]);
		    break;
		case "string":
		    types[i] = java.lang.String.class;
		    values[i] = valueStrings[i];
		    break;
		case "String":
		    types[i] = java.lang.String.class;
		    values[i] = valueStrings[i];
		default:
		    types[i] = Class.forName(typeStrings[i]);
		    Constructor c = types[i].getConstructor(java.lang.String.class);
		    values[i] = c.newInstance(valueStrings[i]);
	    }
	    System.out.println(Arrays.toString(types));
	    System.out.println(Arrays.toString(values));
	}
    }
}

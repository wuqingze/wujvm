package com.github.jvmgo.reflect;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.util.List;
import java.lang.reflect.*;

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

    @Parameter(description = "main class and args")
    public List<String> mainClassAndArgs;

    public Class[] types;

    public Object[] values;

    public boolean ok;

    static CustomerArgs parse(String[] argv) {
        CustomerArgs args = new CustomerArgs();
        JCommander cmd = JCommander.newBuilder()
                .addObject(args)
                .build();
        try {
            cmd.parse(argv);
	    if(args.type == null || "".equals(args.type)){
		args.ok = false;
                args.types = new Class[0];
		args.values = new Object[0];
	    }else{
                args.ok = true;
	    }

	    String[] types = args.type.split(";");
	    args.types = new Class[types.length];
            String[] values = args.value.split(";");
	    args.values = new Object[values.length];
	    for(int i=0; i<types.length; i++){
		switch(types[i]){
		    case "int":
		       args.types[i] = int.class;
		       args.values[i] = Integer.parseInt(values[i]);
		       break;
		    case "double":
			args.types[i] = double.class;
			args.values[i] = Double.parseDouble(values[i]);
			break;
		    case "boolean":
			args.types[i] = boolean.class;
			args.values[i] = Boolean.parseBoolean(values[i]);
			break;
		    case "float":
			args.types[i] = float.class;
			args.values[i] = Float.parseFloat(values[i]);
			break;
		    case "string":
			args.types[i] = java.lang.String.class;
			args.values[i] = values[i];
			break;
		    case "String":
			args.types[i] = java.lang.String.class;
			args.values[i] = values[i];
		    default:
			args.types[i] = Class.forName(types[i]);
			Constructor c = args.types[i].getConstructor(java.lang.String.class);
			args.values[i] = c.newInstance(values[i]);
		}
	    }

	} catch (ParameterException ignored) {
	    System.out.println(ignored.getMessage());
	} catch (Exception e){
	    System.out.println(e.getMessage());
	}
        return args;
    }

}

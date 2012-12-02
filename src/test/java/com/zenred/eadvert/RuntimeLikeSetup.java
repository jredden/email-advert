package com.zenred.eadvert;


import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.FileSystemResource;



public class RuntimeLikeSetup {
	
    GenericApplicationContext ctx = new GenericApplicationContext();
    XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
    private static RuntimeLikeSetup singleRuntimeLikeSetup=null;
    //make as singleton since it's memory intensive
    public static RuntimeLikeSetup getRuntimeLikeSetup(){
    	if(singleRuntimeLikeSetup==null){
    		singleRuntimeLikeSetup=new RuntimeLikeSetup();
    	}
    	return singleRuntimeLikeSetup;
    }
    
    private RuntimeLikeSetup(){
	    xmlReader.loadBeanDefinitions(new FileSystemResource("./src/main/webapp/WEB-INF/admin-controller-servlet.xml"));
	 }
    public GenericApplicationContext getContext(){
    	return ctx;
    }
    public Object getBean(String name){
    	return ctx.getBean(name);
    }
    


}

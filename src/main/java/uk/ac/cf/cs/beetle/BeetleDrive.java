package uk.ac.cf.cs.beetle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeetleDrive {
    public static void main(String args[]) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");


    }
}

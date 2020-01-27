package com.phonebook.main;

import com.phonebook.spring.ApplicationConfig;
import com.phonebook.spring.PhoneBook;
import com.phonebook.spring.PhoneBookFormatter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * PhoneBook entry point
 */
public class PhoneBookMain {

    public static void main(String[] args) {
        ApplicationContext context = newApplicationContext(args);

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(System.getProperty("line.separator"));

        PhoneBook phoneBook = context.getBean("phoneBook", PhoneBook.class);
        PhoneBookFormatter renderer = (PhoneBookFormatter) context.getBean("phoneBookFormatter");

        renderer.info("type 'exit' to quit.");
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.equals("exit")) {
                renderer.info("Have a good day...");
                break;
            }
            if (line.equals("SHOW")) {
                Map<String, Set<String>> map = phoneBook.findAll();
                renderer.show(map);
            }
            else if (line.startsWith("ADD")) {
                String[] s= line.split(" ");
                String name = s[1];
                String[] phoneNumbers = s[2].split(",");
                for (String number : phoneNumbers) {
                    phoneBook.addPhone(name, number);
                }
            }
            else if (line.startsWith("REMOVE_PHONE")) {
                String[] s= line.split(" ");
                String phoneNumber = s[1];

                try {
                    phoneBook.removePhone(phoneNumber);
                } catch (Exception e) {
                    renderer.error(e);
                }
            }
        }
    }

    static ApplicationContext newApplicationContext(String... args) {
        return args.length > 0 && args[0].equals("classPath")
                ? new ClassPathXmlApplicationContext("application-config.xml")
                : new AnnotationConfigApplicationContext(ApplicationConfig.class);
    }
}

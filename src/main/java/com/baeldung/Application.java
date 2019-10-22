package com.baeldung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.baeldung.persistence.model.Book;
import com.baeldung.persistence.repo.BookRepository;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = "com.baeldung")
@EnableJpaRepositories("com.baeldung.persistence.repo")
@EntityScan("com.baeldung.persistence.model")
public class Application {

	@Autowired
    private BookRepository bookRepository;
	
    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("hello world, I have just started up");
        
        addBook("SpringBoot for Dummies", "John Smith");
        addBook("OpenShift for Dummies", "Mary Jones");
        addBook("Crossing the Delaware", "George Washington");
    }
    
    public void addBook(String title, String author) {
    	Book	book = new Book(title, author);
    	
    	this.bookRepository.save(book);
    }
}

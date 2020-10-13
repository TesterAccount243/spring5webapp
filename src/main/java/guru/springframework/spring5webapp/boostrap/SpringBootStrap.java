package guru.springframework.spring5webapp.boostrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringBootStrap implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public SpringBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Book notion = new Book("Sometimes a Great Notion", "224355");
        Book cuckoo = new Book("One Flew over the cukoo's nest", "5654336");
        Author keasey = new Author("Ken", "Keasey");

        Publisher random = new Publisher("Random House", "123, New York, New York");
        publisherRepository.save(random);

        keasey.addBook(notion);
        keasey.addBook(cuckoo);
        authorRepository.save(keasey);

        notion.addAuthor(keasey);
        cuckoo.addAuthor(keasey);
        bookRepository.save(notion);
        bookRepository.save(cuckoo);

        notion.setPublisher(random);
        cuckoo.setPublisher(random);
        bookRepository.save(notion);
        bookRepository.save(cuckoo);

        random.addBook(cuckoo);
        random.addBook(notion);
        publisherRepository.save(random);

        System.out.println("Starting --------");
        System.out.println("Authors " + authorRepository.count());
        System.out.println("Books " + bookRepository.count());
        System.out.println("Publishers " + publisherRepository.count());
        System.out.println("Publisher's books " + random.getBooks().size());

    }
}

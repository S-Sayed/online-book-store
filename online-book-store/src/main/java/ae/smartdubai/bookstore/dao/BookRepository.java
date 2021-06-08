package ae.smartdubai.bookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ae.smartdubai.bookstore.model.Book;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, String> {

}

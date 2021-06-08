package ae.smartdubai.bookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ae.smartdubai.bookstore.model.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String> {

}
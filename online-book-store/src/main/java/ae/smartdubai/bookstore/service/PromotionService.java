package ae.smartdubai.bookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ae.smartdubai.bookstore.dao.PromotionRepository;
import ae.smartdubai.bookstore.model.Promotion;

@Service
public class PromotionService {

	@Autowired
	private PromotionRepository promotionRepository;

	public Optional<Promotion> getPromotionByCode(String promotionCode) {
		return promotionRepository.findById(promotionCode);
	}
}

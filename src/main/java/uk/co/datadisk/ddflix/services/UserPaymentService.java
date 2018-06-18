package uk.co.datadisk.ddflix.services;

import uk.co.datadisk.ddflix.dto.models.UserPaymentDTO;
import uk.co.datadisk.ddflix.entities.user.UserPayment;

public interface UserPaymentService {
	UserPayment findById(Long id);
	void removeById(Long id);
	void savePayment(UserPaymentDTO userPaymentDTO);
	void setDefault(Long userPaymentId, Long userId);
	UserPaymentDTO getUserPaymentDTO(Long id);
}

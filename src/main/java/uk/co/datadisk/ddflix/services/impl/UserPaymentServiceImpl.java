package uk.co.datadisk.ddflix.services.impl;

import org.springframework.stereotype.Service;
import uk.co.datadisk.ddflix.dto.mapper.UserPaymentMapper;
import uk.co.datadisk.ddflix.dto.models.UserPaymentDTO;
import uk.co.datadisk.ddflix.entities.user.UserPayment;
import uk.co.datadisk.ddflix.repositories.user.UserPaymentRepository;
import uk.co.datadisk.ddflix.services.UserPaymentService;

import java.util.List;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {

	private final UserPaymentRepository userPaymentRepository;
	private final UserPaymentMapper userPaymentMapper;

	public UserPaymentServiceImpl(UserPaymentRepository userPaymentRepository, UserPaymentMapper userPaymentMapper) {
		this.userPaymentRepository = userPaymentRepository;
		this.userPaymentMapper = userPaymentMapper;
	}

	public UserPayment findById(Long id) {
		return userPaymentRepository.getOne(id);
	}
	
	public void removeById(Long id) {
		userPaymentRepository.deleteById(id);
	}

	@Override
	public void savePayment(UserPaymentDTO userPaymentDTO) {
		UserPayment userPayment = userPaymentMapper.UserPaymentDTOToUserPayment(userPaymentDTO);
		userPaymentRepository.save(userPayment);
	}

	@Override
	public void setDefault(Long userPaymentId, Long userId) {

		List<UserPayment> userPaymentList = userPaymentRepository.findAllUserPaymentsByUser_Id(userId);

		for (UserPayment userPayment : userPaymentList) {
			if(userPayment.getId() == userPaymentId) {
				System.out.println("Default credit card #" + userPayment.getId());
				userPayment.setDefaultPayment(true);
			} else {
				System.out.println("Remove default credit card #" + userPayment.getId());
				userPayment.setDefaultPayment(false);
			}
			userPaymentRepository.save(userPayment);
		}
	}

    @Override
    public UserPaymentDTO getUserPaymentDTO(Long id) {
		return userPaymentMapper.UserPaymentToUserPaymentDTO(userPaymentRepository.findById(id).get());
    }
}
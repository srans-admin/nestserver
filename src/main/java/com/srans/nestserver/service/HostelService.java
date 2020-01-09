package com.srans.nestserver.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.util.ConsolidatedHostel;
import com.srans.nestserver.util.NSConstants;

@Service
public class HostelService {

	private Logger logger = LoggerFactory.getLogger(HostelService.class);

	@Autowired
	private UserRepository userRepository;

	public List<ConsolidatedHostel> getHostelDetails() {

		logger.debug("In::getHostelDetails");

		// List<User> userDetails = userRepository.findAll();
		List<Object> hostelInfo = userRepository.getConsolidatedHostel(NSConstants.ROLE_ADMIN);
		List<ConsolidatedHostel> getconsoData = new ArrayList<>();

		for (Iterator<Object> iterator = hostelInfo.iterator(); iterator.hasNext();) {
			Object[] object = (Object[]) iterator.next();
			ConsolidatedHostel hostelData = new ConsolidatedHostel();
			for (int i = 0; i < object.length; i++) {

				switch (i) {
				case 0:
					hostelData.setHostelId(((BigInteger) object[i]).longValue());
					break;
				case 1:
					hostelData.setHostelName((String) object[i]);
					break;
				case 2:
					hostelData.setHostelAddress((String) object[i]);
					break;
				case 3:
					hostelData.setAdminId(((BigInteger) object[i]).longValue());
					break;
				case 4:
					hostelData.setAdminName((String) object[i]);
					break;
				case 5:
					hostelData.setEmail((String) object[i]);
					break;
				case 6:
					hostelData.setPhoneNo(((BigInteger) object[i]).longValue());
					break;
				case 7:
					hostelData.setsubscriptions(((BigInteger) object[i]).longValue());
					break;
				case 8:
					hostelData.setPayment(((BigInteger) object[i]).longValue());
					break;
				default:
					break;
				}

			}

			getconsoData.add(hostelData);

		}
		logger.debug("Out::getHostelDetails");
		return (getconsoData);

	}
}

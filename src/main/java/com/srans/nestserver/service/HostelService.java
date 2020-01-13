package com.srans.nestserver.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.repository.AdminDetailsRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.util.ConsolidatedHostel;
import com.srans.nestserver.util.NSConstants;

@Service
public class HostelService {

	

	private Logger logger = LoggerFactory.getLogger(HostelService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HostelRepository hostelRepository;

	@Autowired
	private AdminDetailsRepository adminDetailsRepo;

	// Get All Consolidated Hostel Details
	public List<ConsolidatedHostel> getHostelDetails() {

		logger.debug("In::getHostelDetails");

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

	// Display All Hostel For Admin
	public List<Hostel> getHostelByAdminId(Long adminId) {

		Long checkAdminId = hostelRepository.checkAdminId(adminId);
		Long checkSubadminId = adminDetailsRepo.checkSubAdminDetails(adminId);
		List<Hostel> hostelData = new ArrayList<Hostel>();;

		if (checkAdminId != 0) {
			List<Object> hosteDetails = hostelRepository.getHostelDetailsForAdmin(adminId);
			//hostelData = new ArrayList<Hostel>();
			for (Iterator<Object> iterator = hosteDetails.iterator(); iterator.hasNext();) {
				Object[] object = (Object[]) iterator.next();
				Hostel hostel = new Hostel();
				for (int i = 0; i < object.length; i++) {

					if (i == 0) {
						hostel.setId(((BigInteger) object[i]).longValue());
					} else if (i == 1) {
						hostel.setAc((boolean) object[i]);
					} else if (i == 2) {
						hostel.setAdminId(((BigInteger) object[i]).longValue());
					} else if (i == 3) {
						hostel.setFridge((boolean) object[i]);
					} else if (i == 4) {
						hostel.setGym((boolean) object[i]);
					} else if (i == 5) {
						hostel.setHostelAddress((String) object[i]);
					} else if (i == 6) {
						hostel.setHostelName((String) object[i]);
					} else if (i == 7) {
						hostel.setHostelType((String) object[i]);
					} else if (i == 8) {
						hostel.setMineralWater((boolean) object[i]);
					} else if (i == 9) {
						hostel.setNumOfFloors((Integer) object[i]);
					} else if (i == 10) {
						hostel.setParking((boolean) object[i]);
					} else if (i == 11) {
						hostel.setTv((boolean) object[i]);
					}

				}

				hostelData.add(hostel);
			}
			return hostelData;

		}

		//Display All Hostel For Sub Admin
		else if (checkSubadminId != 0) {

			Long[] getAssignHostelId = hostelRepository.getSubAdminId(adminId);

			for (Long assignHostelId : getAssignHostelId) {
				List<Object> hosteDetails = hostelRepository.getHostelDetailsForSubAdmin(assignHostelId);

				for (Iterator<Object> iterator = hosteDetails.iterator(); iterator.hasNext();) {
					
					Object[] object = (Object[]) iterator.next();
					Hostel hostel = new Hostel();

					for (int i = 0; i < object.length; i++) {

						if (i == 0) {
							hostel.setId(((BigInteger) object[i]).longValue());
						} else if (i == 1) {
							hostel.setAc((boolean) object[i]);
						} else if (i == 2) {
							hostel.setAdminId(((BigInteger) object[i]).longValue());
						} else if (i == 3) {
							hostel.setFridge((boolean) object[i]);
						} else if (i == 4) {
							hostel.setGym((boolean) object[i]);
						} else if (i == 5) {
							hostel.setHostelAddress((String) object[i]);
						} else if (i == 6) {
							hostel.setHostelName((String) object[i]);
						} else if (i == 7) {
							hostel.setHostelType((String) object[i]);
						} else if (i == 8) {
							hostel.setMineralWater((boolean) object[i]);
						} else if (i == 9) {
							hostel.setNumOfFloors((Integer) object[i]);
						} else if (i == 10) {
							hostel.setParking((boolean) object[i]);
						} else if (i == 11) {
							hostel.setTv((boolean) object[i]);
						}

					}
					hostelData.add(hostel);

				}

			}

		}
		return hostelData;

	}
}

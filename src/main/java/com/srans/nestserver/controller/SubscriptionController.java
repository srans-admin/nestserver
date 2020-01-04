package com.srans.nestserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1")
public class SubscriptionController {
	Logger logger = LoggerFactory.getLogger(SubscriptionController.class);
/*
	@Autowired
	private SubscriptionRepository subscriptionRepo;

	@Autowired
	private NotificationRepository notificationRepo;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private NotificationUserRepository notificationUserRepo;

	@Autowired
	private NiodsMailer niodsMailer;

	@PostMapping(value = "/subscriptionandnotification")
	@PreAuthorize("permitAll()")
	public User saveSubscription(@Valid @RequestBody User user)
			throws NSException, MailException, MessagingException, IOException, TemplateException {

		logger.info("IN::POST::/subscription::Savesubscription::" + user);

		Subscription responseSubscribe = subscriptionRepo.save(subscription);
		String rendomPassword;

		if (responseSubscribe.getId() != -1) {

			subscription.getNotification().setAdminId(responseSubscribe.getId());
			subscription.getNotification().setUserName(responseSubscribe.getUserName());
			Notification notification = notificationRepo.save(subscription.getNotification());

			responseSubscribe.setNotification(notification);

			String emailId = responseSubscribe.getEmail();
			rendomPassword = GenerateUniquePassword.Code();
			String subject = "Subscription";
			String ccMail = null;
			String bccMail = null;
			System.out.println(rendomPassword);
			String message = MailTemplates.ADMIN_SUBSCRIPTION_TEMPLATE
					.replaceAll("##USER_NAME##", responseSubscribe.getUserName())
					.replaceAll("##PASSWORD##", rendomPassword).replaceAll("##NAME##", responseSubscribe.getName());

			niodsMailer.sendEmail(emailId, subject, ccMail, bccMail, message);

		} else {
			throw new NSException("Unable to save subscription details");
		}

		responseSubscribe.setPassword(rendomPassword);

		String superAdminUserName = null;
		Long superAdminCode = (long) 0;
		String url = "http://localhost:9090/uaa-server/v1/superAdminUserName";
		Object[] object = restTemplate.getForObject(url, Object[].class);

		Long code = (long) 1;
		Long[] getNotificationId = notificationRepo.getNotificationId();
		for (int i = 0; i < object.length; i++) {

			System.out.println(object.length);
			superAdminUserName = (String) object[i];
			superAdminCode = code * 100;

			for (int j = 0; j < getNotificationId.length; j++) {

				NotificationUser notificationUser = new NotificationUser();
				notificationUser.setNotificationId(getNotificationId[j]);
				notificationUser.setSuperAdminCode(superAdminCode);
				notificationUser.setsuperAdminUsername(superAdminUserName);
				notificationUserRepo.save(notificationUser);

			}

			code++;
		}

		logger.info("OUT::POST::/subscription::savesubscription::" + subscription);
		return (responseSubscribe);

	}

	@GetMapping("/subscriptionandnotification/{username}/")
	@PreAuthorize("permitAll()")
	public Set<NotificationUtility> getSuperAdminUsername(@PathVariable(value = "username") String superAdminUsername)
			throws NSException {

		Set<Object> notificationInfo = notificationRepo.findNewNotification(superAdminUsername);

		Set<NotificationUtility> notifydata = new HashSet<>();
		for (Iterator<Object> iterator = notificationInfo.iterator(); iterator.hasNext();) {

			Object[] obj = (Object[]) iterator.next();

			NotificationUtility notificationData = new NotificationUtility();
			for (int i = 0; i < obj.length; i++) {

				if (i == 0) {
					notificationData.setMessage((String) obj[i]);

				} else if (i == 1) {
					notificationData.setNotificationCategory((String) obj[i]);
				}

				else if (i == 2) {
					notificationData.setUserRole((String) obj[i]);
				} else if (i == 3) {
					notificationData.setViewStatus((Character) obj[i]);
				} else if (i == 4) {
					notificationData.setNotificationId(((BigInteger) obj[i]).longValue());
				}
				notifydata.add(notificationData);
			}

		}

		return (notifydata);

	}

	@PutMapping("/subscriptionandnotification/{notificationid}")
	@PreAuthorize("permitAll()")

	public Notification updateNotification(@PathVariable Long notificationid,
			@Valid @RequestBody Notification notificationRequest) {

		return notificationRepo.findById(notificationid).map(notification -> {

			notification.setViewStatus(notificationRequest.getViewStatus());

			return notificationRepo.save(notification);
		}).orElseThrow(() -> new ResourceNotFoundException("NotificationId " + notificationid + " not found"));
	}

	@DeleteMapping("/subscriptionandnotification/{notificationid}")
	@PreAuthorize("permitAll()")

	public ResponseEntity<?> deleteNotification(@PathVariable Long notificationid) {
		return notificationRepo.findById(notificationid).map(notification -> {
			notificationRepo.delete(notification);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("NotificationId " + notificationid + " not found"));
	}*/

}

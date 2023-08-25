package com.jjuarez.gila;

import com.jjuarez.gila.constants.ApiConstants;
import com.jjuarez.gila.entity.BroadcastChannel;
import com.jjuarez.gila.entity.Topic;
import com.jjuarez.gila.entity.User;
import com.jjuarez.gila.repository.BroadcastChannelRepository;
import com.jjuarez.gila.repository.TopicRepository;
import com.jjuarez.gila.repository.UserRepository;
import com.jjuarez.gila.request.NotificationRequest;
import com.jjuarez.gila.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootApplication
public class GilaApiApplication {
	private static final Logger LOG = LoggerFactory.getLogger(GilaApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GilaApiApplication.class, args);
	}

	private final UserRepository userRepository;
	private final TopicRepository topicRepository;
	private final BroadcastChannelRepository broadcastChannelRepository;
	private final NotificationService notificationService;

	public GilaApiApplication(final UserRepository userRepository, final TopicRepository topicRepository,
							  final BroadcastChannelRepository broadcastChannelRepository,
							  final NotificationService notificationService) {
		this.userRepository = userRepository;
		this.topicRepository = topicRepository;
		this.broadcastChannelRepository = broadcastChannelRepository;
		this.notificationService = notificationService;
	}

	@EventListener
	public void seed(ContextRefreshedEvent event) {
		LOG.info("Seeding users...");
		final Random random = new Random();

		final int numUsers = random.nextInt(ApiConstants.MAX_APP_USERS - ApiConstants.MIN_APP_USERS + 1)
				+ ApiConstants.MIN_APP_USERS;

		final List<Topic> allTopics = topicRepository.findAll();
		final List<BroadcastChannel> allChannels = broadcastChannelRepository.findAll();

		IntStream.range(0, numUsers).forEach(i -> {
			final List<Topic> userSubscribedTopics = new ArrayList<>();
			final List<BroadcastChannel> userPreferredChannels = new ArrayList<>();

			allTopics.stream().filter(topic -> random.nextBoolean()).forEach(userSubscribedTopics::add);

			allChannels.stream().filter(channel -> random.nextBoolean()).forEach(userPreferredChannels::add);

			final User user = new User.Builder()
					.name("User " + i)
					.email("user" + i + "@example.com")
					.phone("0123456789")
					.subscribedTopics(userSubscribedTopics)
					.preferredChannels(userPreferredChannels)
					.build();

			userRepository.save(user);
		});

		LOG.info("Seeding first broadcast...");
		NotificationRequest notificationRequest
				= new NotificationRequest("FINANCE", "My first broadcast");
		notificationService.broadcast(notificationRequest);
	}

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		config.addAllowedMethod("*");
		config.addAllowedHeader("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}

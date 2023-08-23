package com.jjuarez.gila;

import com.jjuarez.gila.constants.ApiConstants;
import com.jjuarez.gila.entity.BroadcastChannel;
import com.jjuarez.gila.entity.Topic;
import com.jjuarez.gila.entity.User;
import com.jjuarez.gila.repository.BroadcastChannelRepository;
import com.jjuarez.gila.repository.TopicRepository;
import com.jjuarez.gila.repository.UserRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(GilaApiApplication.class, args);
	}

	private final UserRepository userRepository;
	private final TopicRepository topicRepository;
	private final BroadcastChannelRepository broadcastChannelRepository;

	public GilaApiApplication(final UserRepository userRepository, final TopicRepository topicRepository,
							  final BroadcastChannelRepository broadcastChannelRepository) {
		this.userRepository = userRepository;
		this.topicRepository = topicRepository;
		this.broadcastChannelRepository = broadcastChannelRepository;
	}

	@EventListener
	public void seed(ContextRefreshedEvent event) {
		final Random random = new Random();

		final int numUsers = random.nextInt(ApiConstants.MAX_APP_USERS - ApiConstants.MIN_APP_USERS + 1)
				+ ApiConstants.MIN_APP_USERS;

		final List<Topic> allTopics = topicRepository.findAll();
		final List<BroadcastChannel> allChannels = broadcastChannelRepository.findAll();

		IntStream.range(0, numUsers).forEach(i -> {
			List<Topic> userSubscribedTopics = new ArrayList<>();
			List<BroadcastChannel> userPreferredChannels = new ArrayList<>();

			allTopics.stream().filter(topic -> random.nextBoolean()).forEach(userSubscribedTopics::add);

			allChannels.stream().filter(channel -> random.nextBoolean()).forEach(userPreferredChannels::add);

			User user = new User.Builder()
					.name("User " + i)
					.email("user" + i + "@example.com")
					.phone("0123456789")
					.subscribedTopics(userSubscribedTopics)
					.preferredChannels(userPreferredChannels)
					.build();

			userRepository.save(user);
		});
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		config.addAllowedMethod("*");
		config.addAllowedHeader("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}

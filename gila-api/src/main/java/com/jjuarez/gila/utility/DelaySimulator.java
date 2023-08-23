package com.jjuarez.gila.utility;

import com.jjuarez.gila.constants.ApiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class DelaySimulator {
    private static final Logger LOG = LoggerFactory.getLogger(DelaySimulator.class);
    private static final Random random = new Random();

    public static void simulateDelay() {
        LOG.debug("Simulating a delay");
        final int randomDelay = random.nextInt(
                ApiConstants.MAX_RANDOM_DELAY - ApiConstants.MIN_RANDOM_DELAY + 1)
                + ApiConstants.MIN_RANDOM_DELAY;
        try {
            Thread.sleep(randomDelay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

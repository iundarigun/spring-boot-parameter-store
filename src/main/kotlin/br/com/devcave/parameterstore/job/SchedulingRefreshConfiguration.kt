package br.com.devcave.parameterstore.job

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.endpoint.RefreshEndpoint
import org.springframework.context.annotation.Configuration
import org.springframework.util.StopWatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Configuration
class SchedulingRefreshConfiguration(
    @Value("\${spring.cloud.config.refresh-interval-in-seconds:300}")
    private val refreshIntervalInSeconds: Long,
    private val refreshEndpoint: RefreshEndpoint
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    private val scheduledExecutor = Executors.newSingleThreadScheduledExecutor().also {
        logger.info("creating schedule interval $refreshIntervalInSeconds")
        it.scheduleAtFixedRate(
            {
                runCatching {
                    refreshEndpoint.refresh()
                }
            },
            refreshIntervalInSeconds,
            refreshIntervalInSeconds,
            TimeUnit.SECONDS
        )
    }
}
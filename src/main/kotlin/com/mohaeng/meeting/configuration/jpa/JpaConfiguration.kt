package com.mohaeng.meeting.configuration.jpa

import com.mohaeng.meeting.infrastructure.persistence.Persistence
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 * Created by ShinD on 2022/09/07.
 */
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackageClasses = [Persistence::class])
class JpaConfiguration {
}
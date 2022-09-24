package com.mohaeng.meeting.global.configuration.jpa

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

/**
 * Created by ShinD on 2022/09/07.
 */
@Configuration
@EnableJpaAuditing
//@EnableJpaRepositories(basePackageClasses = [Persistence::class])
class JpaConfiguration {
}
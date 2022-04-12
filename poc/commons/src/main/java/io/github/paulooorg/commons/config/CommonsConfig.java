package io.github.paulooorg.commons.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ RequestLoggingFilterConfig.class })
public class CommonsConfig {}

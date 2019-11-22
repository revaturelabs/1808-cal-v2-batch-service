package com.revaturelabs.caliber.batch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}") String jwkSetUri;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests(authorizeRequests ->
        authorizeRequests
          .mvcMatchers("*h2-console*").permitAll()
          .mvcMatchers("**").permitAll()
      )
      .oauth2ResourceServer().jwt().jwtAuthenticationConverter(grantedAuthoritiesExtractor());
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
  }

  private Converter<Jwt, AbstractAuthenticationToken> grantedAuthoritiesExtractor() {
    JwtAuthenticationConverter jwtAuthenticationConverter =
            new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter
            (new GrantedAuthoritiesExtractor());
    return jwtAuthenticationConverter;
  }

  static class GrantedAuthoritiesExtractor
          implements Converter<Jwt, Collection<GrantedAuthority>> {

    public Collection<GrantedAuthority> convert(Jwt jwt) {
      Collection<GrantedAuthority> authorities = new LinkedHashSet<>();

      // Add user groups to list of authorities which can be a simple string or JSON array of groups
      Object groups = jwt.getClaims().get("groups");
      if (groups instanceof String) {
        authorities.add(new SimpleGrantedAuthority((String) groups));
      } else if (groups instanceof List) {
        for(Object group: (List)groups) {
          authorities.add(new SimpleGrantedAuthority((String) group));
        }
      }

      // Add any scope that the jwt has attached multiple scopes are separated with ,
      String[] scopes = ((String) jwt.getClaims().get("scope")).split(",");
      for(String s: scopes) {
        authorities.add(new SimpleGrantedAuthority(s));
      }

      return authorities;
    }
  }
}

package crud.security;

import crud.model.User;
import crud.repository.UserRepository;
import crud.security.oauth.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CompositeFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.ArrayList;

@EnableWebSecurity
@Configuration
@EnableOAuth2Client
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                    .antMatchers(
                            "/registration",
                            "/login",
//                        "/js/registration.js",
//                        "/js/login.js",
//                        "/js/lib/jquery-3.4.1.min.js",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js")
                        .permitAll()
                    .antMatchers("/oauth2/**")
                        .permitAll()
                    .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/crud")
                    .permitAll()
                .and()
            .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessUrl("/login")
                    .permitAll()
                .and()
                    .httpBasic()
                .and()
            .oauth2Login()
                .authorizationEndpoint()
//                    .baseUri("/oauth2/authorize")
//                    .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                    .and()
//                .redirectionEndpoint()
//                    .baseUri("/oauth2/callback/*")
                .userInfoEndpoint()
                    .userService(customOAuth2UserService);
//                    .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)

        http.csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    private Filter ssoFilter() {
        var filter = new CompositeFilter();
        var filters = new ArrayList<Filter>();

        OAuth2ClientAuthenticationProcessingFilter googleFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/google");
        var facebookTemplate = new OAuth2RestTemplate(google(), oauth2ClientContext);
        googleFilter.setRestTemplate(facebookTemplate);
        var tokenServices = new UserInfoTokenServices(googleResource().getUserInfoUri(), google().getClientId());
        tokenServices.setRestTemplate(facebookTemplate);
        googleFilter.setTokenServices(tokenServices);
        filters.add(googleFilter);

        OAuth2ClientAuthenticationProcessingFilter vkFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/vk");
        var vkTemplate = new OAuth2RestTemplate(vk(), oauth2ClientContext);
        vkFilter.setRestTemplate(vkTemplate);
        tokenServices = new UserInfoTokenServices(vkResource().getUserInfoUri(), vk().getClientId());
        tokenServices.setRestTemplate(vkTemplate);
        vkFilter.setTokenServices(tokenServices);
        filters.add(vkFilter);

        filter.setFilters(filters);
        return filter;
    }

    @Bean
    @ConfigurationProperties("google.client")
    public AuthorizationCodeResourceDetails google() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("google.resource")
    public ResourceServerProperties googleResource() {
        return new ResourceServerProperties();
    }

    @Bean
    @ConfigurationProperties("vk.client")
    public AuthorizationCodeResourceDetails vk() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("vk.resource")
    public ResourceServerProperties vkResource() {
        return new ResourceServerProperties();
    }

    @Bean
    public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        var registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

//    @Bean
//    public PrincipalExtractor principalExtractor(UserRepository repository) {
//        return map -> {
//            var id = (Long) map.get("sub");
//            var user = repository.findById(id).orElseGet(() -> {
//                var newUser = new User();
//
//                newUser.setId(id);
//                newUser.setUsername((String) map.get("name"));
//
//                return newUser;
//            });
//
//            return repository.save(user);
//        };
//    }
}

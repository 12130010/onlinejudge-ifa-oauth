package onlinejudge.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import onlinejudge.oauth.service.MongoUserDetailsService;

@Configuration
@EnableAuthorizationServer
//@PropertySource("classpath:config.properties")
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

	private TokenStore tokenStore = new InMemoryTokenStore();

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	private MongoUserDetailsService userDetailsService;

	@Autowired
	private Environment env;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		// TODO persist clients details

		// @formatter:off
//		clients.inMemory()
//				.withClient("browser")
//				.authorizedGrantTypes("refresh_token", "password")
//				.scopes("ui")
//		.and()
//				.withClient("account-service")
//				.secret(env.getProperty("ACCOUNT_SERVICE_PASSWORD"))
//				.authorizedGrantTypes("client_credentials", "refresh_token")
//				.scopes("server")
//		.and()
//				.withClient("statistics-service")
//				.secret(env.getProperty("STATISTICS_SERVICE_PASSWORD"))
//				.authorizedGrantTypes("client_credentials", "refresh_token")
//				.scopes("server")
//		.and()
//				.withClient("notification-service")
//				.secret(env.getProperty("NOTIFICATION_SERVICE_PASSWORD"))
//				.authorizedGrantTypes("client_credentials", "refresh_token")
//				.scopes("server");
		clients.inMemory()
		.withClient("browser")
		.authorizedGrantTypes("refresh_token", "password")
		.scopes("ui")
.and()
		.withClient(env.getProperty("onlinejudge.ms.user.id"))
		.secret(env.getProperty("onlinejudge.ms.user.password"))
		.authorizedGrantTypes("client_credentials", "refresh_token", "authorization_code")
		.scopes("server")
//.and()
//		.withClient("statistics-service")
//		.secret(env.getProperty("STATISTICS_SERVICE_PASSWORD","STATISTICS_SERVICE_PASSWORD"))
//		.authorizedGrantTypes("client_credentials", "refresh_token")
//		.scopes("server")
//.and()
//		.withClient("notification-service")
//		.secret(env.getProperty("NOTIFICATION_SERVICE_PASSWORD","NOTIFICATION_SERVICE_PASSWORD"))
//		.authorizedGrantTypes("client_credentials", "refresh_token")
//		.scopes("server")
		;
		// @formatter:on
		System.out.println(env.getProperty("onlinejudge.ms.user.password"));
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.tokenStore(tokenStore)
				.authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer
				.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()");
	}
}
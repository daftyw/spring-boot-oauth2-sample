package bw.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * Created by wind on 12/6/2016 AD.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizeServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("resourceId:oauth-app-1")
    private String resId;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .inMemory()
                .withClient("trusted-client")
                    .authorizedGrantTypes("password", "refresh_token", "implicit")
                    .authorities("USER")
                    .scopes("read", "write", "trust")
                    .resourceIds(resId)
                    .accessTokenValiditySeconds(500)
            .and()
                .withClient("client")
                    .authorizedGrantTypes("client_credentials")
                    .authorities("USER")
                    .scopes("read", "write", "trust")
                    .resourceIds(resId)
                    .secret("passw");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
}

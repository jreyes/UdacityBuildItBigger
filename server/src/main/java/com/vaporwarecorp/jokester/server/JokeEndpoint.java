package com.vaporwarecorp.jokester.server;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

@Api(
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "server.joke.vaporwarecorp.com",
                ownerName = "server.joke.vaporwarecorp.com",
                packagePath = ""
        )
)
public class JokeEndpoint {
// -------------------------- OTHER METHODS --------------------------

    @ApiMethod(name = "getJoke")
    public JokeBean getJoke() {
        return new JokeBean(Jokester.random());
    }
}

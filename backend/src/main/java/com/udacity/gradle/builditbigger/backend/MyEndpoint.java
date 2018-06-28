package com.udacity.gradle.builditbigger.backend;

import com.example.joketeller.JokeProvider;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {
    private JokeProvider mJokeProvider = new JokeProvider();

    @ApiMethod(name = "fetchJoke")
    public MyBean fetchJoke() {
        MyBean myBean = new MyBean();
        myBean.setData(mJokeProvider.getJoke());
        return myBean;
    }

}

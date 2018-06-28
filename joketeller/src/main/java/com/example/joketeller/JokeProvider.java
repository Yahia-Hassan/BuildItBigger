package com.example.joketeller;

import java.util.Random;

public class JokeProvider {

    private final String[] jokes = new String[]{
            "Can a kangaroo jump higher than a house?  \nOf course, a house doesn’t jump at all.",
            "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.",
            "What happens to a frog's car when it breaks down? \nIt gets toad away.",
            "If you ever get cold, stand in the corner of a room for a while. \nThey're usually 90 degrees.",
            "What's the best thing about Switzerland? \nI don't know, but the flag is a big plus."
    };

    public String getJoke() {
        return jokes[new Random().nextInt(jokes.length)];
    }
}

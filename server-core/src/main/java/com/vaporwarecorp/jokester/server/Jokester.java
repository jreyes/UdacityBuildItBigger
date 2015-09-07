package com.vaporwarecorp.jokester.server;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Jokester {
// ------------------------------ FIELDS ------------------------------

    private static final Jokester instance = new Jokester();

    private List<String> jokes;
    private Random random;

// -------------------------- STATIC METHODS --------------------------

    public static String random() {
        return instance.jokes.get(instance.random.nextInt(instance.jokes.size()));
    }

// --------------------------- CONSTRUCTORS ---------------------------

    private Jokester() {
        random = new Random();
        jokes = Arrays.asList(
                "https://s-media-cache-ak0.pinimg.com/736x/ed/d7/ec/edd7ece9b31d96b72885dee367619a30.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/64/b0/36/64b0361e64262da192d90d40294e3af1.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/7f/c2/0c/7fc20cafee5f957fd4f0f8c5e63591b5.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/f0/3e/dc/f03edcb46ff1e9c03fa705f4a8484c7d.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/ed/d4/c3/edd4c3930b879a170b4631a7c6a35275.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/f4/b7/6f/f4b76fef3b63d380703e64939a20e62f.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/52/c5/c7/52c5c7eff23bc1c5b077b0501c476153.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/4f/f2/b8/4ff2b872cc0ac2a4ff98dcfb16f9baf6.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/35/2a/01/352a01b02a0846b0a4f0100525d6457b.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/ee/43/d8/ee43d8279ed0583507a5c2f2f2b187dd.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/48/e1/8e/48e18e07c0fe835e653fbdc9ed5e904e.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/1c/c5/6e/1cc56e82ace3ee507df22a7c0e0cda3d.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/83/ed/4a/83ed4abf0a15abe18ba8ccc09a16d3dd.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/f4/ee/df/f4eedf0a520db7b058d3147e6a359dca.jpg",
                "https://s-media-cache-ak0.pinimg.com/236x/f2/3a/0d/f23a0dd709e8e5b4db4e9a9699bf3c88.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/4c/a3/8d/4ca38da4a3bd294b3a76e15d5b9e8db4.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/51/10/b0/5110b06600960c266bb2c58c3bd4b882.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/b7/f9/07/b7f907fb820aa5866afb35ddddb380d3.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/a9/db/0a/a9db0a6c08e97246d1610166171b4d25.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/46/d7/d1/46d7d17f1f26d6c2b85a9fb1ffad8066.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/3f/08/1d/3f081d1d43303dda0e61f557ea457d0a.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/c4/22/5c/c4225c72516605e85f4a7b4886f5a5d7.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/34/ab/65/34ab653496ee65197cf70547c1143f73.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/53/e8/7b/53e87b67f8ced37c1e12c4bf3378dce7.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/05/1c/2e/051c2eabd29c7b96271f17e0c1f75825.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/e2/47/e2/e247e21b7777441c9efa3a564b1b0162.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/32/a3/80/32a3804566ad638d25280649c47c8438.jpg"
        );
    }
}

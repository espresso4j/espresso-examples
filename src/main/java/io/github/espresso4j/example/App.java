package io.github.espresso4j.example;

import io.github.espresso4j.espresso.*;
import io.github.espresso4j.jettino.JettinoAdapter;

public class App {

    public static void main(String[] args) throws Exception {
        JettinoAdapter ja = new JettinoAdapter();
        ja.start((request) -> new Response()
                 .setStatus(200)
                 .addHeader("Content-Type", "text/html")
                 .setBody(String.format("<h1>It works</h1><p>%s</p>", request)));
    }

}

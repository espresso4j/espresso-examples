package io.github.espresso4j.example;

import io.github.espresso4j.espresso.*;
import io.github.espresso4j.latte.Latte;
import io.github.espresso4j.jettino.JettinoAdapter;

public class App {

    public static void main(String[] args) throws Exception {
        JettinoAdapter ja = new JettinoAdapter();

        Espresso latte = Latte.sync()
            .on("/", (req) -> Response.of(200)
                .header("Content-Type", "text/html")
                .body(String.format("<h1>It works</h1><p>%s</p>", req)))
            .on("/user/:name", (req) -> Response.of(200)
                .header("Content-Type", "text/plain")
                .body(String.format("Hello, %s", Latte.getExtension(req).get("name"))))
            .buildSync();

        ja.start(latte);
    }

}

package io.github.espresso4j.example;

import io.github.espresso4j.espresso.*;
import io.github.espresso4j.latte.Latte;
import io.github.espresso4j.sugar.Sugar;
import io.github.espresso4j.jettino.JettinoAdapter;

public class App {

    public static void main(String[] args) throws Exception {
        JettinoAdapter ja = new JettinoAdapter();

        Espresso latte = Latte.by(Espresso.class)
            .on("/", (req) -> Response.of(200)
                .header("Content-Type", "text/html")
                .body(String.format("<h1>It works</h1><p>%s</p>", req)))
            .on("/user/:name", (req) -> Response.of(200)
                .header("Content-Type", "text/plain")
                .body(String.format("Hello, %s", Latte.extension(req).get("name"))))
            .on("/user/:name/update", (req) -> Response.of(200)
                .header("Content-Type", "text/plain")
                .body(String.format("Updating user %s to age %s",
                                    Latte.extension(req).get("name"),
                                    Sugar.extension(req).get("age"))))
            .intoEspresso();

        ja.start(new Sugar(latte));
    }

}

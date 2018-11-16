package io.github.espresso4j.example;

import io.github.espresso4j.espresso.*;
import io.github.espresso4j.latte.Latte;
import io.github.espresso4j.sugar.Sugar;
import io.github.espresso4j.jettino.Jettino;

public class App {

    public static void main(String[] args) throws Exception {
        Jettino ja = new Jettino();

        Espresso latte = Latte.by(Espresso.class)
            .on(Request.Method.GET, "/", (req) -> Response.of(200)
                .header("Content-Type", "text/html")
                .body(String.format("<h1>It works</h1><p>%s</p>", req)))
            .on(Request.Method.GET, "/user/:name", (req) -> Response.of(200)
                .header("Content-Type", "text/plain")
                .body(String.format("Hello, %s", Latte.extension(req).get("name"))))
            .on(Request.Method.POST, "/user/:name/update", (req) -> Response.of(200)
                .header("Content-Type", "text/plain")
                .body(String.format("Updating user %s to age %s",
                                    Latte.extension(req).get("name"),
                                    Sugar.extension(req).get("age"))))
            .intoEspresso();

        ja.start(new Sugar(latte));
    }

}

package com.ps.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InputManager {
    public static void setInput(String pkgName) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/" + pkgName +"/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
    }
}

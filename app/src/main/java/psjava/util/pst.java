package psjava.util;

import java.io.*;

public class pst {

    public static TimeUtil tu;

    public static void init() {
        tu = new TimeUtil();
        tu.start();
    }

    public static void close() throws IOException {
        tu.end();
        ps.sb.append("Took ").append(tu.getDurationMs()).append(" ms").append("\n");
        ps.close();
    }
}

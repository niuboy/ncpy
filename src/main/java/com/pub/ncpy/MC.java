package com.pub.ncpy;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MC {
    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            syntaxError();
            return;
        }
        System.out.println("开始处理");
        String to = "DECRYPT";
        if (args.length == 2)
            to = args[1];
        try {
            ncpy(args[0], to);
            System.out.println("结束处理,请自行重命名文件:" + to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void syntaxError() {
        System.out.println("java -jar XX.jar from.xls [to] ");
        System.out.println("to 不带扩展名,可选,生成完自己重命名");
        System.out.println("support jdk 11+");
    }

    private static void ncpy(String from, String to) throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get(from), StandardOpenOption.READ);
        FileChannel outChennel = FileChannel.open(Paths.get(to), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);
        outChennel.transferFrom(inChannel, 0, inChannel.size());
    }
}

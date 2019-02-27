package test.gpss;
import test.gpss.greenplum.Simple;

import java.io.FileOutputStream;
import java.io.IOException;


public class writing {
    static final String data = "2451813|65495|3617|67006|591617|3428|24839|10|161|1|79|11.41|18.71|2.80|99.54|221.20|901.39|1478.09|6.08|99.54|121.66|127.74|-779.73";

    public static void main (String[] args) {

        int totalcount = 1000000;

        try {
            FileOutputStream os = new FileOutputStream("data.pb", false);
            Simple.StrData.Builder b = Simple.StrData.newBuilder();

            long startTime = System.nanoTime();
            doPB(os, totalcount, b);
            long endTime = System.nanoTime();

            long duration = (endTime - startTime);
            System.out.println("pb\t:" + (endTime - startTime)  + " ns");

            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            FileOutputStream os = new FileOutputStream("data.pb2", false);
            Simple.Row.Builder b = Simple.Row.newBuilder();

            long startTime = System.nanoTime();
            doRawData(os, totalcount, b);
            long endTime = System.nanoTime();

            long duration = (endTime - startTime);
            System.out.println("pb2\t:" + (endTime - startTime)  + " ns");

            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream os = new FileOutputStream("data.txt", false);

            long startTime = System.nanoTime();
            doWrite(os, totalcount);
            long endTime = System.nanoTime();

            long duration = (endTime - startTime);
            System.out.println("writing\t:" + (endTime - startTime) + " ns");

            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void doPB (FileOutputStream os, int count, Simple.StrData.Builder b) {


        for(int i = 0; i < count; i++) {
            try {
                b.setData(data).build().writeTo(os);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void doWrite(FileOutputStream os, int count) {

        String str = data + "\n";
        for(int i = 0; i < count; i++) {
            try {
                os.write(str.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void doRawData (FileOutputStream os, int count, Simple.Row.Builder b) {
        b.addColumns(Simple.DBValue.newBuilder().setInt32Value(2451813).build());
        b.addColumns(Simple.DBValue.newBuilder().setInt32Value(2451813).build());
        b.addColumns(Simple.DBValue.newBuilder().setInt32Value(2451813).build());
        b.addColumns(Simple.DBValue.newBuilder().setInt32Value(2451813).build());
        b.addColumns(Simple.DBValue.newBuilder().setInt32Value(2451813).build());
        b.addColumns(Simple.DBValue.newBuilder().setInt32Value(2451813).build());
        b.addColumns(Simple.DBValue.newBuilder().setInt32Value(2451813).build());
        b.addColumns(Simple.DBValue.newBuilder().setInt32Value(2451813).build());
        b.addColumns(Simple.DBValue.newBuilder().setInt32Value(2451813).build());
        b.addColumns(Simple.DBValue.newBuilder().setInt32Value(2451813).build());
        b.addColumns(Simple.DBValue.newBuilder().setInt32Value(2451813).build());
        b.addColumns(Simple.DBValue.newBuilder().setInt32Value(2451813).build());
        b.addColumns(Simple.DBValue.newBuilder().setInt32Value(2451813).build());
        b.addColumns(Simple.DBValue.newBuilder().setInt32Value(2451813).build());
        b.addColumns(Simple.DBValue.newBuilder().setInt32Value(2451813).build());
        b.addColumns(Simple.DBValue.newBuilder().setInt32Value(2451813).build());

        for(int i = 0; i < count; i++) {
            try {

                b.build().writeTo(os);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package test.gpss;

import com.google.protobuf.InvalidProtocolBufferException;
import test.gpss.greenplum.Simple;

import java.io.*;

import org.json.simple.JSONObject;
import com.google.protobuf.util.JsonFormat;
import java.util.ArrayList;
import java.util.List;

//2451813|65495|3617|67006|591617|3428|24839|10|161|1|79|11.41|18.71|2.80|99.54|221.20|901.39|1478.09|6.08|99.54|121.66|127.74|-779.73|

public class format {
    public static void main2(String[] args)
    {

        int totalcount = 100000;

        // write directly text only
        try {
            FileOutputStream os = new FileOutputStream("data_0.txt", false);

            long startTime = System.nanoTime();
            for(int i = 0; i < totalcount; i++) {
                doWriteOnly(os);
            }
            long endTime = System.nanoTime();

            long duration = (endTime - startTime);
            System.out.println("writing:" + (endTime - startTime) / totalcount + " ns");

            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // protobuf
        try {
            FileOutputStream os = new FileOutputStream("data.pb", false);

            long startTime = System.nanoTime();
            for(int i = 0; i < totalcount; i++) {
                doPB(os);
            }
            long endTime = System.nanoTime();

            long duration = (endTime - startTime);
            System.out.println("pb:" + (endTime - startTime) / totalcount + " ns");

            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // text format
        try {
            FileOutputStream os = new FileOutputStream("data.txt", false);

            long startTime = System.nanoTime();
            for(int i = 0; i < totalcount; i++) {
                doTXT(os);
            }
            long endTime = System.nanoTime();

            long duration = (endTime - startTime);
            System.out.println("text:" + (endTime - startTime) / totalcount + " ns");

            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // json
        try {
            FileOutputStream os = new FileOutputStream("data.json", false);

            long startTime = System.nanoTime();
            for(int i = 0; i < totalcount; i++) {
                doJSON(os);
            }
            long endTime = System.nanoTime();

            long duration = (endTime - startTime);
            System.out.println("json:" + (endTime - startTime) / totalcount + " ns");

            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // write json with protobuf
        try {
            FileOutputStream os = new FileOutputStream("data2.json", false);

            long startTime = System.nanoTime();
            for(int i = 0; i < totalcount; i++) {
                doJSON2(os);
            }
            long endTime = System.nanoTime();

            long duration = (endTime - startTime);
            System.out.println("json2:" + (endTime - startTime) / totalcount + " ns");

            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private static void doPB (FileOutputStream os) {
        Simple.store_sales.Builder builder = Simple.store_sales.newBuilder();
        builder.setSsSoldDateSk(2451813).setSsSoldTimeSk(65495)
                .setSsItemSk(3617).setSsCustomerSk(67006).setSsCdemoSk(591617)
                .setSsHdemoSk(3428).setSsAddrSk(24839).setSsStoreSk(10)
                .setSsPromoSk(161).setSsTicketNumber(1).setSsQuantity(79)
                .setSsWholesaleCost((float)11.41).setSsListPrice((float)18.71)
                .setSsSalesPrice((float)2.80).setSsExtDiscountAmt((float)99.54)
                .setSsExtSalesPrice((float)221.20).setSsExtWholesaleCost((float)901.39)
                .setSsExtListPrice((float)1478.09).setSsExtTax((float)6.08)
                .setSsCouponAmt((float)99.54).setSsNetPaid((float)121.66)
                .setSsNetPaidIncTax((float)127.74).setSsNetProfit((float)-779.73);
        Simple.store_sales message = builder.build();


        try {
            message.writeTo(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doJSON2 (FileOutputStream os) {
        Simple.store_sales.Builder builder = Simple.store_sales.newBuilder();
        builder.setSsSoldDateSk(2451813).setSsSoldTimeSk(65495)
                .setSsItemSk(3617).setSsCustomerSk(67006).setSsCdemoSk(591617)
                .setSsHdemoSk(3428).setSsAddrSk(24839).setSsStoreSk(10)
                .setSsPromoSk(161).setSsTicketNumber(1).setSsQuantity(79)
                .setSsWholesaleCost((float) 11.41).setSsListPrice((float) 18.71)
                .setSsSalesPrice((float) 2.80).setSsExtDiscountAmt((float) 99.54)
                .setSsExtSalesPrice((float) 221.20).setSsExtWholesaleCost((float) 901.39)
                .setSsExtListPrice((float) 1478.09).setSsExtTax((float) 6.08)
                .setSsCouponAmt((float) 99.54).setSsNetPaid((float) 121.66)
                .setSsNetPaidIncTax((float) 127.74).setSsNetProfit((float) -779.73);
        Simple.store_sales message = builder.build();

        try {
            String str = JsonFormat.printer().print(builder) + "\n";
            os.write(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void doTXT(FileOutputStream os) {
        String delimiter = "|";
        String eol = "\n";

        List<String> l = new ArrayList<String>();
        l.add("2451813");
        l.add("65495");
        l.add("3617");
        l.add("67006");
        l.add("591617");
        l.add("3428");
        l.add("24839");
        l.add("10");
        l.add("161");
        l.add("1");
        l.add("79");
        l.add("11.41");
        l.add("18.71");
        l.add("2.80");
        l.add("99.54");
        l.add("221.20");
        l.add("901.39");
        l.add("1478.09");
        l.add("6.08");
        l.add("99.54");
        l.add("121.66");
        l.add("127.74");
        l.add("-779.73");

        String str = String.join(delimiter, l) + eol;
        try {
            os.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doJSON(FileOutputStream os) {
        JSONObject obj = new JSONObject();

        obj.put("ss_sold_date_sk",2451813 );
        obj.put("ss_sold_time_sk",65495 );
        obj.put("ss_item_sk", 3617);
        obj.put("ss_customer_sk", 67006);
        obj.put("ss_cdemo_sk", 591617);
        obj.put("ss_hdemo_sk", 3428);
        obj.put("ss_addr_sk", 24839);
        obj.put("ss_store_sk", 10);
        obj.put("ss_promo_sk", 161);
        obj.put("ss_ticket_number",1 );
        obj.put("ss_quantity", 79);
        obj.put("ss_wholesale_cost", 11.41);
        obj.put("ss_list_price", 18.71);
        obj.put("ss_sales_price", 2.80);
        obj.put("ss_ext_discount_amt", 99.54);
        obj.put("ss_ext_sales_price", 221.20);
        obj.put("ss_ext_wholesale_cost", 901.39);
        obj.put("ss_ext_list_price", 1478.09);
        obj.put("ss_ext_tax", 6.08);
        obj.put("ss_coupon_amt", 99.54);
        obj.put("ss_net_paid", 121.66);
        obj.put("ss_net_paid_inc_tax", 127.74);
        obj.put("ss_net_profit", -779.73);

        String str = obj.toString() + "\n";
        try {
            os.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doCSV(FileOutputStream os) {

        String str = "\n";
        try {
            os.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doWriteOnly(FileOutputStream os) {

        String str = "2451813|65495|3617|67006|591617|3428|24839|10|161|1|79|11.41|18.71|2.80|99.54|221.20|901.39|1478.09|6.08|99.54|121.66|127.74|-779.73\n";
        try {
            os.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

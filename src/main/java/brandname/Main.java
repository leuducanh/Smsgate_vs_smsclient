package brandname;

import java.util.Locale;

public class Main {
//    public static void main(String[] args) {
//
//        String content = "id:2409948719 submit date:2101141227 done date:2101141233 stat:DELIVRD";
//
//        if (content == null) {
//            return;
//        }
//        int s = content.indexOf("id:") + 3;
//        if (s == 2) {
//            return;
//        }
//        int e = content.indexOf(' ', s);
//        if ((e == content.length() - 1) || (e <= s)) {
//            return;
//        }
//        String smsgId = content.substring(s, e);
////        logger.debug("=========> Got delivery report. Process it....Step 2. smsgId = " + smsgId);
//        try {
//            //QUYNV-TODO: Bo rao doan nay di.
//            if (smsgId.startsWith("Smsc")) {
//                smsgId = smsgId.substring(4, smsgId.length());
//            }
//            //END QUYNV-TODO: Bo rao doan nay di.
//            long smppMsgId = Long.parseLong(smsgId, 10);
//            s = content.toLowerCase(Locale.getDefault()).indexOf("stat:") + 5;
//            e = content.toLowerCase(Locale.getDefault()).indexOf("err:");
//            if (e == -1) {
//                e = content.length();
//            }
//            String dlv_status = content.substring(s, e).trim();
//            //QuyNV Delivery status chi co 2 trang thang la 0: Sent succ, 1 la sent failure
//            int dlvstatus = ("DELIVRD".equals(dlv_status)) || ("0".equals(dlv_status)) ? 0 : 1;
//            System.out.println("dlvstatus = " + dlvstatus);
////
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}

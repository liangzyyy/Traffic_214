package com.mad.trafficclient.util;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

/**
 * Created by liangzy on 2019/2/7.
 */

public class QRCodeUtil {

    public static Bitmap createQRCodeBitmap(String content,int width,int height){
       if (TextUtils.isEmpty(content)){
           return null;
       }
       if (width<0||height<0){
           return null;
       }
       Hashtable<EncodeHintType,String> hints = new Hashtable<>();
       hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
       hints.put(EncodeHintType.MARGIN,"1");
       hints.put(EncodeHintType.ERROR_CORRECTION,"H");
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(content,BarcodeFormat.QR_CODE,width,height,hints);
            int[] pixels = new int[width*height];
            for (int y = 0;y<height;y++){
                for (int x = 0;x<width;x++){
                    if (bitMatrix.get(x,y)){
                        pixels[y*width+x]=Color.BLACK;
                    }else{
                        pixels[y*width+x]=Color.WHITE;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels,0,width,0,0,width,height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}

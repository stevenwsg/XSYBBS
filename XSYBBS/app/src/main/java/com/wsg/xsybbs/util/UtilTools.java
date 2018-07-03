package com.wsg.xsybbs.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.bean.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by wsg
 * on         2018/7/2.
 * function: 全局工具类
 */
public class UtilTools {

    //设置字体
    public static void setFont(Context mContext, TextView textview) {
        Typeface fontType = Typeface.createFromAsset(mContext.getAssets(), "fonts/FONT.TTF");
        textview.setTypeface(fontType);
    }

    //获取版本号
    public static String getVersion(Context mContext){
        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(mContext.getPackageName(),0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return mContext.getString(R.string.text_unknown);
        }
    }


    //保存图片到shareutils
    public static void putImageToShare(final Context mContext, ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        //第一步：将Bitmap压缩成字节数组输出流
        ByteArrayOutputStream byStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byStream);
        //第二步：利用Base64将我们的字节数组输出流转换成String
        byte[] byteArray = byStream.toByteArray();
        String imgString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        //第三步：将String保存shareUtils


        L.d(imgString);

        //设置具体的值
        User userInfo = BmobUser.getCurrentUser(User.class);
        userInfo.setImage(imgString);
        //更新数据
        BmobUser bmobUser = BmobUser.getCurrentUser();
        userInfo.update(bmobUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    //修改成功
                    Toast.makeText(mContext, R.string.text_editor_success, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, R.string.text_editor_failure+e.toString()+e.getErrorCode(), Toast.LENGTH_SHORT).show();
                    L.i(R.string.text_editor_failure+e.toString()+e.getErrorCode());
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    L.i(e.getMessage());
                }
            }
        });





        SPUtils.putString(mContext, "image_title", imgString);
        SPUtils.putBoolean(mContext,"image_modify",true);





    }

    //读取图片
    public static void getImageToShare(Context mContext, ImageView imageView) {
        //1.拿到string
        String imgString =SPUtils.getString(mContext, "image_title", "");
        if (!imgString.equals("")) {
            //2.利用Base64将我们string转换
            byte[] byteArray = Base64.decode(imgString, Base64.DEFAULT);
            ByteArrayInputStream byStream = new ByteArrayInputStream(byteArray);
            //3.生成bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(byStream);
            imageView.setImageBitmap(bitmap);




        }
    }




    //保存图片到服务器

    public static String putImage(final Context mContext, String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        //第一步：将Bitmap压缩成字节数组输出流
        ByteArrayOutputStream byStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byStream);
        //第二步：利用Base64将我们的字节数组输出流转换成String
        byte[] byteArray = byStream.toByteArray();
        String imgString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        //第三步：将String保存shareUtils

        //保存到服务器

        return imgString;



    }




    //读取图片
    public static void getImage(Context mContext, ImageView imageView,String s) {
        //1.拿到string
        String imgString = s;
        if (!imgString.equals("")) {
            //2.利用Base64将我们string转换
            byte[] byteArray = Base64.decode(imgString, Base64.DEFAULT);
            ByteArrayInputStream byStream = new ByteArrayInputStream(byteArray);
            //3.生成bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(byStream);
            imageView.setImageBitmap(bitmap);



        }
    }





}

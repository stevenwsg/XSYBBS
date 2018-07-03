package com.wsg.xsybbs.activity.usercenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.User;
import com.wsg.xsybbs.util.L;
import com.wsg.xsybbs.util.UtilTools;
import com.wsg.xsybbs.view.CustomDialog;

import java.io.File;

import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by wsg
 * on         2018/7/2.
 * function: 修改个人信息
 */
public class ModifyPersionalInformationActivity extends BaseActivity implements View.OnClickListener {

    //圆形头像
    private CircleImageView profile_image;


    //四个个人输入信息框
    private EditText et_username;
    private EditText et_sex;
    private EditText et_age;
    private EditText et_desc;


    //四个图片来源
    private Button btn_choose;
    private Button btn_camera;
    private Button btn_picture;
    private Button btn_cancel;



    private CustomDialog dialog;




    //更新按钮
    private Button btn_update_ok;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_persional_information);

        initView();

    }


    //初始化控件
    private void initView() {

        //按照布局依次进行初始化
        profile_image=(CircleImageView) findViewById(R.id.modify_profile_image);
        profile_image.setOnClickListener(this);

        et_username=(EditText) findViewById(R.id.et_username);
        et_sex=(EditText) findViewById(R.id.et_sex);
        et_age=(EditText) findViewById(R.id.et_age);
        et_desc=(EditText) findViewById(R.id.et_desc);


        btn_update_ok=(Button)findViewById(R.id.btn_update_ok);
        btn_update_ok.setOnClickListener(this);


        //初始化dialog
        dialog = new CustomDialog(this, 0, 0,
                R.layout.dialog_photo, R.style.pop_anim_style, Gravity.BOTTOM, 0);
        //提示框以外点击无效
        dialog.setCancelable(false);
        btn_choose = (Button)dialog.findViewById(R.id.btn_choose);
        btn_choose.setOnClickListener(this);
        btn_camera = (Button) dialog.findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(this);
        btn_picture = (Button) dialog.findViewById(R.id.btn_picture);
        btn_picture.setOnClickListener(this);
        btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);


        User user = BmobUser.getCurrentUser(User.class);
        et_username.setText(user.getUsername());
        et_age.setText(user.getAge() + "");
        et_sex.setText(user.isSex() ? getString(R.string.text_boy) : getString(R.string.text_girl_f));
        et_desc.setText(user.getDesc());



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.modify_profile_image:
                dialog.show();
                break;
            case R.id.btn_update_ok:

                break;



            case R.id.btn_choose:
                startActivity(new Intent(this, ChooseProfileActivity.class));
                break;
            case R.id.btn_camera:
                toCamera();
                break;
            case R.id.btn_picture:
                toPicture();
                break;
            case R.id.btn_cancel:
                dialog.dismiss();
                break;
        }

    }

    public static final String PHOTO_IMAGE_FILE_NAME = "fileImg.jpg";
    public static final int CAMERA_REQUEST_CODE = 100;
    public static final int IMAGE_REQUEST_CODE = 101;
    public static final int RESULT_REQUEST_CODE = 102;
    private File tempFile = null;

    //跳转相机
    private void toCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断内存卡是否可用，可用的话就进行储存
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME)));
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
        dialog.dismiss();
    }

    //跳转相册
    private void toPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
        dialog.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != this.RESULT_CANCELED) {
            switch (requestCode) {
                //相册数据
                case IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                //相机数据
                case CAMERA_REQUEST_CODE:
                    tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME);
                    startPhotoZoom(Uri.fromFile(tempFile));
                    break;
                case RESULT_REQUEST_CODE:
                    //有可能点击舍弃
                    if (data != null) {
                        //拿到图片设置
                        setImageToView(data);
                        //既然已经设置了图片，我们原先的就应该删除
                        if (tempFile != null) {
                            tempFile.delete();
                        }
                    }
                    break;
            }
        }
    }

    //裁剪
    private void startPhotoZoom(Uri uri) {
        if (uri == null) {
            L.e("uri == null");
            return;
        }




        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //设置裁剪
        intent.putExtra("crop", "true");
        //裁剪宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //裁剪图片的质量
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        //发送数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    //设置图片
    private void setImageToView(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            Bitmap bitmap = bundle.getParcelable("data");
            profile_image.setImageBitmap(bitmap);
            //保存
            UtilTools.putImageToShare(this, profile_image);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }



}


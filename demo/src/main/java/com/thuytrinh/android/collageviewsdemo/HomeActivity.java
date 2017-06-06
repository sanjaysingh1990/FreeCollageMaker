package com.thuytrinh.android.collageviewsdemo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.bumptech.glide.Glide;
import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorChangedListener;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.github.clans.fab.FloatingActionMenu;
import com.github.siyamed.shapeimageview.DiamondImageView;
import com.github.siyamed.shapeimageview.HeartImageView;
import com.github.siyamed.shapeimageview.OctogonImageView;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.thuytrinh.android.collageviews.MultiTouchListener;

import com.github.clans.fab.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HomeActivity extends Activity implements View.OnClickListener {
    private FrameLayout mContainer;
    private int currentBackgroundColor = 0xffffffff;
    private FloatingActionMenu menuLabelsRight;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContainer = (FrameLayout) findViewById(R.id.container);

        menuLabelsRight = (FloatingActionMenu) findViewById(R.id.menu_labels_right);
        fab1 = (FloatingActionButton) findViewById(R.id.background);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_addimage);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);


/*
        findViewById(R.id.collageView1).setOnTouchListener(new MultiTouchListener());
        findViewById(R.id.collageView2).setOnTouchListener(new MultiTouchListener());
        findViewById(R.id.collageView3).setOnTouchListener(new MultiTouchListener());
        findViewById(R.id.collageView4).setOnTouchListener(new MultiTouchListener());*/

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ( resultCode == RESULT_OK && data != null) {
            ArrayList<Image> images = data.getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            for (int i = 0, l = images.size(); i < l; i++) {
                File file = new File(images.get(i).path);
                Uri imageUri = Uri.fromFile(file);
                Log.e("rc",requestCode+"");
                switch (requestCode) {
                    case Cons.REQEUST_CODE_BUBBLE:
                        break;
                    case Cons.REQEUST_CODE_CIRCLE:
                        break;
                    case Cons.REQEUST_CODE_DIAMOND:
                        DiamondImageView diamondImageView = new DiamondImageView(this);
                        diamondImageView.setBorderWidth(20);
                        diamondImageView.setBorderColor(getResources().getColor(R.color.multiple_image_select_primary));

                        diamondImageView.setLayoutParams(
                                new ViewGroup.LayoutParams(
                                        // or ViewGroup.LayoutParams.WRAP_CONTENT
                                        ViewGroup.LayoutParams.WRAP_CONTENT,
                                        // or ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                        diamondImageView.setOnTouchListener(new MultiTouchListener());
                        try {
                            Bitmap mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                            diamondImageView.setImageBitmap(mBitmap);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mContainer.addView(diamondImageView);
                        break;
                    case Cons.REQEUST_CODE_HEART:
                       /* HeartImageView heartImageView = new HeartImageView(this);
                        heartImageView.setBorderWidth(20);
                        heartImageView.setBorderColor(getResources().getColor(R.color.multiple_image_select_primary));*/
                       final View view = inflater.inflate(R.layout.image_heart, null);
                        final HeartImageView heartImageView= (HeartImageView) view.findViewById(R.id.imageview_heart);
                        ImageView mImageDone= (ImageView) view.findViewById(R.id.imageview_done);
                        mImageDone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                heartImageView.setBorderWidth(100);
                                heartImageView.setBorderColor(Color.parseColor("#abc123"));


                            }
                        });
                        SeekBar seekBar= (SeekBar) view.findViewById(R.id.seekBar);
                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, final int width, boolean b) {


                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {
                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }
                        });

                        view.setLayoutParams(
                                new ViewGroup.LayoutParams(
                                        // or ViewGroup.LayoutParams.WRAP_CONTENT
                                        ViewGroup.LayoutParams.WRAP_CONTENT,
                                        // or ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                        view.setOnTouchListener(new MultiTouchListener());
                        try {
                            Bitmap mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                            heartImageView.setImageBitmap(mBitmap);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mContainer.addView(view);

                        break;
                    case Cons.REQEUST_CODE_HEXAGON:
                    break;
                    case Cons.REQEUST_CODE_OCTAGON:
                    break;
                    case Cons.REQEUST_CODE_PENTAGON:
                    break;
                    case Cons.REQEUST_CODE_POSTERSHAPE:
                    break;
                    case Cons.REQEUST_CODE_ROUNDED:
                    break;
                    case Cons.REQEUST_CODE_STAR:
                    break;
                }
            }

        }
    }

    private void showColorPicker() {

        final Context context = HomeActivity.this;

        ColorPickerDialogBuilder
                .with(context)
                .setTitle("choose color")
                .initialColor(currentBackgroundColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorChangedListener(new OnColorChangedListener() {
                    @Override
                    public void onColorChanged(int selectedColor) {
                        // Handle on color change
                        Log.d("ColorPicker", "onColorChanged: 0x" + Integer.toHexString(selectedColor));
                    }
                })
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        //  toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        mContainer.setBackgroundColor(selectedColor);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .showColorEdit(true)
                .setColorEditTextColor(ContextCompat.getColor(HomeActivity.this, android.R.color.holo_blue_bright))
                .build()
                .show();
    }

    @Override
    public void onClick(View view) {
        menuLabelsRight.close(true);

        switch (view.getId()) {
            case R.id.background:
                showColorPicker();
                break;
            case R.id.fab_addimage:

                buildDialog(R.style.DialogAnimation_2);

                break;
        }
    }

    private void buildDialog(int animationSource) {

        mDialog = new Dialog(this, android.R.style.Theme_NoTitleBar_Fullscreen);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setTitle("Set reminder");
        mDialog.setContentView(R.layout.layout_shape);
        mDialog.getWindow().getAttributes().windowAnimations = animationSource;
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.holo_green_light);

        mDialog.show();

        //set listener
        mDialog.findViewById(R.id.imageview_circle).setOnClickListener(new MyEvent());
        mDialog.findViewById(R.id.imageview_diamond).setOnClickListener(new MyEvent());
        mDialog.findViewById(R.id.imageview_heart).setOnClickListener(new MyEvent());
        mDialog.findViewById(R.id.imageview_hexagon).setOnClickListener(new MyEvent());
        mDialog.findViewById(R.id.imageview_octagon).setOnClickListener(new MyEvent());
        mDialog.findViewById(R.id.imageview_pentagon).setOnClickListener(new MyEvent());
        mDialog.findViewById(R.id.imageview_portershape).setOnClickListener(new MyEvent());
        mDialog.findViewById(R.id.imageview_rounded).setOnClickListener(new MyEvent());
        mDialog.findViewById(R.id.imageview_star).setOnClickListener(new MyEvent());
        mDialog.findViewById(R.id.imageview_bubble).setOnClickListener(new MyEvent());


    }

    class MyEvent implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageview_circle:
                    choseImage(Cons.REQEUST_CODE_CIRCLE);
                    break;
                case R.id.imageview_diamond:
                    choseImage(Cons.REQEUST_CODE_DIAMOND);
                    break;
                case R.id.imageview_heart:
                    choseImage(Cons.REQEUST_CODE_HEART);
                    break;
                case R.id.imageview_hexagon:
                    choseImage(Cons.REQEUST_CODE_HEXAGON);
                    break;
                case R.id.imageview_octagon:
                    choseImage(Cons.REQEUST_CODE_OCTAGON);
                    break;
                case R.id.imageview_pentagon:
                    choseImage(Cons.REQEUST_CODE_PENTAGON);
                    break;
                case R.id.imageview_portershape:
                    choseImage(Cons.REQEUST_CODE_POSTERSHAPE);
                    break;
                case R.id.imageview_rounded:
                    choseImage(Cons.REQEUST_CODE_ROUNDED);
                    break;
                case R.id.imageview_star:
                    choseImage(Cons.REQEUST_CODE_STAR);
                    break;
                case R.id.imageview_bubble:
                    choseImage(Cons.REQEUST_CODE_BUBBLE);
                    break;

            }
        }
    }

    private void choseImage(int requesCode) {
        Intent intent = new Intent(HomeActivity.this, AlbumSelectActivity.class);
        intent.putExtra(Constants.INTENT_EXTRA_LIMIT, 1);
        startActivityForResult(intent, requesCode);
        if (mDialog != null) {
            mDialog.dismiss();
        }

    }
}
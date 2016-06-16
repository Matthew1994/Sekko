package com.example.guanlu.sekko.viewConctroller;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guanlu.sekko.R;

/**
 * Created by guanlu on 16/5/9.
 */

public class OrderDialog extends Dialog {

    private Context context;
    private String title;
    private ClickListenerInterface clickListenerInterface;
    private TextView movieName;
    private TextView cinameName;
    private TextView startTime;
    private TextView num;
    private TextView price;
    private ImageView img;

    private Button commit;
    private Button cancel;
    private View view;


    public interface ClickListenerInterface {

        public void doCommit();

        public void doCancel();

    }

    public OrderDialog(Context context) {
        super(context);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    @Override
    public void show() {
        setContentView(view);
        super.show();
    }
    public void init() {

        LayoutInflater inflater = LayoutInflater.from(context);

        view = inflater.inflate(R.layout.order_dialog, null);


        movieName = (TextView)view.findViewById(R.id.order_dialog_movie_name);
        cinameName = (TextView)view.findViewById(R.id.order_dialog_cinema_name);
        startTime = (TextView)view.findViewById(R.id.order_dialog_start_time);
        num = (TextView)view.findViewById(R.id.order_dialog_num);
        price = (TextView)view.findViewById(R.id.order_dialog_price);
        img = (ImageView)view.findViewById(R.id.order_dialog_movie_img);


        cancel = (Button) view.findViewById(R.id.order_dialog_cancel);
        commit = (Button) view.findViewById(R.id.order_dialog_commit);


        cancel.setOnClickListener(new clickListener());
        commit.setOnClickListener(new clickListener());

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        lp.width = (int) (d.widthPixels * 1.0);
        dialogWindow.setAttributes(lp);


    }

    public void setClickListener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.order_dialog_commit:
                    clickListenerInterface.doCommit();
                    break;
                case R.id.order_dialog_cancel:
                    clickListenerInterface.doCancel();
                    break;

            }
        }
    }

    public String getMovieName() {
        return movieName.getText().toString();
    }

    public void setMovieName(String movieName) {
        this.movieName.setText(movieName);
    }

    public String getCinameName() {
        return cinameName.getText().toString();
    }

    public void setCinameName(String cinameName) {
        this.cinameName.setText(cinameName);
    }

    public String getStartTime() {
        return startTime.getText().toString();
    }

    public void setStartTime(String startTime) {
        this.startTime.setText(startTime);
    }

    public String getNum() {
        return num.getText().toString();
    }

    public void setNum(String num) {
        this.num.setText(num);
    }

    public String getPrice() {
        return price.getText().toString();
    }

    public void setPrice(String price) {
        if(price!=null)
        this.price.setText(price);
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img.setImageBitmap(img);
    }
}


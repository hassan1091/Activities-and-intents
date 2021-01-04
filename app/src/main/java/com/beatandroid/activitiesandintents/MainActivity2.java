package com.beatandroid.activitiesandintents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity2 extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private ConstraintLayout mConstraintLayout;
    private ImageView mBoyGirlImageView;
    private TextView mBoyGirlTextView, mMessageTextView;
    private ImageButton shareImageButton , sendImageButton;
    private RadioGroup mRadioGroup;

    private int color = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //
        getViews();
        //
        displayData();
        /*
        *
        *
        *
        *
        * */
        mRadioGroup.setOnCheckedChangeListener(this);
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    private void getViews() {
        mConstraintLayout = findViewById(R.id.constraint_layout2);
        mBoyGirlImageView = findViewById(R.id.boy_girl_image_view2);
        mBoyGirlTextView = findViewById(R.id.boy_girl_text_view);
        mMessageTextView = findViewById(R.id.message_text_view);
        mRadioGroup = findViewById(R.id.background_radio_group);
        shareImageButton = findViewById(R.id.share_image_button_view);
        sendImageButton = findViewById(R.id.send_image_button_view);
    }

    private void displayData() {
        Intent intent = getIntent();
        if (intent.getBooleanExtra(ExtraKeys.GENDER, true)) {
            mBoyGirlImageView.setImageResource(R.drawable.boy);
            mBoyGirlTextView.setText(R.string.boy);
        } else {
            mBoyGirlImageView.setImageResource(R.drawable.girl);
            mBoyGirlTextView.setText(R.string.girl);
        }
        mMessageTextView.setText(intent.getStringExtra(ExtraKeys.MESSAGE));
    }

    public void reply(View view) {
        Intent intent = new Intent();
        intent.putExtra(ExtraKeys.COLOR, color);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.red_radio_button:
                color = R.color.light_light_red;
                changeColor(color);
                break;
            case R.id.blue_radio_button:
                color = R.color.light_light_blue;
                changeColor(color);
                break;
            case R.id.green_radio_button:
                color = R.color.light_light_green;
                changeColor(color);
                break;
        }
    }
    private void changeColor(int color){
        mConstraintLayout.setBackgroundResource(color);
        shareImageButton.setBackgroundResource(color);
        sendImageButton.setBackgroundResource(color);
    }

    public void shareMessage(View view) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, mMessageTextView.getText());
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
    }
}
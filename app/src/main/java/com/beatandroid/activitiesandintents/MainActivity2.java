package com.beatandroid.activitiesandintents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity2 extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private ConstraintLayout mConstraintLayout;
    private ImageView mBoyGirlImageView;
    private TextView mBoyGirlTextView, mMessageTextView;
    private ImageButton shareImageButton;
    private RadioGroup mRadioGroup;

    private int colorPlace = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //
        getViews();
        //
        displayData();
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    private void getViews() {
        mConstraintLayout = findViewById(R.id.constraint_layout2);
        mBoyGirlImageView = findViewById(R.id.boy_girl_image_view2);
        mBoyGirlTextView = findViewById(R.id.boy_girl_text_view);
        mMessageTextView = findViewById(R.id.message_text_view);
        mRadioGroup = findViewById(R.id.background_radio_group);
        shareImageButton = findViewById(R.id.share_image_button_view);
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
        intent.putExtra(ExtraKeys.COLOR, colorPlace);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.red_radio_button:
                colorPlace = R.color.light_light_red;
                mConstraintLayout.setBackgroundResource(colorPlace);
                shareImageButton.setBackgroundResource(colorPlace);
                break;
            case R.id.blue_radio_button:
                colorPlace = R.color.light_light_blue;
                mConstraintLayout.setBackgroundResource(colorPlace);
                shareImageButton.setBackgroundResource(colorPlace);
                break;
            case R.id.green_radio_button:
                colorPlace = R.color.light_light_green;
                mConstraintLayout.setBackgroundResource(colorPlace);
                shareImageButton.setBackgroundResource(colorPlace);
                break;
        }
    }

    public void shareMessage(View view) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, mBoyGirlTextView.getText());
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
    }
}
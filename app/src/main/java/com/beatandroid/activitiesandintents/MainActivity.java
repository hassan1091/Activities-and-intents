package com.beatandroid.activitiesandintents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private ConstraintLayout mConstraintLayout;
    private EditText mMessageEditText;
    private ImageView mBoyGirlImageView;
    private RadioGroup mGenderRadioGroup;

    private boolean isBoy = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        getViews();
        //
        mGenderRadioGroup.setOnCheckedChangeListener(this);
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
        mConstraintLayout = findViewById(R.id.constraint_layout);
        mMessageEditText = findViewById(R.id.message_edit_text);
        mBoyGirlImageView = findViewById(R.id.boy_girl_image_view);
        mGenderRadioGroup = findViewById(R.id.gender_radio_group);
    }

    public void send(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra(ExtraKeys.MESSAGE, mMessageEditText.getText().toString());
        intent.putExtra(ExtraKeys.GENDER, isBoy);
        startActivityForResult(intent, ExtraKeys.MAIN_ACTIVITY_2);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.boy_radio_button:
                mBoyGirlImageView.setImageResource(R.drawable.boy);
                isBoy = true;
                break;
            case R.id.girl_radio_button:
                mBoyGirlImageView.setImageResource(R.drawable.girl);
                isBoy = false;
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ExtraKeys.MAIN_ACTIVITY_2) {
            if (resultCode == RESULT_OK) {
                if (data == null) return;
                int colorPlace = data.getIntExtra(ExtraKeys.COLOR, 0);
                mConstraintLayout.setBackgroundResource(colorPlace);
            }
        }
    }
}
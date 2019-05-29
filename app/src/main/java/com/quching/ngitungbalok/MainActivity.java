package com.quching.ngitungbalok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String STATE_RESULT = "state_result";
    EditText edtWidth, edtHeight, edtLength;
    Button btnCalculate;
    TextView tvResult, tvWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);
        tvWords = findViewById(R.id.tv_words);

        btnCalculate.setOnClickListener(this);

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
            tvWords.setText(result);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
        outState.putString(STATE_RESULT, tvWords.getText().toString());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calculate) {
            String inputLength = edtLength.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();
            String inputHeight = edtHeight.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true;
                edtLength.setError("Must be filled");
            }

            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true;
                edtHeight.setError("Must be filled");
            }

            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true;
                edtWidth.setError("Must be filled");
            }

            Double length = toDouble(inputLength);
            Double width = toDouble(inputWidth);
            Double height = toDouble(inputHeight);

            if (length == null) {
                isInvalidDouble = true;
                edtLength.setError("This field must be valid number");
            }

            if (width == null) {
                isInvalidDouble = true;
                edtWidth.setError("This field must be valid number");
            }

            if (height == null) {
                isInvalidDouble = true;
                edtHeight.setError("This field must be valid number");
            }

            if (!isEmptyFields && !isInvalidDouble) {
                double volume = length * width * height;
                String words = "Berhasil";
                tvResult.setText(String.valueOf(volume));
                tvWords.setText(words);
            }
        }
    }

    Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

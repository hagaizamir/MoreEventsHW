package hagai.edu.moreeventshw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, TextWatcher {

    private TextView tvResult;
    private SeekBar sbRed;
    private SeekBar sbGreen;
    private SeekBar sbBlue;
    private EditText etRed, etGreen, etBlue;

    private boolean userIsCurrentlyScrolling = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
        initEvents();
    }

    private void initEvents() {
        sbRed.setOnSeekBarChangeListener(this);
        sbGreen.setOnSeekBarChangeListener(this);
        sbBlue.setOnSeekBarChangeListener(this);

        etGreen.addTextChangedListener(this);
        etBlue.addTextChangedListener(this);
        etRed.addTextChangedListener(this);
    }

    private void initLayout() {
        tvResult = (TextView) findViewById(R.id.tvResult);
        sbRed = (SeekBar) findViewById(R.id.sbRed);
        sbGreen = (SeekBar) findViewById(R.id.sbGreen);
        sbBlue = (SeekBar) findViewById(R.id.sbBlue);
        etRed = (EditText) findViewById(R.id.etRed);
        etBlue = (EditText) findViewById(R.id.etBlue);
        etGreen = (EditText) findViewById(R.id.etGreen);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar,
                                  int progress,
                                  boolean fromUser) {
        this.userIsCurrentlyScrolling = fromUser;
        int rgb = Color.rgb(
                sbRed.getProgress(),
                sbGreen.getProgress(),
                sbBlue.getProgress()
        );

        tvResult.setBackgroundColor(rgb);

        if (userIsCurrentlyScrolling){
            etGreen.setText(String.valueOf(sbGreen.getProgress()));
            etBlue.setText(String.valueOf( sbBlue.getProgress()));
            etRed.setText(String.valueOf( sbRed.getProgress()));
        }
        this.userIsCurrentlyScrolling = false;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try {
            int redValue = Integer.valueOf(etRed.getText().toString());
            int greenValue = Integer.valueOf(etGreen.getText().toString());
            int blueValue = Integer.valueOf(etBlue.getText().toString());

            if (!userIsCurrentlyScrolling){
                sbRed.setProgress(redValue);
                sbGreen.setProgress(greenValue);
                sbBlue.setProgress(blueValue);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {


    }
}
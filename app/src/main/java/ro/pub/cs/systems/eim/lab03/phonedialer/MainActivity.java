package ro.pub.cs.systems.eim.lab03.phonedialer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    EditText phone;
    ImageButton call;
    ImageButton hangup;
    ImageButton back;
    Button button;

    private CallImageButtonClickListener callImageButtonClickListener = new CallImageButtonClickListener();
    private class CallImageButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        1);
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phone.getText().toString()));
                startActivity(intent);
            }
        }
    }

    private HangupImageButtonClickListener hangupImageButtonClickListener = new HangupImageButtonClickListener();
    private class HangupImageButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            finish();
        }
    }

    private BackspaceButtonClickListener backspaceButtonClickListener = new BackspaceButtonClickListener();
    private class BackspaceButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String phoneNumber = phone.getText().toString();
            if (phoneNumber.length() > 0) {
                phoneNumber = phoneNumber.substring(0, phoneNumber.length() - 1);
                phone.setText(phoneNumber);
            }
        }
    }

    private GenericButtonClickListener genericButtonClickListener = new GenericButtonClickListener();
    private class GenericButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            phone.setText(phone.getText().toString() + ((Button)view).getText().toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        phone=(EditText)findViewById(R.id.phone_number_edit_text);
        //phone.getText();
        //phone.setText('');
        call = (ImageButton)findViewById(R.id.call_image_button);
        call.setOnClickListener(callImageButtonClickListener);
        hangup = (ImageButton)findViewById(R.id.hangup_image_button);
        hangup.setOnClickListener(hangupImageButtonClickListener);
        back = (ImageButton)findViewById(R.id.backspace_image_button);
        back.setOnClickListener(backspaceButtonClickListener);

        for (int i = 0; i < 10; i++) {
            String buttonID = "btn" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            button = (Button)findViewById(resID);
            button.setOnClickListener(genericButtonClickListener);
        }

        button = (Button)findViewById(R.id.btn_tag);
        button.setOnClickListener(genericButtonClickListener);
        button = (Button)findViewById(R.id.btn_star);
        button.setOnClickListener(genericButtonClickListener);

    }

}
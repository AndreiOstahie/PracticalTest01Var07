
package ro.pub.cs.systems.eim.practicaltest01var07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var07MainActivity extends AppCompatActivity {

    private EditText editText1, editText2, editText3, editText4;
    private Button navigateButton, serviceButton;


    private int serviceStatus = Constants.SERVICE_STOPPED;

    private IntentFilter intentFilter = new IntentFilter();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getExtras().containsKey(Constants.RAND1)) {
                int rand1 = intent.getIntExtra(Constants.RAND1, -1);
                editText1.setText(String.valueOf(rand1));
            }
            if (intent != null && intent.getExtras().containsKey(Constants.RAND2)) {
                int rand2 = intent.getIntExtra(Constants.RAND2, -1);
                editText2.setText(String.valueOf(rand2));
            }
            if (intent != null && intent.getExtras().containsKey(Constants.RAND3)) {
                int rand3 = intent.getIntExtra(Constants.RAND3, -1);
                editText3.setText(String.valueOf(rand3));
            }
            if (intent != null && intent.getExtras().containsKey(Constants.RAND4)) {
                int rand4 = intent.getIntExtra(Constants.RAND4, -1);
                editText4.setText(String.valueOf(rand4));
            }
            System.out.printf("I received something");
        }
    }

    // Button click listener
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.button_navigate:
                    if (editText1.getText().toString().matches("") ||
                            editText2.getText().toString().matches("") ||
                            editText3.getText().toString().matches("") ||
                            editText4.getText().toString().matches("")) {
                        break;
                    } else {
                        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07SecondaryActivity.class);

                        int num1 = Integer.parseInt(editText1.getText().toString());
                        int num2 = Integer.parseInt(editText2.getText().toString());
                        int num3 = Integer.parseInt(editText3.getText().toString());
                        int num4 = Integer.parseInt(editText4.getText().toString());
                        intent.putExtra(Constants.TEXT1, num1);
                        intent.putExtra(Constants.TEXT2, num2);
                        intent.putExtra(Constants.TEXT3, num3);
                        intent.putExtra(Constants.TEXT4, num4);
                        startActivity(intent);
                        break;
                    }

                case R.id.button_service:
                    if(serviceStatus == Constants.SERVICE_STOPPED) {
                        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07Service.class);
                        getApplicationContext().startService(intent);
                        serviceStatus = Constants.SERVICE_STARTED;
                    }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_main);

        editText1 = (EditText)findViewById(R.id.editText_cell_1);
        editText2 = (EditText)findViewById(R.id.editText_cell_2);
        editText3 = (EditText)findViewById(R.id.editText_cell_3);
        editText4 = (EditText)findViewById(R.id.editText_cell_4);

        navigateButton = (Button)findViewById(R.id.button_navigate);
        serviceButton = (Button)findViewById(R.id.button_service);

        navigateButton.setOnClickListener(buttonClickListener);
        serviceButton.setOnClickListener(buttonClickListener);
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.SAVE1, editText1.getText().toString());
        savedInstanceState.putString(Constants.SAVE2, editText2.getText().toString());
        savedInstanceState.putString(Constants.SAVE3, editText3.getText().toString());
        savedInstanceState.putString(Constants.SAVE4, editText4.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.SAVE1)) {
            editText1.setText(savedInstanceState.getString(Constants.SAVE1));
        }
        if (savedInstanceState.containsKey(Constants.SAVE2)) {
            editText2.setText(savedInstanceState.getString(Constants.SAVE2));
        }
        if (savedInstanceState.containsKey(Constants.SAVE3)) {
            editText3.setText(savedInstanceState.getString(Constants.SAVE3));
        }
        if (savedInstanceState.containsKey(Constants.SAVE4)) {
            editText4.setText(savedInstanceState.getString(Constants.SAVE4));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var07Service.class);
        stopService(intent);
        super.onDestroy();
    }
}
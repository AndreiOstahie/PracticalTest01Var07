package ro.pub.cs.systems.eim.practicaltest01var07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var07SecondaryActivity extends AppCompatActivity {

    private EditText editText1, editText2, editText3, editText4;
    private Button sumButton, productButton;


    // Button click listener
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int num1, num2, num3, num4;
            int sum, product;
            num1 = Integer.parseInt(editText1.getText().toString());
            num2 = Integer.parseInt(editText2.getText().toString());
            num3 = Integer.parseInt(editText3.getText().toString());
            num4 = Integer.parseInt(editText4.getText().toString());
            switch(view.getId()) {
                case R.id.button_sum:
                    sum = num1 + num2 + num3 + num4;
                    Toast toast = Toast. makeText(getApplicationContext(),String.valueOf(sum),Toast. LENGTH_SHORT);
                    toast.show();
                    break;
                case R.id.button_product:
                    product = num1 * num2 * num3 * num4;
                    Toast toast2 = Toast. makeText(getApplicationContext(),String.valueOf(product),Toast. LENGTH_SHORT);
                    toast2.show();
                    break;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_secondary);

        editText1 = (EditText)findViewById(R.id.editText_cell_1);
        editText2 = (EditText)findViewById(R.id.editText_cell_2);
        editText3 = (EditText)findViewById(R.id.editText_cell_3);
        editText4 = (EditText)findViewById(R.id.editText_cell_4);

        sumButton = (Button)findViewById(R.id.button_sum);
        productButton = (Button)findViewById(R.id.button_product);

        sumButton.setOnClickListener(buttonClickListener);
        productButton.setOnClickListener(buttonClickListener);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.TEXT1)) {
            int num1 = intent.getIntExtra(Constants.TEXT1, -1);
            editText1.setText(String.valueOf(num1));
        }
        if (intent != null && intent.getExtras().containsKey(Constants.TEXT2)) {
            int num2 = intent.getIntExtra(Constants.TEXT2, -1);
            editText2.setText(String.valueOf(num2));
        }
        if (intent != null && intent.getExtras().containsKey(Constants.TEXT3)) {
            int num3 = intent.getIntExtra(Constants.TEXT3, -1);
            editText3.setText(String.valueOf(num3));
        }
        if (intent != null && intent.getExtras().containsKey(Constants.TEXT4)) {
            int num4 = intent.getIntExtra(Constants.TEXT4, -1);
            editText4.setText(String.valueOf(num4));
        }
    }
}
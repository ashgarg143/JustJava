/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match the package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    int amount=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increament(View view) {
        quantity++;
        if(quantity>100) {
            Toast.makeText(this, " You Can not have more than 100 coffees", Toast.LENGTH_SHORT).show();
            quantity=100;
        }
        display(quantity);
        amount = quantity * 10;
        displayAmount(amount);

    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decreament(View view) {
        quantity--;
        if (quantity <= 0) {
            quantity = 1;
            Toast.makeText(this, "You Can not have less than 1 coffee", Toast.LENGTH_SHORT).show();
        }
        display(quantity);
        amount = quantity * 10;
        displayAmount(amount);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        String message = summary();
        composeEmail(message);
    }

    private String name() {
        EditText edit_text=(EditText)findViewById(R.id.name);
        String name=edit_text.getText().toString();
        return name;
    }

    public String summary() {
        int amount1 = amount;

        String message = "\nOrder Summary\n\nName: ";
        message += name();
        CheckBox box1 = (CheckBox) findViewById(R.id.whipped_cream);
        message += "\nAdd Whipped Cream? "+box1.isChecked();
        if (box1.isChecked()) {
            amount1 += quantity * 1;
        }
        CheckBox box2 = (CheckBox) findViewById(R.id.chocolate);
        message += "\nAdd Chocolate? "+box2.isChecked();
        if (box2.isChecked()) {
            amount1 += quantity * 2;
        }
        message += "\nQuantity: " + quantity;
        message += "\nTotal: $" + amount1;
        message += "\n" + "Thank You!";
        return message;
    }

    public void composeEmail(String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "shivam.vk529@gmail.com");
        intent.putExtra(Intent.EXTRA_CC, "shivam.vk529@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT,"Coffee Order");
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayAmount(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.price_text_view);
        quantityTextView.setText(NumberFormat.getCurrencyInstance(Locale.US).format(number));

    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}
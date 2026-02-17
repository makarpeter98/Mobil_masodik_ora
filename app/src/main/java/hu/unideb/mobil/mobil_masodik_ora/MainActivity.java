package hu.unideb.mobil.mobil_masodik_ora;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //teszt
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        resultTextview = findViewById(R.id.resultTextView);

    }

    public void handleButtonClick(View view) {
        Button myButton = (Button) view;
        String inputString = myButton.getText().toString();
        Log.d("button", inputString);
        switch (inputString)
        {
            case "=": resultTextview.setText(calculate(resultTextview.getText().toString())); break;
            case "CE": resultTextview.setText("0"); break;
            case "-":
            case "*":
            case "/": if("+-*/".indexOf(resultTextview.getText().toString().charAt(
                    resultTextview.getText().toString().length()-1
            )) > -1 ) break;
            default: resultTextview.append(myButton.getText());
        }
        Log.d("expression", resultTextview.getText().toString());
    }
    public String calculate(String expression)
    {
        try {
            String op1Str = expression.split("[+\\-*/]")[0];
            int op1 = Integer.parseInt(op1Str);
            int op2 = Integer.parseInt(expression.split("[+\\-*/]")[1]);
            char operator = expression.charAt(op1Str.length());

            Log.d("expression", op1 + "_" + operator + "_" + op2);
            switch (operator)
            {
                case '+': return "" + (op1 + op2);
                case '-': return "" + (op1 - op2);
                case '*': return "" + (op1 * op2);
                case '/': return "" + (op1 / op2);
                default: return "";
            }
        }
        catch (Exception e)
        {
            Log.d("exp", e.toString());
        }

        return "NaN";
    }
}
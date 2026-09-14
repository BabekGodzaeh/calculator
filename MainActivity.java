package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;

public class MainActivity extends Activity {
    private TextView display;
    private String current = "";
    private double first = 0;
    private String op = "";

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(20, 20, 20, 20);

        display = new TextView(this);
        display.setTextSize(40);
        display.setText("0");
        root.addView(display);

        String[] buttons = {"7","8","9","/","4","5","6","*","1","2","3","-","C","0","=","+"};
        for (int i = 0; i < buttons.length; i += 4) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < 4; j++) {
                Button btn = new Button(this);
                btn.setText(buttons[i + j]);
                final String val = buttons[i + j];
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) { onButton(val); }
                });
                row.addView(btn, new LinearLayout.LayoutParams(0, -2, 1));
            }
            root.addView(row);
        }
        setContentView(root);
    }

    private void onButton(String val) {
        if (val.equals("C")) { current = ""; display.setText("0"); return; }
        if (val.equals("=")) {
            double second = current.isEmpty() ? 0 : Double.parseDouble(current);
            double result = 0;
            if (op.equals("+")) result = first + second;
            else if (op.equals("-")) result = first - second;
            else if (op.equals("*")) result = first * second;
            else if (op.equals("/")) result = second != 0 ? first / second : 0;
            display.setText(String.valueOf(result));
            current = ""; op = "";
            return;
        }
        if ("+-*/".contains(val)) {
            first = current.isEmpty() ? 0 : Double.parseDouble(current);
            op = val; current = ""; return;
        }
        current += val;
        display.setText(current);
    }
}

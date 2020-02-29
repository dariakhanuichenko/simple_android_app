package com.example.myapplication2lab;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    TextView inputTextView;
    Spinner spinner;
    ArrayAdapter<String> adapter;
    TextView errortextView;
    EditText editText;
    List<TypefaceWithFontName> fonts;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        super.onStart();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<TypefaceWithFontName> getFonts() {
        List<TypefaceWithFontName> fonts = new ArrayList<TypefaceWithFontName>();
        fonts.add(new TypefaceWithFontName(getResources().getFont(R.font.murrey_c), "MurreyC"));
        fonts.add(new TypefaceWithFontName(getResources().getFont(R.font.arialblack), "Arial Black"));
        fonts.add(new TypefaceWithFontName(getResources().getFont(R.font.helvetica), "Helvetica"));
        fonts.add(new TypefaceWithFontName(Typeface.SANS_SERIF, "Sans Serif"));
        return fonts;
    }

    private List<String> getFontNames(List<TypefaceWithFontName> fonts) {
        List<String> names = new ArrayList<String>();
        for (TypefaceWithFontName font : fonts) {
            names.add(font.getFontName());
            System.out.println(" !!!! " + font.getFont().getStyle());
        }
        return names;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void init(List<TypefaceWithFontName> fonts) {
        inputTextView = (TextView) findViewById(R.id.result);

        fonts = this.getFonts();
        spinner = (Spinner) findViewById(R.id.spinner);
        errortextView = findViewById(R.id.error);
        editText = (EditText) findViewById(R.id.editText1);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getFontNames(fonts));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment);

        init(fonts);

        // адаптер
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        // заголовок
        spinner.setPrompt("Title");
        // выделяем элемент
        spinner.setSelection(2);

        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                for (TypefaceWithFontName font : getFonts()) {

                    if (spinner.getSelectedItem().toString().equals(font.getFontName())) {
                        inputTextView.setTypeface(font.getFont());
                        inputTextView.setTextSize(50);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }


    public void onClickSave(View view) {
        String str = editText.getText().toString();
        if (str.length() == 0) {
            errortextView.setText("Input string please");
        } else
            inputTextView.setText(str);
    }


    public void onClickCancel(View view) {
        final TextView errorTextView = (TextView) findViewById(R.id.error);

        EditText editText = (EditText) findViewById(R.id.editText1);
        inputTextView.setText("");
        editText.setText("");
        errorTextView.setText("");
    }

}

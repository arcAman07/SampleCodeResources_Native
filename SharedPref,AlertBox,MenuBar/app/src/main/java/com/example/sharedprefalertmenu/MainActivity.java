package com.example.sharedprefalertmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text;
    SharedPreferences sharedPreferences;
    public void setLanguage(String language) {
        sharedPreferences.edit().putString("language",language);
        text.setText(language);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_reso,menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.english:
                setLanguage("English");
                Log.i("Item selected","Settings");
                return true;

            case R.id.hindi:
                setLanguage("Hindi");
                Log.i("Item selected","Help");
                return true;

            default:
                return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        sharedPreferences = this.getSharedPreferences("package com.example.sharedprefalertmenu", Context.MODE_PRIVATE);
//        text = (TextView) findViewById(R.id.text);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences("package com.example.sharedprefalertmenu", Context.MODE_PRIVATE);
        text = (TextView) findViewById(R.id.text);

        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Choose your Language").setMessage(
                "Which Language would you prefer to communicate in?"
        ).setPositiveButton("English", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setLanguage("English");

            }
        }).setNegativeButton("Hindi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setLanguage("Hindi");

            }
        }).show();

    }
}

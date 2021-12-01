package hu.petrik.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText email, felh, jelsz, nev;
    private Button reg, vissza;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        reg.setOnClickListener(v -> {
            String e= email.getText().toString().trim();
            String f= felh.getText().toString().trim();
            String j= jelsz.getText().toString().trim();
            String n= nev.getText().toString().trim();

            if(e.isEmpty()||f.isEmpty()||j.isEmpty()||n.isEmpty()){
                Toast.makeText(getApplicationContext(), "Minden mező kitöltése kötelező",
                        Toast.LENGTH_SHORT).show();
            } else {
                if (db.regisztral(e, f, j, n)) {
                    Toast.makeText(getApplicationContext(), "A regisztráció sikeresen megtörtént!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "A regisztráció nem sikerült", Toast.LENGTH_SHORT).show();
                }
            }
        });
        vissza.setOnClickListener(v -> {
            Intent mainre = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(mainre);
            finish();
        });

    }
    public void init(){

        email = findViewById(R.id.emailReg);
        felh = findViewById(R.id.felhReg);
        jelsz = findViewById(R.id.jelszoReg);
        nev = findViewById(R.id.teljesnevReg);
        reg = findViewById(R.id.regBtn);
        vissza = findViewById(R.id.visszBtn);
        db = new DBHelper(this);
    }
}
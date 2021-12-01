package hu.petrik.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editFelh, editJelsz;
    private Button btnReg, btnBej;
    private DBHelper db;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnReg.setOnClickListener(v -> {
            Intent regisztralasra = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(regisztralasra);
            finish();
        });
        btnBej.setOnClickListener(v -> {
            String felhN= editFelh.getText().toString().trim();
            String jelsz= editJelsz.getText().toString().trim();
            String logghoz="";

            if(felhN.isEmpty()||jelsz.isEmpty()){
                Toast.makeText(getApplicationContext(), "Minden mező kitöltése kötelező",
                        Toast.LENGTH_SHORT).show();
            }else{
                Cursor ellenorzes = db.ellenorzes(felhN, jelsz);
                if(ellenorzes.getCount()==0){
                    Toast.makeText(getApplicationContext(), "Ilyen felhasználónév jelszó kombináció nem létezik",
                            Toast.LENGTH_SHORT).show();
                }else {
                    StringBuilder strB = new StringBuilder();
                    while (ellenorzes.moveToNext()) {
                        logghoz = strB.append(ellenorzes.getString(4)).toString();
                    }
                    Intent bejelentkezett = new Intent(MainActivity.this, LoggedInActivity.class);
                    bejelentkezett.putExtra("Teljesnev", logghoz);
                    startActivity(bejelentkezett);
                    finish();
                }
            }
        });
    }

    private void init(){
        editFelh = findViewById(R.id.felhasznalo);
        editJelsz = findViewById(R.id.jelszo);
        btnBej = findViewById(R.id.bejelentkezes);
        btnReg = findViewById(R.id.regisztracio);
        db = new DBHelper(this);
    }
}
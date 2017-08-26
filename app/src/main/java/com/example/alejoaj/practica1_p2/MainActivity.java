package com.example.alejoaj.practica1_p2;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText eLoggin,ePassword,eRPassword,eEmail;
    private Button date;
    private DatePickerDialog datePickerDialog;
    private String log="",pass="",Rpass="",ciudad="",sexo = "Masculino",email="",hobbie="";
    private int mYear=0,mMonth=0,mDay=0;
    private CheckBox cCine, cComida , cDeporte, cMusica;
    private Button bAceptar;
    private TextView tinfo;
    private Spinner sCiudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = (Button) findViewById(R.id.date);

        eLoggin = (EditText) findViewById(R.id.eLoggin);
        ePassword = (EditText) findViewById(R.id.ePassword);
        eRPassword = (EditText) findViewById(R.id.eRPassword);
        eEmail = (EditText) findViewById(R.id.eEmail);

        cCine = (CheckBox) findViewById(R.id.cCine);
        cComida = (CheckBox) findViewById(R.id.cComida);
        cMusica = (CheckBox) findViewById(R.id.cMusica);
        cDeporte = (CheckBox) findViewById(R.id.cDeporte);

        sCiudad = (Spinner) findViewById(R.id.sCiudad);


        tinfo = (TextView) findViewById(R.id.tinfo);


        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Ciudad_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sCiudad.setAdapter(adapter);
        /*
        lo siguiente se autocompletó con ctrol y espacio
         */
        sCiudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ciudad = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR); // current year
                mMonth = c.get(Calendar.MONTH); // current month
                mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

    }

    public void onRadioButtonClicked(View view) {
        int id = view.getId();

        if( id == R.id.rMasculino){
            sexo = "Masculino";
        }
        if(id == R.id.rFemenino){
            sexo = "Femenino";
        }
    }

    public void onCheckboxClicked(View view) {

    }

    public void Aceptar(View view) {
        if(eLoggin.getText().toString().isEmpty() || eEmail.getText().toString().isEmpty()
                || ePassword.getText().toString().isEmpty() || eRPassword.getText().toString().isEmpty()
                || (!cCine.isChecked() && !cComida.isChecked() && !cDeporte.isChecked() && !cMusica.isChecked()) ||
                (mYear == 0)){
            tinfo.setText("Esta vacio algún campo del fomulario");
        }else{
            pass = ePassword.getText().toString();
            Rpass = eRPassword.getText().toString();
            if (!pass.equals(Rpass)){
                tinfo.setText("Contraseñas diferentes, vuelva a comprobar los datos ingresados"+ pass+Rpass);
            }
            else {
                if(cCine.isChecked()){
                    hobbie = hobbie + " " + "Cine";
                }
                if(cComida.isChecked()){
                    hobbie = hobbie+ " " + "Comida";
                }
                if(cDeporte.isChecked()){
                    hobbie = hobbie+ " " + "Deporte";
                }
                if (cMusica.isChecked()){
                    hobbie = hobbie+ " " + "Musica";
                }
                log = eLoggin.getText().toString();
                email = eEmail.getText().toString();
                tinfo.setText("Los Dato ingresados fueron los siguientes:" +
                        "\nLogiin: " + log +
                        "\nPassword: " + pass +
                        "\nEmail: " + email +
                        "\nSexo: " + sexo +
                        "\nFecha de nacimiento:" +mDay + "/" + (mMonth + 1) + "/" + mYear +
                        "\nCiudad: "+ ciudad +
                        "\nHobbie(s): " + hobbie);
            }
        }
    }
}

package com.example.medhacks2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private Button submit;
    private RadioButton zero;
    private RadioButton one;
    private RadioButton two;
    private RadioButton three;
    private RadioButton four;
    private RadioButton five;
    private int breathing;
    private RadioGroup group;
    private EditText dateOfBirth;
    private RadioGroup consciouness;
    private RadioButton yes;
    private RadioButton no;
    private TextView content;
    private String conscious;
    private EditText address;
    private EditText postalCode;
    private EditText LastName;
    private RadioGroup painGroup;
    private int pain;
    private RadioGroup bleedingGroup;
    private String bleed_importance;
    private EditText additional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        name = (EditText) findViewById(R.id.name);
        submit = (Button) findViewById(R.id.btn);
        LastName = (EditText)findViewById(R.id.Last_name);
        zero = (RadioButton) findViewById(R.id.zero);
        one = (RadioButton) findViewById(R.id.one);
        two = (RadioButton) findViewById(R.id.two);
        three = (RadioButton) findViewById(R.id.three);
        four = (RadioButton) findViewById(R.id.four);
        five = (RadioButton) findViewById(R.id.five);
        group = (RadioGroup) findViewById(R.id.radio_group);
        dateOfBirth = (EditText) findViewById(R.id.DateOfBirth);
        consciouness = (RadioGroup)findViewById(R.id.radio_group2);
        yes = (RadioButton)findViewById(R.id.Yes);
        no = (RadioButton)findViewById(R.id.No);
        address = (EditText)findViewById(R.id.Address);
        postalCode = (EditText)findViewById(R.id.Postal_Code);
        painGroup = (RadioGroup)findViewById(R.id.pain);
        bleedingGroup = (RadioGroup)findViewById(R.id.Bleed);
        additional = (EditText)findViewById(R.id.name_edit_text);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                name = (EditText) findViewById(R.id.name);
                submit = (Button) findViewById(R.id.btn);
                LastName = (EditText)findViewById(R.id.Last_name);
                zero = (RadioButton) findViewById(R.id.zero);
                one = (RadioButton) findViewById(R.id.one);
                two = (RadioButton) findViewById(R.id.two);
                three = (RadioButton) findViewById(R.id.three);
                four = (RadioButton) findViewById(R.id.four);
                five = (RadioButton) findViewById(R.id.five);
                group = (RadioGroup) findViewById(R.id.radio_group);
                dateOfBirth = (EditText) findViewById(R.id.DateOfBirth);
                consciouness = (RadioGroup)findViewById(R.id.radio_group2);
                yes = (RadioButton)findViewById(R.id.Yes);
                no = (RadioButton)findViewById(R.id.No);
                address = (EditText)findViewById(R.id.Address);
                postalCode = (EditText)findViewById(R.id.Postal_Code);
                painGroup = (RadioGroup)findViewById(R.id.pain);
                bleedingGroup = (RadioGroup)findViewById(R.id.Bleed);
                additional = (EditText)findViewById(R.id.name_edit_text);

                String patientName = name.getText().toString();
                int id = group.getCheckedRadioButtonId();
                String birth = dateOfBirth.getText().toString();
                String patient_address = address.getText().toString();
                String postal_code = postalCode.getText().toString();
                String Last_Name = LastName.getText().toString();
                Context context = getApplicationContext();
                String additional_info = additional.getText().toString();

                switch (id) {
                    case R.id.zero:
                        breathing = 0;
                        break;
                    case R.id.one:
                        breathing = 1;
                        break;
                    case R.id.two:
                        breathing = 2;
                        break;
                    case R.id.three:
                        breathing = 3;
                        break;
                    case R.id.four:
                        breathing = 4;
                        break;
                    case R.id.five:
                        breathing = 5;
                        break;
                    default:
                        breathing = -1;
                }
                int id2 = consciouness.getCheckedRadioButtonId();
                switch (id2) {
                    case R.id.Yes:
                        conscious = "Yes";
                        break;
                    case R.id.No:
                        conscious = "No";
                        break;
                    default:
                        conscious = null;
                }

                int id3 = painGroup.getCheckedRadioButtonId();
                switch (id3) {
                    case R.id.pzero:
                        pain = 0;
                        break;
                    case R.id.pone:
                        pain = 1;
                        break;
                    case R.id.ptwo:
                        pain = 2;
                        break;
                    case R.id.pthree:
                        pain = 3;
                        break;
                    case R.id.pfour:
                        pain = 4;
                        break;
                    case R.id.pfive:
                        pain = 5;
                        break;
                    default:
                        pain = -1;
                }

                int id4 = bleedingGroup.getCheckedRadioButtonId();
                switch (id4) {
                    case R.id.bzero:
                        bleed_importance = "No";
                        break;
                    case R.id.bone:
                        bleed_importance = "Little";
                        break;
                    case R.id.btwo:
                        bleed_importance = "Mild";
                        break;
                    case R.id.bthree:
                        bleed_importance = "Important";
                        break;
                    default:
                        bleed_importance = null;
                }

                String txt = TypeChecking(patientName, Last_Name, birth, breathing, conscious, pain, bleed_importance);
                if(txt.compareTo("") != 0){
                    Toast.makeText(context,txt, Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                        int consc;
                        if(conscious.compareTo("Yes") == 0){
                            consc = 1;
                        }
                        else{
                            consc = 0;
                        }
                        GetText(patientName, birth, breathing, consc, patient_address, postal_code, Last_Name, bleed_importance,
                                additional_info, pain);
                        openActivity2();

                    } catch (Exception ex) {
                        System.out.println("Quechose pas bon");
                    }
                }
            }
        });
    }

    public void GetText(String name, String DateOfBirth, int breathing, int conscious, String patient_address, String postal_code,
                        String LastName, String bleeding_importance, String additional_info, int pain) {
        StringBuilder sb = new StringBuilder();

        String http = "https://flask-gcloud.ue.r.appspot.com/patient/add";


        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(http);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.connect();

            //Create JSONObject here
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("first_name", name);
            jsonParam.put("last_name", LastName);
            jsonParam.put("date_of_birth", DateOfBirth);
            jsonParam.put("breathing_difficulty", ""+breathing);
            jsonParam.put("conscious", conscious);
            jsonParam.put("address", patient_address);
            jsonParam.put("postal_code", postal_code);
            jsonParam.put("bleeding", bleeding_importance);
            jsonParam.put("additional_info", additional_info);
            jsonParam.put("pain", pain);
            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            out.write(jsonParam.toString());
            out.close();

            int HttpResult = urlConnection.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                System.out.println("WOOOORKED");

            } else {
                System.out.println(urlConnection.getResponseMessage() + "KK FANERRRRRRR");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    public String TypeChecking(String firstName, String lastName, String dateOfBirth, int breathing, String conscious,
                             int pain, String bleeding){

        String text = "";

        if(firstName.length() == 0 || lastName.length() == 0){
            text = text + "-Please enter both the first name and the last name of the patient.\n";
        }
        if(!checkNames(firstName) || !checkNames(lastName)){
            text = text + "-Please ensure that the patient's name is written correctly\n";
        }
        if (!dateOfBirth.matches("\\d{4}-\\d{2}-\\d{2}")) {
            text = text + "-Please make sure that the patient's date of birth respects the right format.\n";
        }
        if(breathing == -1){
            text = text + "-Please select one option for breathing status.\n";
        }
        if(conscious == null){
            text = text + "-Please select option for consciousness status\n";
        }
        if(pain == -1){
            text = text + "-Please select one option for pain status\n";
        }
        if(bleeding == null){
            text = text + "-Please select one option for bleeding status\n";
        }
        return text;
    }
    public static boolean checkNames(String str){
        return ((str != null) && (!str.equals("")) && (str.matches("^[a-zA-Z]*$")));
    }
    public void openActivity2(){
        Intent intent = new Intent(this, Fragment.class);
        startActivity(intent);
    }
}




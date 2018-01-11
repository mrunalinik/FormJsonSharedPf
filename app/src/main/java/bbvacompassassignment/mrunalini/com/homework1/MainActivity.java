package bbvacompassassignment.mrunalini.com.homework1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import bbvacompassassignment.mrunalini.com.homework1.model.UserData;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
//Form data-->json-->shared preferences and print string in logcat.
public class MainActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    public static final String JSON_FILE_NAME = "json_file";
    private String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};
    private UserData mUserData;
    private SharedPreferences mSharedPreferences;
    private Gson gson;

    @BindView(R.id.name)
    EditText mName;
    @BindView(R.id.email)
    EditText mEmail;
    @BindView(R.id.phoneNumber)
    EditText mPhoneNumber;
    @BindView(R.id.SpinnerFeedbackType)
    Spinner mCountry;
    @BindView(R.id.radioGender)
    RadioGroup mGender;
    @BindView(R.id.btn_login)
    Button mLoginButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mUserData = new UserData();
        mSharedPreferences = getPreferences(Context.MODE_PRIVATE);
        gson = new Gson();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, countries);
        mCountry.setAdapter(adapter);
        mCountry.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mUserData.setCountry(countries[i]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @OnClick(R.id.btn_login)
    public void onLoginClicked() {
        mUserData.setUserName(mName.getText().toString());
        mUserData.setEmail(mEmail.getText().toString());
        mUserData.setPhoneNo(mPhoneNumber.getText().toString());
        saveToSharedPrefs();

    }

    private void saveToSharedPrefs() {
        Toast.makeText(this, "its working", Toast.LENGTH_SHORT);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        String json = gson.toJson(mUserData);
        Log.e("JSON FILE", json);
        editor.putString(JSON_FILE_NAME, json).apply();
      //  gson.fromJson(fileName, UserData.class);
    }

    @OnCheckedChanged({R.id.radioMale, R.id.radioFemale})
    public void onRadioButtonClicked(CompoundButton radioButton, boolean isChecked) {

        if (isChecked) {
            switch (radioButton.getId()) {
                case R.id.radioFemale:
                    mUserData.setGender("female");
                    break;
                case R.id.radioMale:
                    mUserData.setGender("male");
                    break;
                default:
                    break;
            }
        }
    }
}

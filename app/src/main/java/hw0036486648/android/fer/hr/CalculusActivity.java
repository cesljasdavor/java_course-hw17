package hw0036486648.android.fer.hr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import hw0036486648.android.fer.hr.functional_iterface.IBinaryOperator;

/**
 * Razred koji nasljeđuje razred {@link AppCompatActivity}. Primjerci ovog razreda predstavljaju
 * jedan prozor, a koji se koristi za izračun jednostavnih matematičkih operacija.
 * Ponuđene operacije su zbrajanje, oduzimanje, množenje i dijeljenje. Rezultat ovih operacija
 * ili poruke o pogrešci biti će prikazane na prozoru koji je primjerak razreda {@link DisplayActivity}
 *
 * @see AppCompatActivity
 * @see DisplayActivity
 *
 * @author Davor Češljaš
 */
public class CalculusActivity extends AppCompatActivity {
    /**
     * Konstanta koja predstavlja primjerak razreda {@link String} na koji se mapira
     * poruka koja se šalje primjerku razreda {@link DisplayActivity}.
     */
    public static final String CALCULATION_RESULT = "calcResult";
    /**
     * Konstanta koja predstavlja kod zahtjeva s kojim se pokreće primjerak razreda
     * {@link DisplayActivity}
     */
    public static final int DISPLAY_REQUEST_CODE = 86;

    /**
     * Konstanta koja predstavlja prazan niz znakova
     */
    private static final String EMPTY = "";

    /**
     * Konstanta koja predstavlja {@link Map} unutar koje su ključevi identifikatori primjeraka razreda
     * {@link RadioButton}, a vrijednosti operacije modelirane sučelje {@link IBinaryOperator} koje je
     * potrebno izvršiti ukoliko je {@link RadioButton} sa tim identifikatorom označen
     */
    private static final Map<Integer, IBinaryOperator<Double>> indexOperators;

    static {
        Map<Integer, IBinaryOperator<Double>> tmp = new HashMap<>();
        tmp.put(R.id.addRB, (a, b) -> a + b);
        tmp.put(R.id.subRB, (a, b) -> a - b);
        tmp.put(R.id.divRB, (a, b) -> a / b);
        tmp.put(R.id.mulRB, (a, b) -> a * b);

        indexOperators = Collections.unmodifiableMap(tmp);
    }

    /**
     * Članska varijabla koja predstavlja primjerak razreda {@link RadioGroup}, a koji
     * sprema modele svih operacija koje se mogu selektirati unutar ovog prozora
     */
    @BindView(R.id.operations)
    RadioGroup radioGroup;

    /**
     * Članska varijabla koja predstavlja primjerak razreda {@link EditText} iz kojeg se vadi prvi
     * operator
     */
    @BindView(R.id.firstNumberInput)
    EditText firstNumberInput;

    /**
     * Članska varijabla koja predstavlja primjerak razreda {@link EditText} iz kojeg se vadi drugi
     * operator
     */
    @BindView(R.id.secondNumberInput)
    EditText secondNumberInput;

    /**
     * Metoda koja se aktivira na pritisak gumba "Izračunaj" unutar aplikacije.
     * Ova metoda vrši izračun te rezultat ili poruku o pogrešci delegira primjerku razreda
     * {@link DisplayActivity}
     */
    @OnClick(R.id.calculate)
    void calculate() {
        Intent intent = new Intent(CalculusActivity.this, DisplayActivity.class);
        String operator = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();

        String message;
        try {
            double firstNumber = Double.parseDouble(firstNumberInput.getText().toString());
            double secondNumber = Double.parseDouble(secondNumberInput.getText().toString());

            double result = currentOperator.apply(firstNumber, secondNumber);

            message = String.format("Rezultat operacije '%s' je '%s'.", operator, String.valueOf(result));
        } catch (Exception e) {
            message = String.format(
                    "Prilikom obavljanja operacije '%s' nad unosima '%s' i '%s' došlo je do sljedeće greške: '%s'.",
                    operator,
                    firstNumberInput.getText().toString(),
                    secondNumberInput.getText().toString(),
                    e.getMessage());
        }

        intent.putExtra(CALCULATION_RESULT, message);

        startActivityForResult(intent, DISPLAY_REQUEST_CODE);
    }

    /**
     * Članska varijabla koja predstavlja operator trenutne selekcije unutar grupe
     * {@link RadioButton}-a
     */
    private IBinaryOperator<Double> currentOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculus);
        ButterKnife.bind(this);

        //pretpostavljena vrijednost
        setSelectedOperator(radioGroup.getCheckedRadioButtonId());

        radioGroup.setOnCheckedChangeListener((rg, id) -> setSelectedOperator(id));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        clearInput(requestCode, resultCode);
    }

    /**
     * Pomoćna metoda koja se koristi za pražnjenje tekstualnih unosa modeliranih razredom
     * {@link EditText}, a koje ovaj razred sadrži. Metoda će se izvršiti samo ukoliko
     * su ispravni predani parametri
     *
     * @param requestCode parametar za koji se očekuje da je jednak {@link #DISPLAY_REQUEST_CODE}
     *                    kako bi se ova metoda izvršila
     * @param resultCode parametar za koji se očekuje da je jednak {@link #RESULT_OK} kako bi se ova
     *                   metoda izvršila
     */
    private void clearInput(int requestCode, int resultCode) {
        if (requestCode == DISPLAY_REQUEST_CODE && resultCode == RESULT_OK) {
            firstNumberInput.setText(EMPTY);
            secondNumberInput.setText(EMPTY);
        }
    }

    /**
     * Pomoćna metoda koja na temelju predanog parametra <b>id</b> postavlja novu vrijednost
     * trenutne operacije {@link #currentOperator}.
     *
     * @param id identifikator na temelju kojeg se mijenja trenutno aktivna operacija
     */
    private void setSelectedOperator(int id) {
        currentOperator = indexOperators.get(id);
    }
}

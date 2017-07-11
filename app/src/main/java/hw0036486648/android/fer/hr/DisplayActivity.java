package hw0036486648.android.fer.hr;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Razred koji nasljeđuje razred {@link AppCompatActivity}. Primjerci ovog razreda predstavljaju
 * prozor za ispis rezultata. Ovaj rezultat dohvaća se kroz primjerak razreda {@link Intent}
 * s koji je primjerak ovog razreda stvoren.
 * <p>
 * Unutar samog prozora korisnik može pregledati rezultat te klikom "OK" se vratiti na
 * prozor koji je pozvao vaj prozor ili može prije no što se vrati na predhodni prozor
 * poslat izvještaj u obliku e-mail poruke
 * </p>
 *
 * @see AppCompatActivity
 *
 * @author Davor Češljaš
 */
public class DisplayActivity extends AppCompatActivity {

    /**
     * Konstanta koja predstavlja predpostavljenu adresu primatelja.
     */
    private static final String DEFAULT_EMAIL = "mailto:ana@baotic.org";

    /**
     * Konsanta koja predstavlja predpostavljeni subjekt poruke koja se šalje
     */
    private static final String DEFAULT_SUBJECT = "0036486648: dz report";

    /**
     * Metoda koja se poziva ukoliko korisnik pritisne gumb "OK". Ova metoda
     * jednostavno korisnika vraća na predhodni prozor, koji je pozvao ovaj
     */
    @OnClick(R.id.okButton)
    void okClicked() {
        closeActivity(RESULT_OK);
    }

    /**
     *
     * Pomoćna metoda koja se koristi za zatvaranje aktivnosti. Ova metoda prima jedan parametar
     * <b>resultStatus</b>, a koji predstavlja status izvođenja ovog prozora
     *
     * @param resultStatus status izvođenja ovog prozora
     */
    private void closeActivity(int resultStatus) {
        setResult(resultStatus);

        finish();
    }

    /**
     * Metoda koja se poziva ukoliko korisnik pritisne gumb "Pošalji izvještaj".
     * Ova metoda će otvoriti e-mail klijenta zaduženog za slanje e-pošte na
     * uređaju te će popuniti polja "To:", "Subject:" te tijelo poruke.
     * Po završetku slanja poruke, metoda će pozvati metodi {@link #closeActivity(int)}
     */
    @OnClick(R.id.sendReportButton)
    void sendReportCliked() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(DEFAULT_EMAIL));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, DEFAULT_SUBJECT);
        emailIntent.putExtra(Intent.EXTRA_TEXT, display.getText());

        try {
            startActivity(Intent.createChooser(emailIntent, "Pošalji izvještaj"));

            closeActivity(RESULT_OK);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(DisplayActivity.this, "Ne postoji e-mail klijent", Toast.LENGTH_SHORT).show();

            closeActivity(RESULT_CANCELED);
        }
    }

    /**
     * Članska varijabl akoja predstavlja dio ekrana na kojem se ispisuje rezultat.
     */
    @BindView(R.id.display)
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        ButterKnife.bind(this);

        display.setText(getIntent().getStringExtra(CalculusActivity.CALCULATION_RESULT));
    }
}

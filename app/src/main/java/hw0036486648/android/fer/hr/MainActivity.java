package hw0036486648.android.fer.hr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.Normalizer;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Razred koji nasljeđuje razred {@link AppCompatActivity}. Primjerci ovog razreda predstavljaju
 * glavni prozor aplikacije. Unutar ovog prozora ponuđene su dvije opcije.
 * <ol>
 * <li>Ukoliko korisnik želi vršiti neke od matematičkih operacija, može to učiniti
 * pritiskom na gumb "Matematika"</li>
 * <li>Ukoliko korisnik želi pregledavati neke statistike, može to učiniti pritiskom na gumb
 * "Statistika"</li>
 * </ol>
 *
 * Oba izbora korisnika vodi na drugi prozor. Tako će se za pritisak na gumb "Matematika" otvoriti
 * prozor modeliran razredom {@link CalculusActivity}, dok će se odabirom gumba "Statistika"
 * otvoriti prozor modeliran razredom {@link FormActivity}
 *
 * @see AppCompatActivity
 *
 * @author Davor Češljaš
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Metoda koja se poziva ukoliko korisnik pritisne gumb "Matematika"
     * Ova metoda stovriti će novi primjerak razreda {@link Intent}, a koji će
     * korisnika odvesti na prozor modeliran razredom {@link CalculusActivity}
     */
    @OnClick(R.id.math)
    void pickedMath() {
        startForClass(CalculusActivity.class);
    }

    /**
     * Metoda koja se poziva ukoliko korisnik pritisne gumb "Statistika"
     * Ova metoda stovriti će novi primjerak razreda {@link Intent}, a koji će
     * korisnika odvesti na prozor modeliran razredom {@link FormActivity}
     */
    @OnClick(R.id.stats)
    void pickedStats() {
        startForClass(FormActivity.class);
    }

    /**
     * Pomoćna metoda koja prima jedan parametar <b>acitvityClass</b>. Ovaj parametar predstavlja
     * opisnika razreda. Ova metoda će stvoriti primjerak razreda {@link Intent} gdje će kao odredište
     * postaviti upravo predani parametar.
     *
     * @param activityClass opisnika razreda s kojim se stvara novi primjerak razreda {@link Intent}
     */
    private void startForClass(Class<?> activityClass) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}

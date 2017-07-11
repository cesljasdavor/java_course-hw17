package hw0036486648.android.fer.hr;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hw0036486648.android.fer.hr.models.JsonResponse;
import hw0036486648.android.fer.hr.networking.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Razred koji nasljeđuje razred {@link AppCompatActivity}. Primjerci ovog razreda koriste se
 * kako bi za korisnika dohvatili JSON objekt modeliran razredom {@link JsonResponse}.
 * <p>
 * Ukoliko je korisnik upisao ispravnu lokaciju resursa na internetu, korisniku se ispisuje sav
 * dostupni sadržaj iz dohvaćenog primjerka razreda {@link JsonResponse}. Dodatno, korisnik je u
 * mogućnosti kliknuti na broj telefona osobe, čime će se pokrenuti aplikacija za unos broja sa
 * brojem iz JSON objekta. Korisnik također može poslati i e-mail na e-mail adresu koja je sastavni dio
 * svakog {@link JsonResponse} objekta.
 * </p>
 * <p>
 * Ukoliko korisnik nije upisao pravovaljani resurs na internetu, ekran će ostati nepromijenjen, a
 * korisniku će se ispisati toast poruka.
 * </p>
 *
 *
 * @see AppCompatActivity
 * @see JsonResponse
 *
 * @author Davor Češljaš
 */
public class FormActivity extends AppCompatActivity {
    /**
     * Privatna konstanta koja predstavlja niz znakova dvotočka te jedan razmak
     */
    private static final String COLON = ": ";

    /**
     * Konstanta koja predstavlja prazan niz znakova
     */
    private static final String EMPTY = "";

    /**
     * Članska varijabla koja predstavlja primjerak razreda {@link ImageView},
     * a koji predstavlja avatar osobe.
     */
    @BindView(R.id.image)
    ImageView image;

    /**
     * Članska varijabla koja predstavlja primjerak razreda {@link TextView}, a koji predstavlja
     * lokaciju resursa na internetu s kojeg se dohvaća avatar
     */
    @BindView(R.id.avatar_location)
    TextView avatarLocation;

    /**
     * Članska varijabla koja predstavlja primjerak razreda {@link TextView}, a koji predstavlja
     * ime osobe
     */
    @BindView(R.id.first_name)
    TextView firstName;

    /**
     * Članska varijabla koja predstavlja primjerak razreda {@link TextView}, a koji predstavlja
     * prezime osobe
     */
    @BindView(R.id.last_name)
    TextView lastName;

    /**
     * Članska varijabla koja predstavlja primjerak razreda {@link TextView}, a koji predstavlja
     * telefonski broj osobe
     */
    @BindView(R.id.phone_no)
    TextView phoneNo;

    /**
     * Članska varijabla koja predstavlja primjerak razreda {@link TextView}, a koji predstavlja
     * e-mail adresu osobe
     */
    @BindView(R.id.email_sknf)
    TextView emailSknf;

    /**
     * Članska varijabla koja predstavlja primjerak razreda {@link TextView}, a koji predstavlja
     * ime i prezime osobina suputnika/ce
     */
    @BindView(R.id.spouse)
    TextView spouse;

    /**
     * Članska varijabla koja predstavlja primjerak razreda {@link TextView}, a koji predstavlja
     * osobinu dob
     */
    @BindView(R.id.age)
    TextView age;

    /**
     * Članska varijabl akoja predstavlja primjerak razreda {@link EditText}. Unutar ovog primjerka
     * razreda unosi se putanja do resursa na internetu iz kojeg se dohvaća JSON objekt
     */
    @BindView(R.id.relativePathInput)
    EditText relativePathInput;

    /**
     * Članska varijabla koja predstavlja primjerak razreda {@link Retrofit}. Pomoću ovog primjerka
     * dohvaća se resurs sa interneta
     */
    private Retrofit retrofit;

    /**
     * Metoda koja se poziva u trenutku kada korisnik pritisne gumb "Učitaj".
     * Unutar ove metode dohvaća se resurs sa internete, a koji se nalazi unutar unosa
     * članske varijable {@link #relativePathInput} te se dobiveni objekt obrađuje.
     * Ukoliko je pak došlo do bilo kakve pogreške korisniku se ispisuje poruka o pogrešci
     * pozivome metode {@link #printToast(String)}
     */
    @OnClick(R.id.loadButton)
    void loadJSON() {
        String path = relativePathInput.getText().toString();

        if (path.isEmpty()) {
            printToast("Niste unijeli nikakve podatke");
            return;
        }

        RetrofitService service = retrofit.create(RetrofitService.class);

        service.getJson(path).enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                JsonResponse jsonResponse = response.body();
                if (jsonResponse == null) {
                    clearViews();
                    printToast(String.format("Na stranici '%s' ne postoji JSON objekt", path));
                    return;
                }

                setTextViews(jsonResponse);
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                clearViews();
                printToast(t.getMessage());
            }
        });
    }

    /**
     * Metoda koja se poziva ukoliko korisnik pritisne na telefonski broj osobe, a
     * koji se nalazi unutar dohvaćenog JSON objekta. Unutar ove metode, a ukoliko je broj valjan
     * korisnika se vodi na ekran za biranje brojeva
     */
    @OnClick(R.id.phone_no)
    void dial() {
        String numberString = phoneNo.getText().toString();
        if (numberString.isEmpty()) {
            printToast("Nemam podataka o telefonskom broju");
            return;
        }

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + numberString.split(COLON)[1]));
        startActivity(intent);
    }

    /**
     * Metoda koja se poziva ukoliko korisnik pritisne na e-mail adresu osobe, a
     * koji se nalazi unutar dohvaćenog JSON objekta. Unutar ove metode, a ukoliko je e-mail
     * adresa ispravna, otvara se novi prozor, gdje se korisniku omogućuje slanje elektorničke pošte
     * na dohvaćenu e-mail adresu osobe
     */
    @OnClick(R.id.email_sknf)
    void sentTo() {
        String emailString = emailSknf.getText().toString();
        if (emailString.isEmpty()) {
            printToast("Nemam podatke o primatelju");
        }

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        String email = emailString.split(COLON)[1];
        intent.setData(Uri.parse("mailto:" + email));

        try {
            startActivity(Intent.createChooser(intent, String.format("Pošalji poruku '%s'", email)));
        } catch (android.content.ActivityNotFoundException ex) {
            printToast("Ne postoji e-mail klijent");
        }
    }

    /**
     * Pomoćna metoda koja se koristi za micanje svih unosa koji su učitani na ekranu.
     * Ova metoda poziva metodu {@link #setTextViews(JsonResponse)} sa potpuno novim, praznim
     * primjerkom razreda {@link JsonResponse}. Time se dobiva efekt brisanja elemenata sa ekrana.
     */
    private void clearViews() {
        setTextViews(new JsonResponse());
    }

    /**
     * Pomoćna metoda koja se koristi za namještanje svih pogleda, a za koje se podaci mogu dobiti
     * iz predanog primjerka razreda {@link JsonResponse}. Ukoliko je predani parametar <code>null</code>
     * poziv ove metode neće imati nikakav utjecaj
     *
     * @param jsonResponse primjerak razreda {@link JsonResponse} koji se koristi za inicijalizaciju pogleda
     */
    private void setTextViews(JsonResponse jsonResponse) {
        if (jsonResponse == null) {
            return;
        }

        setTextView(avatarLocation, jsonResponse.getAvatarLocation(), JsonResponse.AVATAR_LOCATION);
        setImage(jsonResponse.getAvatarLocation());
        setTextView(firstName, jsonResponse.getFirstName(), JsonResponse.FIRST_NAME);
        setTextView(lastName, jsonResponse.getLastName(), JsonResponse.LAST_NAME);
        setTextView(phoneNo, jsonResponse.getPhoneNo(), JsonResponse.PHONE_NO);
        setTextView(emailSknf, jsonResponse.getEmailSknf(), JsonResponse.EMAIL_SKNF);
        setTextView(spouse, jsonResponse.getSpouse(), JsonResponse.SPOUSE);
        setTextView(age, jsonResponse.getAge(), JsonResponse.AGE);
    }

    /**
     * Pomoćna metoda koja se koristi za namještanje primjerka razreda {@link TextView} koji se
     * predaje kao parametar <b>textView</b>. Ovaj primjerak biti će namješten koristeći druga
     * dva predana parametra <b>fromJson</b> i <b>representation</b>. Ukoliko je <b>fromJson</b>
     * prazan ili <code>null</code>, predani primjerak razreda {@link TextView} maknuti će se s ekrana
     *
     * @param textView       primjerak razreda {@link TextView} koji se namješta
     * @param fromJson       primjerak razreda {@link Object} koji predstavlja bilo koji objekt, a koji
     *                       je dobiven iz <b>redaka</b> JSON objekta
     * @param representation primjerak razreda {@link String} koji opisuje namjenu objekta
     *                       <b>fromJson</b>
     */
    private void setTextView(TextView textView, Object fromJson, String representation) {
        if (fromJson == null || fromJson.toString().isEmpty()) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(representation + COLON + fromJson.toString());
        }
    }

    /**
     * Pomoćna metoda koja se koristi za namještanje slike avatara.
     * Ovoj metodi predaje se samo jedan parametar, a koji predstavlja lokaciju avatara
     * <b>avatarLocation</b>. Ukoliko lokacija nije ispravna čitav pogled se miće sa ekrana.
     *
     * @param avatarLocation primjerak razreda {@link String} ,a koji predsatvlja resurs na internetu
     *                       gdje se nalazi avatar
     */
    private void setImage(String avatarLocation) {
        if (avatarLocation == null || avatarLocation.isEmpty()) {
            image.setVisibility(View.GONE);
            return;
        }

        image.setVisibility(View.VISIBLE);
        Glide.with(FormActivity.this)
                .load(avatarLocation)
                .into(image);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ButterKnife.bind(this);

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://m.uploadedit.com")
                .build();
    }

    /**
     * Pomoćna metoda koja se koristi za ispis poruke o pogrešci korisniku, a koristeći
     * {@link Toast#makeText(Context, CharSequence, int)} metodu. Ovoj metodi predaje se
     * samo poruka korisniku koju je potrebno ispisati <b>message</b>
     *
     * @param message poruka korisniku koju je potrebno ispisati
     */
    private void printToast(String message) {
        Toast.makeText(FormActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}

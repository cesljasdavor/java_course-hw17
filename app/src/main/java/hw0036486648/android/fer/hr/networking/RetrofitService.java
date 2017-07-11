package hw0036486648.android.fer.hr.networking;


import hw0036486648.android.fer.hr.models.JsonResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Sučelje koje se koristi kao pomoćno sučelje za rad sa razredom {@link retrofit2.Retrofit}.
 * Ovo sučelje sastoji se od jedne metode, a koja služi za dohvat resursa sa predane poveznice.
 *
 * @see retrofit2.Retrofit
 *
 * @author Davor Češljaš
 */
public interface RetrofitService {

    /**
     * Metoda koja se koristi za dohvat JSON objekta sa interneta, a preko parametra
     * predanog kao <b>path</b>, a koji predstavlja resurs na internetu.
     *
     * @param path putanja koja predstavlja neki resurs na internetu
     * @return primjerak sučelja {@link Call}, koji se koristi za slanje zahtjeva. Ovaj zahtjev je
     *          takav da se kao rezultat očekuje objekt tipa {@link JsonResponse}.
     */
    @GET("{path}")
    Call<JsonResponse> getJson(@Path("path") String path);
}

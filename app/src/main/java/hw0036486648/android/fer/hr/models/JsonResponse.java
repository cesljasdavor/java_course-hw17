package hw0036486648.android.fer.hr.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Razred koji se koristi kao model podataka, a koji se dohvaćaju s interneta.
 * U primjerak ovog razreda oguće je parsirati bilo koji JSON objekt, a koji ima barem
 * jedno o sljedećih polja:
 * <ul>
 * <li>{@value #AVATAR_LOCATION}</li>
 * <li>{@value #FIRST_NAME}</li>
 * <li>{@value #LAST_NAME}</li>
 * <li>{@value #PHONE_NO}</li>
 * <li>{@value #EMAIL_SKNF}</li>
 * <li>{@value #SPOUSE}</li>
 * <li>{@value #AGE}</li>
 * </ul>
 * <p>
 * Razred je također serijalizabilan jer implementira sučelje {@link Serializable}
 *
 * @author Davor Češljaš
 */
public class JsonResponse implements Serializable {
    /**
     * Konstanta koja predstavlja primjerak razreda {@link String} "avatar_location",
     * a na koji se u JSON-u mapira lokacija avatara
     */
    public static final String AVATAR_LOCATION = "avatar_location";
    /**
     * Konstanta koja predstavlja primjerak razreda {@link String} "first_name",
     * a na koji se u JSON-u mapira ime osobe
     */
    public static final String FIRST_NAME = "first_name";
    /**
     * Konstanta koja predstavlja primjerak razreda {@link String} "last_name",
     * a na koji se u JSON-u mapira prezime osobe
     */
    public static final String LAST_NAME = "last_name";
    /**
     * Konstanta koja predstavlja primjerak razreda {@link String} "phone_no",
     * a na koji se u JSON-u mapira telefonski broj osobe
     */
    public static final String PHONE_NO = "phone_no";
    /**
     * Konstanta koja predstavlja primjerak razreda {@link String} "email_sknf",
     * a na koji se u JSON-u mapira e-mail adresa osobe
     */
    public static final String EMAIL_SKNF = "email_sknf";
    /**
     * Konstanta koja predstavlja primjerak razreda {@link String} "spouse",
     * a na koji se u JSON-u mapira ime i prezime suputnika
     */
    public static final String SPOUSE = "spouse";
    /**
     * Konstanta koja predstavlja primjerak razreda {@link String} "age",
     * a na koji se u JSON-u mapira osobina dob
     */
    public static final String AGE = "age";


    /**
     * Članska varijabla koja predstavlja lokaciju avatara
     */
    @SerializedName(AVATAR_LOCATION)
    String avatarLocation;

    /**
     * Članska varijabla koja predstavlja ime osobe
     */
    @SerializedName(FIRST_NAME)
    String firstName;

    /**
     * Članska varijabla koja predstavlja prezime osobe
     */
    @SerializedName(LAST_NAME)
    String lastName;

    /**
     * Članska varijabla koja predstavlja telefonski broj osobe
     */
    @SerializedName(PHONE_NO)
    String phoneNo;

    /**
     * Članska varijabla koja predstavlja e-mail adresu osobe
     */
    @SerializedName(EMAIL_SKNF)
    String emailSknf;

    /**
     * Članska varijabla koja predstavlja ime i prezime suputnika
     */
    @SerializedName(SPOUSE)
    String spouse;

    /**
     * Članska varijabla koja predstavlja osobinu dob
     */
    @SerializedName(AGE)
    Integer age;

    /**
     * Konstrukor koji inicijalizira primjerak ovog razreda. Ovaj konstruktor je prazan, te
     * se time sve članske varijable namještaju na <code>null</code>
     */
    public JsonResponse() {
    }

    /**
     * Konstruktor koji inicijalizira primjerak ovog razreda. Unutar ovog konstruktora,
     * svi predani parametri interno se pohranjuju.
     *
     * @param avatarLocation predstavlja lokaciju avatara
     * @param firstName      predstavlja ime osobe
     * @param lastName       predstavlja prezime osobe
     * @param phoneNo        predstavlja telefonski broj osobe
     * @param emailSknf      predstavlja e-mail adresu osobe
     * @param spouse         predstavlja ime i prezime osobina suputnika/ce
     * @param age            predstavlja dob osobe
     */
    public JsonResponse(String avatarLocation, String firstName,
                        String lastName, String phoneNo,
                        String emailSknf, String spouse, Integer age) {
        this.avatarLocation = avatarLocation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.emailSknf = emailSknf;
        this.spouse = spouse;
        this.age = age;
    }

    /**
     * Metoda koja dohvaća lokaciju avatara
     *
     * @return lokaciju avatara
     */
    public String getAvatarLocation() {
        return avatarLocation;
    }

    /**
     * Metoda koja dohvaća ime osobe
     *
     * @return ime osobe
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Metoda koja dohvaća prezime osobe
     *
     * @return prezime osobe
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Metoda koja dohvaća telefonski broj osobe
     *
     * @return telefonski broj osobe
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Metoda koja dohvaća e-mail adresu osobe
     *
     * @return e-mail adresu osobe
     */
    public String getEmailSknf() {
        return emailSknf;
    }

    /**
     * Metoda koja dohvaća ime i prezime suputnika/ce osobe
     *
     * @return ime i prezime suputnika/ce osobe
     */
    public String getSpouse() {
        return spouse;
    }

    /**
     * Metoda koja dohvaća dob osobe
     *
     * @return dob osobe
     */
    public Integer getAge() {
        return age;
    }
}

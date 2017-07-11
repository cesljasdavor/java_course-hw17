package hw0036486648.android.fer.hr.functional_iterface;

/**
 * Sučelje koje predstavlja jednu binarnu operaciju. Ovo sučelje ima jedan
 * parametrizirani tip {@link T}. Primjerci ovog razreda biti će svi parametri metode
 * {@link #apply(Object, Object)}, te će i rezultat biti primjerak tog razreda.
 * Sučelje se koristi kao supstitucija sučelja {@link java.util.function.BinaryOperator} koji
 * se zbog API razine, a koju ova aplikacija koristi, ne može koristiti.
 *
 * @param <T> parametrizirani tip. Ovog tipa biti će svi argumenti te rezultat metode {@link #apply(Object, Object)}
 *
 * @see java.util.function.BinaryOperator
 *
 * @author Davor Češljaš
 */
public interface IBinaryOperator<T> {
    /**
     * Metoda koja kao ulazne parametre prima dva primjerka bilo kojeg razreda. Važno je uočiti da
     * parametri <b>a</b> i <b>b</b> moraju biti istog tipa. Metoda potom vrši izračun nad tim dvama
     * parametrima i kao rezultat vraća također primjerak razreda {@link T}
     *
     * @param a prvi parametar koji se koristi za izračun
     * @param b drugi parametar koji se koristi za izračun
     * @return rezultat poziva ove metode (primjerak istog razreda kao i parametri <b>a</b> i <b>b</b>)
     */
    T apply(T a, T b);
}

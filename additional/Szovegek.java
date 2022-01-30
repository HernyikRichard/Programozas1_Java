package additional;


/**
 * a Szovegek osztály definiálja a játék által dobott szövegeket tároljuk el.
 */
public class Szovegek {

    private String options;
    private String iranyitas;
    private String palyaElemek;
    private String kraftok;
    private String kezdoSzoveg;

    public Szovegek() {
        this.setOptions();
        this.setIranyitas();
        this.setPalyaElemek();
        this.setKraftok();
        this.setKezdoSzoveg();
    }

    public String getKezdoSzoveg() {
        return kezdoSzoveg;
    }

    public void setKezdoSzoveg() {
        this.kezdoSzoveg = """

                ┌─────────────────────────────────────────────────────────────────────┐
                │ üdv a Java raft játékban a játék célja, hogy 1000 lépést cselekvést │
                │     túlélj a vad és zórd tengeren. Mielött elkezdenéd a harcot,     │
                │ kérlek válasz a új játék kezdés vagy már meglévő mentésed betőltése │
                │                              között.                                │
                │          Új játékhoz nyomd az 1-est, betőltéshez a 2-est.           │
                └─────────────────────────────────────────────────────────────────────┘

                """;
    }

    private void setIranyitas() {
        this.iranyitas = """

                ┌──────────┬────────────────┬────────────┬────────────────┐
                │ fel -> 8 │ fel-jobra -> 9 │ jobra -> 6 │ jobra-le -> 3  │
                ├──────────┼────────────────┼────────────┼────────────────┤
                │ le -> 2  │ le-balra -> 1  │ balra -> 4 │ balra-fel -> 7 │
                └──────────┴────────────────┴────────────┴────────────────┘
                """;

    }

    public String getIranyitas() {
        return iranyitas;
    }

    public void setOptions() {
        this.options = """

                ┌─────────────────────────────────────────────┬────────────────────────────────────────────────────────────────────────┐
                │ Léphetsz a "mozog" szóval.                  │ Karakter teljes infó lekéréséért írd be "info" szót.                   │
                ├─────────────────────────────────────────────┼────────────────────────────────────────────────────────────────────────┤
                │ Kiléphetsz a "kilepes" szóval.              │ Horgászáshoz írd be a "horgasz" szót és szurkolj.                      │
                ├─────────────────────────────────────────────┼────────────────────────────────────────────────────────────────────────┤
                │ Pálya megjelenitéshez írd be a "palya" szót.│ Kraftoláshoz írd be a "kraft" szót és tobábbi opciók megjelennek.      │
                ├─────────────────────────────────────────────┼────────────────────────────────────────────────────────────────────────┤
                │ Sütéshez írd be a "sut" szót.               │ Evéshez írd be a "eszik" szót.                                         │
                ├─────────────────────────────────────────────┼────────────────────────────────────────────────────────────────────────┤
                │ Iváshoz írd be a "iszik" szót.              │ Mentéshez írd be a "mentes" szót                                       │
                └─────────────────────────────────────────────┴────────────────────────────────────────────────────────────────────────┘

                """;


    }

    public String getOptions() {
        return options;
    }

    public String getPalyaElemek() {
        return palyaElemek;
    }

    public void setPalyaElemek() {
        this.palyaElemek = """

                ┌────────────────────────────────┐
                │        Jelek a térképen        │
                ├───┬───────────┬▄┬───┬──────────┤
                │   │   tenger  │█│ D │  Deszka  │
                ├───┼───────────┼█┼───┼──────────┤
                │ P │  Platform │█│ L │   Level  │
                ├───┼───────────┼█┼───┼──────────┤
                │ ░ │    Halo   │█│ H │ Hulladek │
                ├───┼───────────┼█┼───┼──────────┤
                │ • │Viztisztito│█│ B │   Hordo  │
                ├───┼───────────┼█┼───┼──────────┤
                │ ¶ │  Tuzhely  │█│   │          │
                └───┴───────────┴▀┴───┴──────────┘
                """;

    }

    public String getKraftok() {
        return kraftok;
    }

    public void setKraftok() {
        this.kraftok = """
                ┌─────────────────────────────────────────────────┐
                │                 Kraft Lehetőségek               │
                ├─┬───────────┬▄┬─────────────────────────────────┤
                │1│  Platfor  │█│       2 Deszka + 2 Levél        │
                ├─┼───────────┼█┼─────────────────────────────────┤
                │2│   Lánzsa  │█│ 4 Deszka + 4 Levél + 4 Hulladék │
                ├─┼───────────┼█┼─────────────────────────────────┤
                │3│  Tűzhely  │█│ 2 Deszka + 4 Levél + 3 Hulladék │
                ├─┼───────────┼█┼─────────────────────────────────┤
                │4│Viztisztito│█│      2 Levél  + 4 Hulladék      │
                ├─┼───────────┼█┼─────────────────────────────────┤
                │5│   Háló    │█│        2 Deszka + 6 Levél       │
                ├─┴───────────┴▀┴─────────────────────────────────┤
                │ Fontos, hogy amilyen ponton kroftolod az itemet,│
                │    azonon a ponton lehelyezésre is kerül, ha    │
                │  van megfelelő mennyiségü alap anyagod és jó,   │
                │                  helyen állsz.                  │
                └─────────────────────────────────────────────────┘

                """;

    }


}

public enum Responses {
    //list of possible server responses
    OK("OK"),
    TERM("TERM "),
    NONE("NONE"),
    JOBP("JOBP"),
    JOBN("JOBN"),
    RESR("RESR"),
    RESF("RESF"),
    JCPL("JCPL")
    ;

    private final String text;

    Responses(final String text) {
        this.text = text;
    }

    public String get() {
        return text;
    }
}

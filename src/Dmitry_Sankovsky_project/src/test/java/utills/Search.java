package utills;

public class Search {
    public String user;
    public boolean strict; //false - partial name

    public Search(String user, boolean strict) {
        this.user = user;
        this.strict = strict;
    }

}

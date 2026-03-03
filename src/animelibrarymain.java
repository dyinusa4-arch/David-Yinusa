public static void main(String args[]) {
    animelibrary store = new animelibrary();

    //should be in set of watchlist
    store.add("Naruto");
    store.add("Bleach");
    store.add("One Piece");
    store.advance("Bleach");
    //should return 2
    int place = store.section("Bleach");

    store.add("JJK");
    store.remove("JJK");

    //advanced twice
    store.moveToWatched("One Piece");
    store.tier("One Piece"); // should be unranked

    store.changeTier("One Piece", "SS");

    store.contains("DBZ"); //false

    store.advance("Naruto");

    store.lengthOfAll(); //3

}

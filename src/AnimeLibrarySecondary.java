/**
 * Layered implementations of secondary methods for {@code AnimeLibrary}.
 */
public abstract class AnimeLibrarySecondary implements AnimeLibrary{

    /*
    * Secondary Methods
    */

    public int lengthOfAll() {
        return this.length(1) + this.length(2)  + this.length(3);
    }

    public void addToWatching(String show) {
        this.add(show);
        this.advance(show);
    }

    public void addToWatched(String show) {
        this.add(show);
        this.advance(show);
        this.advance(show);
    }

    public void addToWatched(String show, String tier) {
        this.add(show);
        this.advance(show);
        this.advance(show);
        this.changeTier(show, tier);
    }

    /*
    * Common methods (from Object)
    */


}
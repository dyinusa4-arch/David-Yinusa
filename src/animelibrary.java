/**
 * {@code AnimeLibraryKernel} enhanced with secondary methods.
 *
 * @author David Yinusa
 */
public interface AnimeLibrary extends AnimeLibraryKernel {

    /**
     * Returns the length of {@code this}.
     *
     * @return the number of shows in this
     * @ensures lengthOfAll = length(Watchlist) + length(currWatch) +
     *          length(Watched)
     */
    int lengthOfAll();

    /**
     * adds {@code show} to watching section.
     *
     * @param show
     *            the show to be added to this
     * @requires show is not in this
     * @updates this
     * @ensures {@code this = <show> * #this} and this.length(currWatch) =
     *          #this.length(currWatch) + 1
     */
    void addToWatching(String show);

    /**
     * adds {@code show} to watched section.
     *
     * @param show
     *            the show to be added to this
     * @requires show is not in this
     * @updates this
     * @ensures {@code this = <show> * #this} and this.length(Watched) =
     *          #this.length(Watched) + 1
     */
    void addToWatched(String show);

    /**
     * adds {@code show} to watched section and assigns it the tier
     * {@code tier}.
     *
     * @param show
     *            the show to be added to this
     * @param tier
     *            the tier to be assigned to show
     * @requires show is not in this
     * @updates this
     * @ensures {@code this = <show> * #this} and this.tier(show) = {@code tier}
     *          and this.length(Watched) = #this.length(Watched) + 1
     */
    void addToWatched(String show, Tier tier);
}

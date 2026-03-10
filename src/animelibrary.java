/**
 * {@code AnimeLibraryKernel} enhanced with secondary methods.
 */
public interface AnimeLibrary extends AnimeLibraryKernel {

    /**
     * Returns the length of {@code this}.
     *
     * @return the number of shows in this
     * @ensures lengthOfAll = length(1) + length(2) + length(3)
     */
    int lengthOfAll();

    /**
     * adds {@code show} to watching section.
     *
     * @param show
     *            the show to be added to this
     * @requires show is not in this
     * @updates this
     * @ensures {@code this = <show> * #this} and this.section(show) = 2
     */
    void addToWatching(String show);

    /**
     * adds {@code show} to watched section.
     *
     * @param show
     *            the show to be added to this
     * @requires show is not in this
     * @updates this
     * @ensures {@code this = <show> * #this} and this.section(show) = 3
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
     * @ensures {@code this = <show> * #this} and this.section(show) = 3 and
     *          this.tier(show) = {@code tier}
     */
    void addToWatched(String show, String tier);
}

package com.palarz.mike.chucknorris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * A singleton Java class that provides Chuck Norris jokes. It is a singleton because there can
 * only ever be one Chuck Norris.
 */

// TODO: Make sure at some point to give credit to ICNDB.
public class ChuckNorris {

    // An ArrayList that stores all of the jokes
    private final List<String> ALL_JOKES = new ArrayList<>(Arrays.asList(
            "The Chuck Norris military unit was not used in the game Civilization 4, because a single Chuck Norris could defeat the entire combined nations of the world in one turn.",
            "Chuck Norris is the only human being to display the Heisenberg uncertainty principle - you can never know both exactly where and how quickly he will roundhouse-kick you in the face.",
            "Chuck Norris originally appeared in the \'Street Fighter II\' video game, but was removed by Beta Testers because every button caused him to do a roundhouse kick. When asked about this glitch, Norris replied \"That's no glitch\"",
            "Scientists have estimated that the energy given off during the Big Bang is roughly equal to 1CNRhK (Chuck Norris Roundhouse Kick).",
            "Chuck Norris knows the last digit of pi.",
            "When Chuck Norris throws exceptions, it's across the room.",
            "All arrays Chuck Norris declares are of infinite size, because Chuck Norris knows no bounds.",
            "Chuck Norris writes code that optimizes itself.",
            "Chuck Norris doesn't need garbage collection because he doesn't call .Dispose(), he calls .DropKick().",
            "Chuck Norris's first program was kill -9.",
            "Chuck Norris burst the dot com bubble.",
            "Chuck Norris can write infinite recursion functions and have them return.",
            "Chuck Norris can solve the Towers of Hanoi in one move.",
            "Chuck Norris finished World of Warcraft.",
            "Chuck Norris doesn't use web standards as the web will conform to him.",
            "Chuck Norris can delete the Recycling Bin.",
            "Chuck Norris's beard can type 140 wpm.",
            "Chuck Norris can unit test entire applications with a single assert.",
            "Chuck Norris doesn't bug hunt as that signifies a probability of failure, he goes bug killing.",
            "Chuck Norris's keyboard doesn't have a Ctrl key because nothing controls Chuck Norris.",
            "Chuck Norris doesn't need a debugger, he just stares down the bug until the code confesses.",
            "Chuck Norris can access private methods.",
            "Chuck Norris can instantiate an abstract class.",
            "Chuck Norris does not need to know about class factory pattern. He can instantiate interfaces.",
            "The class object inherits from Chuck Norris",
            "No statement can catch the ChuckNorrisException.",
            "Chuck Norris can write multi-threaded applications with a single thread.",
            "There is no Esc key on Chuck Norris' keyboard, because no one escapes Chuck Norris.",
            "Chuck Norris can binary search unsorted data.",
            "Chuck Norris breaks RSA 128-bit encrypted codes in milliseconds.",
            "Chuck Norris went out of an infinite loop.",
            "If Chuck Norris writes code with bugs, the bugs fix themselves.",
            "Chuck Norris's keyboard has the Any key.",
            "Chuck Norris can access the DB from the UI.",
            "Chuck Norris' programs never exit, they terminate.",
            "Chuck Norris doesn't need an OS.",
            "Chuck Norris's OSI network model has only one layer - Physical.",
            "Chuck Norris compresses his files by doing a flying round house kick to the hard drive.",
            "No one has ever spoken during review of Chuck Norris' code and lived to tell about it.",
            "Chuck Norris doesn't use GUI, he prefers COMMAND line.",
            "Chuck Norris doesn't use Oracle, he is the Oracle.",
            "Chuck Norris can dereference NULL.",
            "A diff between your code and Chuck Norris's is infinite.",
            "The Chuck Norris Eclipse plugin made alien contact.",
            "Each hair in Chuck Norris's beard contributes to make the world's largest DDOS.",
            "Chuck Norris's database has only one table, 'Kick', which he DROPs frequently.",
            "Chuck Norris does infinit loops in 4 seconds.",
            "Product Owners never ask Chuck Norris for more features. They ask for mercy.",
            "Chuck Norris knows the value of NULL, and he can sort by it too.",
            "Chuck Norris can install a 64 bit OS on 32 bit machines.",
            "Chuck Norris can write to an output stream.",
            "Chuck Norris can read from an input stream.",
            "Chuck Norris never has to build his program to machine code. Machines have learnt to interpret Chuck Norris code.",
            "Chuck Norris causes the Windows Blue Screen of Death.",
            "Chuck Norris' unit tests don't run. They die.",
            "Chuck Norris can make a class that is both abstract and final.",
            "Chuck Norris could use anything in java.util.* to kill you, including the javadocs.",
            "Code runs faster when Chuck Norris watches it.")
    );

    // A String that indicates that ChuckNorris hasn't been instantiated yet
    private final String NO_JOKES = "No jokes available. Are you sure that you've used get()?";

    /* A ChuckNorris object is made both private and static so that this class is the only one
     * capable of creating an instance of ChuckNorris and so that there is only ever one instance
     * of this object.
      */
    private static ChuckNorris sChuckNorris;
    private List<String> mJokes;

    // A public method which creates a new, single instance of ChuckNorris if it doesn't yet exist
    // and then returns a reference to that single instance.
    public static ChuckNorris get() {
        if (sChuckNorris == null) {
            sChuckNorris = new ChuckNorris();
        }

        return sChuckNorris;
    }

    private ChuckNorris() {
        mJokes = ALL_JOKES;
    }

    // A method which returns a random joke from ALL_JOKES
    public String getRandomJoke() {
        if (sChuckNorris != null){
            int index = new Random().nextInt(mJokes.size());
            return mJokes.get(index);
        }
        else {
            return NO_JOKES;
        }
    }

    // A method which returns a specific joke from ALL_JOKES, given the index.
    public String getSpecificJoke(int index) {
        if (sChuckNorris != null) {
            return mJokes.get(index);
        }
        else {
            return NO_JOKES;
        }
    }

    // main() is  used to test out each of the methods
    public static void main(String[] args) {
        ChuckNorris chuckNorris = ChuckNorris.get();
        System.out.println("Here is a random joke:\n" + chuckNorris.getRandomJoke());
        System.out.println("Here is a specific joke:\n" + chuckNorris.getSpecificJoke(2));
    }

}

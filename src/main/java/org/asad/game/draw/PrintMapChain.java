package org.asad.game.draw;

import org.asad.game.entity.AtomEntity;
import org.asad.game.entity.chapter.Chapter;
import org.asad.game.entity.place.Place;
import org.asad.game.helper.ConsoleLogger;

import java.util.Collections;
import java.util.stream.IntStream;

/**
 * it print the Map after printing stats
 */
public class PrintMapChain implements PrintChainInterface {
    private int characterNameSize;
    private String rowSeparator;
    private Chapter chapter;
    private String headerCeiling;

    /**
     * This method is responsible for drawing Map with places.
     */
    @Override
    public void draw() {
        ConsoleLogger.println("######################################### Map #######################################");
        ConsoleLogger.println(headerCeiling);
        IntStream.range(0, chapter.getMap().length).forEach(i -> {
            ConsoleLogger.print("|");
            IntStream.range(0, chapter.getMap()[0].length).forEach(j -> {
                Place place = chapter.getMap()[i][j];
                if (null == place) {
                    ConsoleLogger.print(rowSeparator + "|");
                } else {
                    printCharacter(place);
                }
            });
            ConsoleLogger.println("");
        });
    }

    public PrintMapChain(Chapter chapter) {
        this.chapter = chapter;
        initialize();
    }

    /**
     * this method is responsible for initializing certain variable which will be required further to while drawing the map.
     * it should from inside the constructor at the end.
     */
    private void initialize() {
        characterNameSize = chapter.getHero().getName().length();
        chapter.getPlaces().forEach(place -> {
            if (characterNameSize < place.getName().length()) {
                characterNameSize = place.getName().length();
            }
        });
        characterNameSize = characterNameSize+2;
        rowSeparator = String.join("", Collections.nCopies(characterNameSize,"_"));
        headerCeiling = " " + String.join(" ", Collections.nCopies(chapter.getMap().length, rowSeparator));
    }

    /**
     * this method print the place / player name in the map.
     * @param place
     */
    private void printCharacter(Place place) {
        StringBuilder stringBuilder = new StringBuilder(AtomEntity.underline);
        String padding;
        int paddingSpaces;
        paddingSpaces = characterNameSize - place.getName().length();
        padding = String.join("", Collections.nCopies(paddingSpaces / 2," "));
        stringBuilder
                .append(place.getColor())
                .append(padding)
                .append(place.getName())
                .append(padding);
        if (paddingSpaces % 2 != 0) {
            stringBuilder
                    .append(" ");
        }
        stringBuilder
                .append(AtomEntity.resetColor)
                .append("|");
        ConsoleLogger.print(stringBuilder.toString());
    }

    @Override
    public void setNextPaintChain(PrintChainInterface paintChain) {
    }
}

package org.asad.game.draw;

import org.asad.game.entity.AtomEntity;
import org.asad.game.entity.chapter.Chapter;
import org.asad.game.entity.place.Place;
import org.asad.game.helper.ConsoleLogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * it print the stats of the Players
 */
public class PrintStatsChain implements PrintChainInterface {

    private int characterNameSize;
    private String rowSeparator;
    private PrintChainInterface paintChain;
    private Chapter chapter;
    private String headerCeiling;

    List<String> headers = new ArrayList<String>(){{
        add("Player Name");
        add("Health");
        add("Skill");
        add("Anxiety");
        add("Money");
    }};

    public PrintStatsChain(Chapter chapter) {
        this.chapter = chapter;
        initialize();
    }

    @Override
    public void setNextPaintChain(PrintChainInterface paintChain) {
        this.paintChain = paintChain;
    }

    /**
     * it draws the status grid of all the players including Hero.
     */
    @Override
    public void draw() {
        ConsoleLogger.println("######################## Player Stats ################################");
        List<Place> opponents = chapter.getPlaces().stream().filter(place -> null != place.getPlayer()).collect(Collectors.toList());
        ConsoleLogger.println(headerCeiling);
        IntStream.range(0, (opponents.size()+2)).forEach(i -> {
            ConsoleLogger.print("|");
            IntStream.range(0, headers.size()).forEach(j -> {
                if (i == 0) {
                    printContentInTheCenter(headers.get(j), "");
                } else if (i == 1) {
                    printCellData(j, chapter.getHero());
                } else {
                    int indexOfOpponent = i-2;
                    printCellData(j, opponents.get(indexOfOpponent));
                }
            });
            ConsoleLogger.println("");
        });

        if(paintChain !=null && !chapter.getPlaces().isEmpty()){
            ConsoleLogger.println("");
            ConsoleLogger.println("");
            paintChain.draw();
        }
    }

    /**
     * it initialises the {@link PrintStatsChain} to identify the cell width.
     */
    private void initialize() {
        characterNameSize = 25;
        characterNameSize = characterNameSize+2;
        rowSeparator = String.join("", Collections.nCopies(characterNameSize,"_"));
        headerCeiling = " " + String.join(" ", Collections.nCopies( headers.size(), rowSeparator));
    }

    /**
     * this method print the input with color in the center of the cell
     * @param string
     * @param color
     */
    private void printContentInTheCenter(String string, String color) {
        StringBuilder stringBuilder = new StringBuilder(AtomEntity.underline);
        String padding;
        int paddingSpaces = characterNameSize - string.length();
        padding = String.join("", Collections.nCopies(paddingSpaces / 2," "));
        stringBuilder
                .append(color)
                .append(padding)
                .append(string)
                .append(padding);
        if (paddingSpaces % 2 != 0) {
            stringBuilder
                    .append(' ');
        }
        stringBuilder
                .append(AtomEntity.resetColor)
                .append('|');
        ConsoleLogger.print(stringBuilder.toString());
    }

    /**
     * it prints the player / place attributes on cell.
     * @param j
     * @param place
     */
    private void printCellData(int j, Place place) {
        if (j == 0) {
            printContentInTheCenter(place.getName(), place.getColor());
        } else if (j == 1) {
            printContentInTheCenter(String.valueOf(place.getHealth()), "");
        } else if (j == 2) {
            printContentInTheCenter(String.valueOf(place.getSkill()), "");
        } else if (j == 3) {
            printContentInTheCenter(String.valueOf(place.getAnxiety()), "");
        } else if (j == 4) {
            printContentInTheCenter(String.valueOf(place.getMoney()), "");
        }
    }

}

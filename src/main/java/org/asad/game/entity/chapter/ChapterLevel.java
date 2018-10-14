package org.asad.game.entity.chapter;

public enum ChapterLevel {
    ONE("The blood-splattered BRIDE"), TWO("The Origin of O-Ren"), THREE("Face to Face"),;

    private String nameOfTheChapter;

    public String getNameOfTheChapter() {
        return nameOfTheChapter;
    }

    ChapterLevel(String nameOfTheChapter) {
        this.nameOfTheChapter = nameOfTheChapter;
    }
}

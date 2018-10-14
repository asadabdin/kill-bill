package org.asad.game.draw;

public interface PrintChainInterface {
    void setNextPaintChain(PrintChainInterface paintChain);
    void draw();
}

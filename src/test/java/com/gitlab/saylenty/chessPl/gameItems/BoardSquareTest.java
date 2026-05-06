package com.gitlab.saylenty.chessPl.gameItems;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardSquareTest {

    @Test
    void constructorStoresCoordinates() {
        var square = new BoardSquare(3, 5);
        assertThat(square.getX()).isEqualTo(3);
        assertThat(square.getY()).isEqualTo(5);
    }

    @Test
    void isUnlockedInitially() {
        var square = new BoardSquare(0, 0);
        assertThat(square.isUnlocked()).isTrue();
        assertThat(square.isLocked()).isFalse();
    }

    @Test
    void lockMakesSquareLocked() {
        var square = new BoardSquare(0, 0);
        square.lock();
        assertThat(square.isLocked()).isTrue();
        assertThat(square.isUnlocked()).isFalse();
    }

    @Test
    void unlockAfterSingleLockRestoresSquare() {
        var square = new BoardSquare(0, 0);
        square.lock();
        square.unlock();
        assertThat(square.isUnlocked()).isTrue();
    }

    @Test
    void lockTwiceUnlockOnceStillLocked() {
        var square = new BoardSquare(0, 0);
        square.lock();
        square.lock();
        square.unlock();
        assertThat(square.isLocked()).isTrue();
    }

    @Test
    void unlockBelowZeroClampsToZero() {
        var square = new BoardSquare(0, 0);
        square.unlock();
        assertThat(square.isUnlocked()).isTrue();
        square.lock();
        assertThat(square.isLocked()).isTrue();
        square.unlock();
        assertThat(square.isUnlocked()).isTrue();
    }

    @Test
    void equalsIgnoresLockCount() {
        var a = new BoardSquare(2, 3);
        var b = new BoardSquare(2, 3);
        b.lock();
        assertThat(a).isEqualTo(b);
    }

    @Test
    void hashCodeIgnoresLockCount() {
        var a = new BoardSquare(2, 3);
        var b = new BoardSquare(2, 3);
        b.lock();
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    @Test
    void compareToOrdersByXThenY() {
        var a = new BoardSquare(1, 2);
        var b = new BoardSquare(1, 3);
        var c = new BoardSquare(2, 0);
        assertThat(a.compareTo(b)).isNegative();
        assertThat(b.compareTo(a)).isPositive();
        assertThat(a.compareTo(c)).isNegative();
        assertThat(a.compareTo(a)).isZero();
    }
}

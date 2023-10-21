package baseball;


import baseball.model.Number;
import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    @AfterEach
    public void restoreInput() {
        System.setIn(systemIn);
        Console.close();
    }

    /**
     * @Method : 콘솔 입력 제공
     * @auther : SYB
     * @since : 2023/10/21
     */
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void 사용자_입력_기능_검증() {
        Game game = new Game();
        provideInput("123");
        assertThat(game.inputUserNumber()).isEqualTo(List.of(1, 2, 3));
    }

    @Test
    void 사용자_숫자_입력_예외_테스트() {
        Game game = new Game();
        provideInput("13a");
        assertThrows(IllegalArgumentException.class, () -> game.inputUserNumber());
    }

    @Test
    void 정답_처리_기능_정답_검증(){
        Game game = new Game();
        Number computerNumber = new Number();
        Number userNumber = new Number();

        computerNumber.setUserNumberList(List.of(1,2,3));
        userNumber.setUserNumberList(List.of(1,2,3));

        assertTrue(game.calculator(computerNumber, userNumber));
    }

    @Test
    void 정답_처리_기능_오답_검증(){
        Game game = new Game();
        Number computerNumber = new Number();
        Number userNumber = new Number();

        computerNumber.setUserNumberList(List.of(3,2,1));
        userNumber.setUserNumberList(List.of(1,2,3));

        assertFalse(game.calculator(computerNumber, userNumber));
    }
}

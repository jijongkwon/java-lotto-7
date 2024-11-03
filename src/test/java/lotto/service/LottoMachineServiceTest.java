package lotto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import lotto.domain.LottoMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoMachineServiceTest {

    private LottoMachine lottoMachine;
    private LottoMachineService lottoMachineService;

    @BeforeEach
    public void setup() {
        lottoMachine = LottoMachine.getInstance();
        lottoMachineService = new LottoMachineService();
    }

    // SUCCESS
    @Test
    void 로또_구입금액_입력받는다() {
        // given
        String purchaseAmount = "1000";

        // when
        lottoMachineService.inputLottoPurchaseAmount(purchaseAmount);

        // then
        assertEquals(1000, lottoMachine.getPurchaseAmount());
    }

    @Test
    void 보너스번호를_입력받는다() {
        // given
        String bonusNumber = "12";

        // when
        lottoMachineService.inputBonusNumber(bonusNumber);

        // then
        assertEquals(12, lottoMachine.getBonusNumber());
    }

    @Test
    void 로또_당첨번호를_입력받는다() {
        // given
        String numbers = "1,2,3,4,5,6";

        // when
        lottoMachineService.inputWinningNumbers(numbers);

        // then
        assertEquals(List.of(1, 2, 3, 4, 5, 6), lottoMachine.getWinningLotto().getNumbers());
    }

    // EXCEPTION
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3", "1,2,3,4"})
    void 로또번호_크기가_6이_아닌경우_예외를_던진다(String numbers) {
        assertThrows(IllegalArgumentException.class,
                () -> lottoMachineService.inputWinningNumbers(numbers));
    }
}
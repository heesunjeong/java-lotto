package domain;

import strategy.LottoNumberGenerator;
import study.ValidateUtil;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Lotto {
    private List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static Lotto of(LottoNumberGenerator lottoNumberGenerator) {
        return Lotto.of("", lottoNumberGenerator);
    }

    public static Lotto of(String numbers, LottoNumberGenerator lottoNumberGenerator) {
        List<LottoNumber> lottoNumbers = lottoNumberGenerator
                .generator(numbers)
                .stream()
                .sorted(Comparator.comparingInt(LottoNumber::getValue))
                .collect(toList());
        ValidateUtil.validateLottoNumberCount(lottoNumbers);

        return new Lotto(lottoNumbers);
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getValue)
                .collect(toList());
    }

    public boolean contains(LottoNumber target) {
        return numbers.contains(target);
    }
}

package num.diplom.base.usecase;

import num.diplom.base.exception.UseCaseException;

public interface UseCase<I, O> {
    O execute(I input) throws UseCaseException;
}

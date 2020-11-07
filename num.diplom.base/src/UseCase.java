import exception.UseCaseException;

public interface UseCase<I, O> {
    O execute(I input) throws UseCaseException;
}
